
public class ParseToServer extends Parser
{	
	public String translate(Space clicked)
	{
		String cxy = Integer.toString(clicked.getContents().ordinal()) + Integer.toString(clicked.getRow()) + Integer.toString(clicked.getCol());
		return cxy; 
	}

	public void translate(Byte messageType, Object message)
	{
		
	}
}
