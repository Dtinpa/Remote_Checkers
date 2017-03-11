import java.io.Serializable;

public class Board implements Serializable
{
	final static int NUM_ROW = 8;
	final static int NUM_COLUMN = 8;
	final static int RED_END = 0;
	final static int RED_BOUNDARY = 2;
	final static int BLUE_BOUNDARY = 5;
	final static int BLUE_END = 7;
	
	private Element[][] storage;

	public Board()
	{
		storage = new Element[NUM_ROW][NUM_COLUMN];
		
		for (int i = 0; i < NUM_ROW; i++)
		{
			for (int j = 0; j < NUM_COLUMN; j++)
			{
				if (i % 2 == 0)
				{
					if (j % 2 == 0)
					{
						if (i <= RED_BOUNDARY)
							storage[i][j] = Element.RED;
						else if (i >= BLUE_BOUNDARY)
							storage[i][j] = Element.BLUE;
						else
							storage[i][j] = Element.BLACKSPACE;
					}
					else
						storage[i][j] = Element.WHITESPACE;
				}
				else
				{
					if (j % 2 == 0)
						storage[i][j] = Element.WHITESPACE;
					else
					{
						if (i <= RED_BOUNDARY)
							storage[i][j] = Element.RED;
						else if (i >= BLUE_BOUNDARY)
							storage[i][j] = Element.BLUE;
						else
							storage[i][j] = Element.BLACKSPACE;
					}
				}
			}
		}
	}
	
	public Element getSpace(int row, int column)
	{
		if (row < 0 || row >= NUM_ROW || column < 0 || column >= NUM_COLUMN)
			return null;
		return storage[row][column];
	}
	
	public void setSpace(Element element, int row, int column)
	{
		if (row < 0 || row >= NUM_ROW || column < 0 || column >= NUM_COLUMN)
			return;
		storage[row][column] = element;
	}
	
	public String toString()
	{
		String s = "";
		for (int i = 0; i < NUM_ROW; i++)
		{
			for (int j = 0; j < NUM_COLUMN; j++)
			{
				s += storage[i][j].ordinal();
				if (j < NUM_COLUMN - 1)
					s += " ";
			}
			s += "\n";
		}
		
		return s;
	}
	
	public Board clone()
	{
		Board clone = new Board();
		for (int i = 0; i < NUM_ROW; i++)
		{
			for (int j = 0; j < NUM_ROW; j++)
			{
				Element space = storage[i][j];
				clone.setSpace(space, i, j);
			}
		}
		
		return clone;
	}
	
	public boolean compare(Board board)
	{
		for (int i = 0; i < Board.NUM_ROW; i++)
		{
			for (int j = 0; j < Board.NUM_COLUMN; j++)
			{
				if (board.getSpace(i, j) != storage[i][j])
					return false;
			}
		}
		
		return true;
	}
	
	public boolean canJump(int row, int column)
	{	
		boolean result = false;
		boolean canJumpNW = false, canJumpNE = false, canJumpSW = false, canJumpSE = false;
		if (isRedPiece(row, column))
		{
			canJumpNW = isBluePiece(row+1, column+1) && isEmptySpace(row+2, column+2);
			canJumpNE = isBluePiece(row+1, column-1) && isEmptySpace(row+2, column-2);
			if (isKingPiece(row, column))
			{
				canJumpSW = isBluePiece(row-1, column+1) && isEmptySpace(row-2, column+2);
				canJumpSE = isBluePiece(row-1, column-1) && isEmptySpace(row-2, column-2);
			}
		}
		else if (isBluePiece(row, column))
		{
			canJumpNW = isRedPiece(row-1, column-1) && isEmptySpace(row-2, column-2);
			canJumpNE = isRedPiece(row-1, column+1) && isEmptySpace(row-2, column+2);
			if (isKingPiece(row, column))
			{
				canJumpSW = isRedPiece(row+1, column-1) && isEmptySpace(row+2, column-2);
				canJumpSE = isRedPiece(row+1, column+1) && isEmptySpace(row+2, column+2);
			}
		}
		result = canJumpNW || canJumpNE || canJumpSW || canJumpSE;
		
		return result;
	}
	
	public boolean canStep(int row, int column)
	{
		boolean result = false;
		boolean canStepNW = false, canStepNE = false, canStepSW = false, canStepSE = false;
		
		if (!canJump(row, column))
		{
			if (isRedPiece(row, column))
			{
				canStepNW = isEmptySpace(row+1, column+1);
				canStepNE = isEmptySpace(row+1, column-1);
				if (isKingPiece(row, column))
				{
					canStepSW = isEmptySpace(row-1, column+1);
					canStepSE = isEmptySpace(row-1, column-1);
				}
			}
			else if (isBluePiece(row, column))
			{
				canStepNW = isEmptySpace(row-1, column-1);
				canStepNE = isEmptySpace(row-1, column+1);
				if (isKingPiece(row, column))
				{
					canStepSW = isEmptySpace(row+1, column-1);
					canStepSE = isEmptySpace(row+1, column+1);
				}
			}
		}
		result = canStepNW || canStepNE || canStepSW || canStepSE;
		
		return result;
	}
	
	public boolean isEmptySpace(int row, int column)
	{
		Element space = getSpace(row, column);
		return (space == Element.BLACKSPACE);
	}
	
	public boolean isBluePiece(int row, int column)
	{
		Element space = getSpace(row, column);
		return (space == Element.BLUE || space == Element.BLUEKING);
	}
	
	public boolean isRedPiece(int row, int column)
	{
		Element space = getSpace(row, column);
		return (space == Element.RED || space == Element.REDKING);
	}
	
	public boolean isKingPiece(int row, int column)
	{
		Element space = getSpace(row, column);
		return (space == Element.REDKING || space == Element.BLUEKING);
	}
}
