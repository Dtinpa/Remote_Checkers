import java.util.*;

public class MakeMove
{
	public final static int STEP_DISTANCE = 1;
	public final static int JUMP_DISTANCE = 2;
	public ArrayList<Board> boards;
	
	public MakeMove(ArrayList<Board> boards)
	{
		this.boards = boards;
	}
	
	public void step(int game_index, Move move)
	{
		Board board = boards.get(game_index);
		Element selected = board.getSpace(move.selected_row, move.selected_column);
		
		if (selected == Element.RED && move.destination_row == Board.BLUE_END)
		{
			board.setSpace(Element.REDKING, move.destination_row, move.destination_column);
			GameManagement.getInstance().resetDrawCounters(game_index);
		}
		else if (selected == Element.BLUE && move.destination_row == Board.RED_END)
		{
			board.setSpace(Element.BLUEKING, move.destination_row, move.destination_column);
			GameManagement.getInstance().resetDrawCounters(game_index);
		}
		else
			board.setSpace(selected, move.destination_row, move.destination_column);
		board.setSpace(Element.BLACKSPACE, move.selected_row, move.selected_column);
	}
	
	public boolean jump(int game_index, Move move)
	{
		Board board = boards.get(game_index);
		Element selected = board.getSpace(move.selected_row, move.selected_column);
		int captured_row = 0, captured_column = 0;
		
		captured_row = (move.destination_row > 0) ? move.destination_row - 1 : move.destination_row + 1;
		captured_column = (move.destination_column > 0) ? move.destination_column - 1 : move.destination_column + 1;
		
		boolean hasKing = false;
		if (selected == Element.RED && move.destination_row == Board.BLUE_END)
		{
			board.setSpace(Element.REDKING, move.destination_row, move.destination_column);
			hasKing = true;
		}
		else if (selected == Element.BLUE && move.destination_row == Board.RED_END)
		{
			board.setSpace(Element.BLUEKING, move.destination_row, move.destination_column);
			hasKing = true;
		}
		else
			board.setSpace(selected, move.destination_row, move.destination_column);
		board.setSpace(Element.BLACKSPACE, captured_row, captured_column);
		board.setSpace(Element.BLACKSPACE, move.selected_row, move.selected_column);
		
		GameManagement.getInstance().resetDrawCounters(game_index);
		return hasKing;
	}
}
