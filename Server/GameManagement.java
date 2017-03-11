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
	public ArrayList<Boolean> rematchStandby;
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
		rematchStandby = new ArrayList<Boolean>();
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
			rematchStandby.add(false);
		}
		else
		{
			boards.set(matchIndex, new Board());
			previous_states.set(matchIndex, new ArrayList<BoardState>());
			drawCounter.set(matchIndex, 0);
			rematchStandby.set(matchIndex, false);
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
			endGame(matchIndex, false);
			return;
		}
		
		sendMessageToPlayer(active_player, 'B', validPieceBoard);
		sendMessageToPlayer(active_player, 'M', "Your turn - Select a Piece to Move");
		
		sendMessageToPlayer(inactive_player, 'M', "Opponent's Turn, Please Wait...");
	}
	
	public void processSelectPieceMessage(int matchIndex, int selected_row, int selected_column)
	{
		Player active_player = playerManagement.getActivePlayer(matchIndex);
		int active_color = active_player.getColor();
		Board validPieceBoard = validPieces.find(matchIndex, active_color);
		Board validMoveBoard = validMoves.find(matchIndex, selected_row, selected_column, false);
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

		sendMessageToPlayer(active_player, 'B', validMoveBoard);
		sendMessageToPlayer(active_player, 'M', "Your turn - Select a Space to Move Into");
	}
	
	public void processMoveMessage(int matchIndex, Move move)
	{
		int distance = move.getDistance();
		if (distance == MakeMove.JUMP_DISTANCE)
		{
			boolean hasKing = makeMove.jump(matchIndex, move);
			Board validMoveBoard = validMoves.find(matchIndex, move.destination_row, move.destination_column, true);
			if (boards.get(matchIndex).compare(validMoveBoard) || hasKing)
				endTurn(matchIndex);
			else
			{
				Player active_player = playerManagement.getActivePlayer(matchIndex);
				sendMessageToPlayer(active_player, 'B', validMoveBoard);
			}
		}
		else if (distance == MakeMove.STEP_DISTANCE)
		{
			makeMove.step(matchIndex, move);
			endTurn(matchIndex);
		}
	}
	
	public void sendMessageToPlayer(Player player, char messageType, Object message)
	{
		transcription.write(player.getSocket(), (byte) messageType);
		transcription.write(player.getSocket(), message);
	}
	
	public void endTurn(int matchIndex)
	{
		Board board = boards.get(matchIndex);
		Player active_player = playerManagement.getActivePlayer(matchIndex);
		Player inactive_player = playerManagement.getInactivePlayer(matchIndex);
		sendMessageToPlayer(active_player, 'B', board);
		sendMessageToPlayer(inactive_player, 'B', board);
		
		ArrayList<BoardState> prev_states = previous_states.get(matchIndex);
		for (int i = 0; i < prev_states.size(); i++)
		{
			BoardState state = prev_states.get(i);
			if (state.board.compare(board))
			{
				state.count++;
				if (state.count == SAME_STATE_LIMIT)
				{
					endGame(matchIndex, true);
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
			endGame(matchIndex, true);
			return;
		}
		
		drawCounter.set(matchIndex, drawCount+1);
		playerManagement.changeActivePlayer(matchIndex);
		startTurn(matchIndex);
	}
	
	public void endGame(int matchIndex, boolean isDraw)
	{
		Player active_player = playerManagement.getActivePlayer(matchIndex);
		Player inactive_player = playerManagement.getInactivePlayer(matchIndex);
		
		if (!isDraw)
		{
			sendMessageToPlayer(active_player, 'E', "You lose!");
			sendMessageToPlayer(inactive_player, 'E', "You win!");
		}
		else
		{
			sendMessageToPlayer(active_player, 'E', "It's a draw!");
			sendMessageToPlayer(inactive_player, 'E', "It's a draw!");
		}
	}
	
	public void resetDrawCounters(int matchIndex)
	{
		previous_states.get(matchIndex).clear();
		drawCounter.set(matchIndex, 0);
		
	}
	
	public void rematch(int matchIndex)
	{
		boolean rematchStatus = rematchStandby.get(matchIndex);
		if (!rematchStatus)
			rematchStatus = true;
		else
		{
			boards.set(matchIndex, new Board());
			playerManagement.swapTurnOrder(matchIndex);
			resetDrawCounters(matchIndex);
			rematchStandby.set(matchIndex, false);
			
			Player active_player = playerManagement.getActivePlayer(matchIndex);
			Player inactive_player = playerManagement.getInactivePlayer(matchIndex);
			sendMessageToPlayer(active_player, 'B', boards.get(matchIndex));
			sendMessageToPlayer(inactive_player, 'B', boards.get(matchIndex));
			
			startTurn(matchIndex);
		}
	}
	
	public void closeGame(int matchIndex)
	{
		Player active_player = playerManagement.getActivePlayer(matchIndex);
		Player inactive_player = playerManagement.getInactivePlayer(matchIndex);
		sendMessageToPlayer(active_player, 'D', " ");
		sendMessageToPlayer(inactive_player, 'D', " ");
		
		playerManagement.dismissPlayers(matchIndex);
		boards.set(matchIndex, null);
		drawCounter.set(matchIndex, -1);
		rematchStandby.set(matchIndex, null);
		previous_states.set(matchIndex, null);
	}
}
