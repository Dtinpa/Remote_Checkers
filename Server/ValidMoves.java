import java.util.*;

public class ValidMoves extends Valid
{
	public ValidMoves(ArrayList<Board> board)
	{
		super(board);
	}

	public Board find(int game_index, int selected_row, int selected_column, boolean jumpOnly)
	{
		Board board = this.boards.get(game_index);
		Board clone = board.cloneBoard();
		
		if (board.canJump(selected_row, selected_column))
		{
			if (board.isRedPiece(selected_row, selected_column))
			{
				if (board.isBluePiece(selected_row+1, selected_column+1) && board.isEmptySpace(selected_row+2, selected_column+2))
					clone.setSpace(Element.GREENSPACE, selected_row+2, selected_column+2);
				if (board.isBluePiece(selected_row+1, selected_column-1) && board.isEmptySpace(selected_row+2, selected_column-2))
					clone.setSpace(Element.GREENSPACE, selected_row+2, selected_column-2);
				
				if (board.isKingPiece(selected_row, selected_column))
				{
					if (board.isBluePiece(selected_row-1, selected_column+1) && board.isEmptySpace(selected_row-2, selected_column+2))
						clone.setSpace(Element.GREENSPACE, selected_row-2, selected_column+2);
					if (board.isBluePiece(selected_row-1, selected_column-1) && board.isEmptySpace(selected_row-2, selected_column-2))
						clone.setSpace(Element.GREENSPACE, selected_row-2, selected_column-2);
				}
			}
			else if (board.isBluePiece(selected_row, selected_column))
			{
				if (board.isRedPiece(selected_row-1, selected_column-1) && board.isEmptySpace(selected_row-2, selected_column-2))
					clone.setSpace(Element.GREENSPACE, selected_row-2, selected_column-2);
				if (board.isRedPiece(selected_row-1, selected_column+1) && board.isEmptySpace(selected_row-2, selected_column+2))
					clone.setSpace(Element.GREENSPACE, selected_row-2, selected_column+2);
				
				if (board.isKingPiece(selected_row, selected_column))
				{
					if (board.isRedPiece(selected_row+1, selected_column-1) && board.isEmptySpace(selected_row+2, selected_column-2))
						clone.setSpace(Element.GREENSPACE, selected_row+2, selected_column-2);
					if (board.isRedPiece(selected_row+1, selected_column+1) && board.isEmptySpace(selected_row+2, selected_column+2))
						clone.setSpace(Element.GREENSPACE, selected_row+2, selected_column+2);
				}
			}
		}
		else if (board.canStep(selected_row, selected_column) && !jumpOnly)
		{
			if (board.isRedPiece(selected_row, selected_column))
			{
				if (board.isEmptySpace(selected_row+1, selected_column+1))
					clone.setSpace(Element.GREENSPACE, selected_row+1, selected_column+1);
				if (board.isEmptySpace(selected_row+1, selected_column-1))
					clone.setSpace(Element.GREENSPACE, selected_row+1, selected_column-1);
				if (board.isKingPiece(selected_row, selected_column))
				{
					if (board.isEmptySpace(selected_row-1, selected_column+1))
						clone.setSpace(Element.GREENSPACE, selected_row-1, selected_column+1);
					if (board.isEmptySpace(selected_row-1, selected_column-1))
						clone.setSpace(Element.GREENSPACE, selected_row-1, selected_column-1);
				}
			}
			else if (board.isBluePiece(selected_row, selected_column))
			{
				if (board.isEmptySpace(selected_row-1, selected_column-1))
					clone.setSpace(Element.GREENSPACE, selected_row-1, selected_column-1);
				if (board.isEmptySpace(selected_row-1, selected_column+1))
					clone.setSpace(Element.GREENSPACE, selected_row-1, selected_column+1);
				if (board.isKingPiece(selected_row, selected_column))
				{
					if (board.isEmptySpace(selected_row+1, selected_column-1))
						clone.setSpace(Element.GREENSPACE, selected_row+1, selected_column-1);
					if (board.isEmptySpace(selected_row+1, selected_column+1))
						clone.setSpace(Element.GREENSPACE, selected_row+1, selected_column+1);
				}
			}
		}
			
		return clone;
	}
}
