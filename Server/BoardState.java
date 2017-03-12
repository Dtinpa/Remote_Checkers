
public class BoardState {

	private Board board;
	private int count;
	
	public BoardState(Board board) {
		this.board = board;
		count = 1;
	}

	public Board getBoard()
	{
		return board;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void incrementCount()
	{
		count++;
	}
}
