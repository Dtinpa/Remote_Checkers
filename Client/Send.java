import java.net.Socket;

public class Send
{
	private static Send instance; 
	private ParseToServer parser; 
	private OutServer output; 
	
	private Send(Socket socket)
	{
		parser = new ParseToServer();
		output = new OutServer(socket); 
	}
	
	public static Send getInstance(Socket socket)
	{
		if (instance ==  null)
		{
			instance = new Send(socket); 
		}
		return instance; 
	}
	
	public void sendMessage(byte b)
	{
		output.write(b);
	}
	
	public void sendMessage(Object message)
	{
		//TODO: Get translates stuff;
		parser.translate(null, message); 
		output.write(message);
	}
	
	public void sendMove(Space clicked)
	{
		// get translated move as string 
		String xy = parser.translate(clicked);
		output.write(xy);
	}
	
	public void sendMove(Space clicked, Space lastClicked)
	{
		// get translated move as string 
		String xyxy = parser.translate(clicked, lastClicked);
		output.write(xyxy);
	}
	
	public void sendPairRequest()
	{
		
	}

}
