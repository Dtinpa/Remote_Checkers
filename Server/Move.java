
public class Move {

	private int selected_row = 0;
	private int selected_column = 0;
	private int destination_row = 0;
	private int destination_column = 0;
	
	public Move(int s_row, int s_column, int d_row, int d_column)
	{
		selected_row = s_row;
		selected_column = s_column;
		destination_row = d_row;
		destination_column = d_column;
	}
	
	public int getDistance()
	{
		return Math.abs(selected_row - destination_row);
	}
	
	public int getSelectedRow()
	{
		return selected_row;
	}
	
	public int getSelectedColumn()
	{
		return selected_column;
	}
	
	public int getDestinationRow()
	{
		return destination_row;
	}
	
	public int getDestinationColumn()
	{
		return destination_column;
	}
}
