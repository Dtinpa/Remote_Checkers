import java.util.*;

public class GameManagement
{
	final int NO_CAPTURE_LIMIT = 100;
	final int SAME_STATE_LIMIT = 3;
	
	private static GameManagement singleton;
	public static int next_game = 0;
	
	public PlayerManagement playerManagement;
	public ArrayList<Board> boards;
	public ArrayList<ArrayList<BoardState>> previous_states;
	public ArrayList<Integer> drawCounter;
	public MakeMove makeMove;
	public ValidPieces validPieces;
	public ValidMoves validMoves;
	
	private GameManagement()
	{
		playerManagement = new PlayerManagement();
		boards = new ArrayList<Board>();
		previous_states = new ArrayList<ArrayList<BoardState>>();
		drawCounter = new ArrayList<Integer>();
		makeMove = new MakeMove(boards);
		validMoves = new ValidMoves(boards);
		validPieces = new ValidPieces(boards);
	}
	
	public static GameManagement getInstance()
	{
		if (singleton == null)
			singleton = new GameManagement();
		
		return singleton;
	}
		
	public void startGame(Player firstClient, Player secondClient)
	{
		playerManagement.assignOrder(firstClient, secondClient);
		boards.add(new Board());
		previous_states.add(new ArrayList<BoardState>());
		drawCounter.add(0);
		next_game++;
	}
	
	public void processSelectPieceMessage(int game_index, int selected_row, int selected_column)
	{
		Board validMoveBoard = validMoves.find(game_index, selected_row, selected_column);
		// Send ValidMove Board to player
	}
	
	public void processMoveMessage(int game_index, Move move)
	{
		int distance = move.getDistance();
		if (distance == MakeMove.JUMP_DISTANCE)
		{
			boolean hasKing = makeMove.jump(game_index, move);
			Board validMoveBoard = validMoves.find(game_index, move.selected_row, move.selected_column);
			if (boards.get(game_index).compare(validMoveBoard) || hasKing)
				endTurn(game_index);
			//else
			//	Send ValidMove Board to continue jump
		}
		else if (distance == MakeMove.STEP_DISTANCE)
		{
			makeMove.step(game_index, move);
			endTurn(game_index);
		}
	}
	
	public void endTurn(int game_index)
	{
		Board board = boards.get(game_index);

		ArrayList<BoardState> prev_states = previous_states.get(game_index);
		for (int i = 0; i < prev_states.size(); i++)
		{
			BoardState state = prev_states.get(i);
			if (state.board.compare(board))
			{
				state.count++;
				if (state.count == SAME_STATE_LIMIT)
				{
					endGame(game_index, EndGameCondition.DRAW);
					return;
				}
				break;
			}
			else if (i == prev_states.size() - 1)
			{
				BoardState new_state = new BoardState(board);
				prev_states.add(new_state);
			}
		}
		
		int drawCount = drawCounter.get(game_index);
		if (drawCount == NO_CAPTURE_LIMIT)
		{
			endGame(game_index, EndGameCondition.DRAW);
			return;
		}
		
		Player active_player = playerManagement.getActivePlayer(game_index);
		int active_color = (active_player.color == Player.BLUE) ? Player.RED : Player.BLUE;
		Board validBoard = validPieces.find(game_index, active_color);
		if (board.compare(validBoard))
		{
			EndGameCondition win = (active_color == Player.BLUE) ? EndGameCondition.REDWIN : EndGameCondition.BLUEWIN;
			endGame(game_index, win);
			return;
		}
		
		drawCounter.set(game_index, drawCount+1);
		playerManagement.changeActivePlayer(game_index);
		// Send ValidPiece Board to next player
	}
	
	public void endGame(int game_index, EndGameCondition condition)
	{
		Player player1 = playerManagement.getPlayerOne(game_index);
		Player player2 = playerManagement.getPlayerTwo(game_index);
		
		if (condition == EndGameCondition.BLUEWIN)
		{
			if (player1.color == Player.BLUE)
				player1.score++;
			else
				player2.score++;
		}
		else if (condition == EndGameCondition.REDWIN)
		{
			if (player1.color == Player.RED)
				player1.score++;
			else
				player2.score++;
		}
		
		// Send Rematch request
	}
	
	public void resetDrawCounters(int game_index)
	{
		previous_states.get(game_index).clear();
		drawCounter.set(game_index, 0);
	}
	
	public void rematch(int game_index)
	{
		boards.set(game_index, new Board());
		playerManagement.swapTurnOrder(game_index);
		resetDrawCounters(game_index);
	}
}
