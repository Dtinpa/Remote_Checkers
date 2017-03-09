
public class ParseToServer extends Parser
{	
	public String translate(Space clicked)
	{
		String xy = Integer.toString(clicked.getRow()) + Integer.toString(clicked.getCol());
		return xy; 
	}

	public void translate(Byte messageType, Object message)
	{
		
	}
}
