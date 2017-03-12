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
		int s_row = move.getSelectedRow();
		int s_column = move.getSelectedColumn();
		int d_row = move.getDestinationRow();
		int d_column = move.getDestinationColumn();
		
		Element selected = board.getSpace(s_row, s_column);
		
		if (selected == Element.RED && d_row == Board.BLUE_END)
		{
			board.setSpace(Element.REDKING, d_row, d_column);
			GameManagement.getInstance().resetDrawCounters(game_index);
		}
		else if (selected == Element.BLUE && d_row == Board.RED_END)
		{
			board.setSpace(Element.BLUEKING, d_row, d_column);
			GameManagement.getInstance().resetDrawCounters(game_index);
		}
		else
			board.setSpace(selected, d_row, d_column);
		board.setSpace(Element.BLACKSPACE, s_row, s_column);
	}
	
	public boolean jump(int game_index, Move move)
	{
		Board board = boards.get(game_index);
		int s_row = move.getSelectedRow();
		int s_column = move.getSelectedColumn();
		int d_row = move.getDestinationRow();
		int d_column = move.getDestinationColumn();
		
		Element selected = board.getSpace(s_row, s_column);
		int captured_row = 0, captured_column = 0;
		
		captured_row = (d_row > s_row) ? d_row - 1 : d_row + 1;
		captured_column = (d_column > s_column) ? d_column - 1 : d_column + 1;
		
		boolean hasKing = false;
		if (selected == Element.RED && d_row == Board.BLUE_END)
		{
			board.setSpace(Element.REDKING, d_row, d_column);
			hasKing = true;
		}
		else if (selected == Element.BLUE && d_row == Board.RED_END)
		{
			board.setSpace(Element.BLUEKING, d_row, d_column);
			hasKing = true;
		}
		else
			board.setSpace(selected, d_row, d_column);
		board.setSpace(Element.BLACKSPACE, captured_row, captured_column);
		board.setSpace(Element.BLACKSPACE, s_row, s_column);
		
		GameManagement.getInstance().resetDrawCounters(game_index);
		return hasKing;
	}
}
