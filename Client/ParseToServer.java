
public class ParseToServer extends Parser
{	
	public String translate(Space clicked)
	{
		String xy = Integer.toString(clicked.getRow()) + Integer.toString(clicked.getCol());
		return xy; 
	}
	
	public String translate(Space clicked, Space lastClicked)
	{
		String xyxy = Integer.toString(clicked.getRow()) + Integer.toString(clicked.getCol()) + Integer.toString(lastClicked.getRow()) + Integer.toString(lastClicked.getCol());
		return xyxy; 
	}

	public void translate(Byte messageType, Object message)
	{
		
	}
}
