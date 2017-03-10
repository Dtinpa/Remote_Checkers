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
		String cxy = parser.translate(clicked);
		output.write(cxy);
	}
	
	public void sendPairRequest()
	{
		
	}

}
