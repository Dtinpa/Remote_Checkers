import java.net.Socket;
import java.util.*;

public class GameManagement
{
	final int NO_CAPTURE_LIMIT = 100;
	final int SAME_STATE_LIMIT = 3;
	
	private static GameManagement singleton;
	
	public Transcription transcription;
	public PlayerManagement playerManagement;
	public ArrayList<Board> boards;
	public ArrayList<ArrayList<BoardState>> previous_states;
	public ArrayList<Integer> drawCounter;
	public MakeMove makeMove;
	public ValidPieces validPieces;
	public ValidMoves validMoves;
	
	private GameManagement()
	{
		transcription = Transcription.getTranscription();
		playerManagement = PlayerManagement.getInstance();
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
		
	public void openGameForClients(int matchIndex, Socket clientOne, Socket clientTwo)
	{
		playerManagement.assignOrder(matchIndex, clientOne, clientTwo);
		if (matchIndex == boards.size())
		{
			boards.add(new Board());
			previous_states.add(new ArrayList<BoardState>());
			drawCounter.add(0);
		}
		else
		{
			boards.set(matchIndex, new Board());
			previous_states.set(matchIndex, new ArrayList<BoardState>());
			drawCounter.set(matchIndex, 0);
		}
		
		startTurn(matchIndex);
	}
	
	public void startTurn(int matchIndex)
	{
		Player active_player = playerManagement.getActivePlayer(matchIndex);
		Player inactive_player = playerManagement.getInactivePlayer(matchIndex);
		
		int active_color = active_player.getColor();
		Board validPieceBoard = validPieces.find(matchIndex, active_color);
		Board board = boards.get(matchIndex);
		if (board.compare(validPieceBoard))
		{
			EndGameCondition win = (active_color == Player.BLUE) ? EndGameCondition.REDWIN : EndGameCondition.BLUEWIN;
			endGame(matchIndex, win);
			return;
		}
		
		transcription.write(active_player.getSocket(), (byte)'B');
		transcription.write(active_player.getSocket(), validPieceBoard);
		transcription.write(active_player.getSocket(), (byte)'M');
		transcription.write(active_player.getSocket(), "Your turn - Select a Piece to Move");
		
		transcription.write(inactive_player.getSocket(), (byte)'M');
		transcription.write(inactive_player.getSocket(), "Opponent's Turn, Please Wait...");
		// Send Normal board to Opponent?
	}
	
	public void processSelectPieceMessage(int matchIndex, int selected_row, int selected_column)
	{
		Player active_player = playerManagement.getActivePlayer(matchIndex);
		int active_color = active_player.getColor();
		Board validPieceBoard = validPieces.find(matchIndex, active_color);
		Board validMoveBoard = validMoves.find(matchIndex, selected_row, selected_column);
		for (int i = 0; i < Board.NUM_ROW; i++)
		{
			for (int j = 0; j < Board.NUM_COLUMN; j++)
			{
				if (validPieceBoard.isValidPiece(i, j))
				{
					Element space = validPieceBoard.getSpace(i, j);
					validMoveBoard.setSpace(space, i, j);
				}
			}
		}

		transcription.write(active_player.getSocket(), (byte)'B');
		transcription.write(active_player.getSocket(), validMoveBoard);
		transcription.write(active_player.getSocket(), (byte)'M');
		transcription.write(active_player.getSocket(), "Your turn - Select a Space to Move Into or\n"
				+ "Select a Different Piece to Move");
	}
	
	public void processMoveMessage(int matchIndex, Move move)
	{
		int distance = move.getDistance();
		if (distance == MakeMove.JUMP_DISTANCE)
		{
			boolean hasKing = makeMove.jump(matchIndex, move);
			Board validMoveBoard = validMoves.find(matchIndex, move.selected_row, move.selected_column);
			if (boards.get(matchIndex).compare(validMoveBoard) || hasKing)
				endTurn(matchIndex);
			else
			{
				Player active_player = playerManagement.getActivePlayer(matchIndex);
				transcription.write(active_player.getSocket(), (byte)'B');
				transcription.write(active_player.getSocket(), validMoveBoard);
			}
		}
		else if (distance == MakeMove.STEP_DISTANCE)
		{
			makeMove.step(matchIndex, move);
			endTurn(matchIndex);
		}
	}
	
	public void endTurn(int matchIndex)
	{
		Board board = boards.get(matchIndex);
		Player active_player = playerManagement.getActivePlayer(matchIndex);
		Player inactive_player = playerManagement.getInactivePlayer(matchIndex);
		transcription.write(active_player.getSocket(), (byte)'B');
		transcription.write(active_player.getSocket(), board);
		transcription.write(inactive_player.getSocket(), (byte)'B');
		transcription.write(inactive_player.getSocket(), board);
		
		ArrayList<BoardState> prev_states = previous_states.get(matchIndex);
		for (int i = 0; i < prev_states.size(); i++)
		{
			BoardState state = prev_states.get(i);
			if (state.board.compare(board))
			{
				state.count++;
				if (state.count == SAME_STATE_LIMIT)
				{
					endGame(matchIndex, EndGameCondition.DRAW);
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
		
		int drawCount = drawCounter.get(matchIndex);
		if (drawCount == NO_CAPTURE_LIMIT)
		{
			endGame(matchIndex, EndGameCondition.DRAW);
			return;
		}
		
		drawCounter.set(matchIndex, drawCount+1);
		playerManagement.changeActivePlayer(matchIndex);
		startTurn(matchIndex);
	}
	
	public void endGame(int matchIndex, EndGameCondition condition)
	{
		Player player1 = playerManagement.getPlayerOne(matchIndex);
		Player player2 = playerManagement.getPlayerTwo(matchIndex);
		
		if (condition == EndGameCondition.BLUEWIN)
		{
			//Send win-loss notifs
		}
		else if (condition == EndGameCondition.REDWIN)
		{
			//Send win-loss notifs
		}
		else if (condition == EndGameCondition.DRAW)
		{
			//Send draw notifs
		}
	}
	
	public void resetDrawCounters(int matchIndex)
	{
		previous_states.get(matchIndex).clear();
		drawCounter.set(matchIndex, 0);
	}
	
	public void rematch(int matchIndex)
	{
		boards.set(matchIndex, new Board());
		playerManagement.swapTurnOrder(matchIndex);
		resetDrawCounters(matchIndex);
		startTurn(matchIndex);
	}
	
	public void closeGame(int matchIndex)
	{
		playerManagement.dismissPlayers(matchIndex);
		boards.set(matchIndex, null);
		drawCounter.set(matchIndex, -1);
		previous_states.set(matchIndex, null);
	}
}
