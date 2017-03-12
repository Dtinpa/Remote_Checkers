import java.util.*;

public class ValidPieces extends Valid
{
	public ValidPieces(ArrayList<Board> board)
	{
		super(board);
	}

	public Board find(int game_index, int active_color)
	{
		Board board = this.boards.get(game_index);
		Board clone = board.cloneBoard();
		
		if (hasJumpPieces(board, active_color))
		{
			for (int i = 0; i < Board.NUM_ROW; i++)
			{
				for (int j = 0; j < Board.NUM_COLUMN; j++)
				{
					if (active_color == Player.RED)
					{
						if (board.isRedPiece(i, j) && board.canJump(i, j))
						{
							Element valid = board.isKingPiece(i, j) ? Element.VALIDKING : Element.VALID;
							clone.setSpace(valid, i, j);
						}
					}
					else if (active_color == Player.BLUE)
					{
						if (board.isBluePiece(i, j) && board.canJump(i, j))
						{
							Element valid = board.isKingPiece(i, j) ? Element.VALIDKING : Element.VALID;
							clone.setSpace(valid, i, j);
						}
					}
				}
			}
		}
		else
		{
			for (int i = 0; i < Board.NUM_ROW; i++)
			{
				for (int j = 0; j < Board.NUM_COLUMN; j++)
				{
					if (active_color == Player.RED)
					{
						if (board.isRedPiece(i, j) && board.canStep(i, j))
						{
							Element valid = board.isKingPiece(i, j) ? Element.VALIDKING : Element.VALID;
							clone.setSpace(valid, i, j);
						}
					}
					else if (active_color == Player.BLUE)
					{
						if (board.isBluePiece(i, j) && board.canStep(i, j))
						{
							Element valid = board.isKingPiece(i, j) ? Element.VALIDKING : Element.VALID;
							clone.setSpace(valid, i, j);
						}
					}
				}
			}
		}
			
		return clone;
	}
	
	private boolean hasJumpPieces(Board board, int color)
	{
		for (int i = 0; i < Board.NUM_ROW; i++)
		{
			for (int j = 0; j < Board.NUM_COLUMN; j++)
			{
				if (color == Player.RED)
				{
					if (board.isRedPiece(i, j) && board.canJump(i, j))
						return true;
				}
				else if (color == Player.BLUE)
				{
					if (board.isBluePiece(i, j) && board.canJump(i, j))
						return true;
				}
			}
		}
		
		return false;
	}
}
