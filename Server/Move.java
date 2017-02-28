
public class Move {

	public int selected_row = 0;
	public int selected_column = 0;
	public int destination_row = 0;
	public int destination_column = 0;
	
	public int getDistance()
	{
		return Math.abs(selected_row - destination_row);
	}
}
