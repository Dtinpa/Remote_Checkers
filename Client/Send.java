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
		//TODO: Get translated STUFF; 
		parser.translate(clicked);
		//TODO: Change this cause this will not work -- temp
		output.write(clicked.getName());
	}
	
	public void sendPairRequest()
	{
		
	}

}
