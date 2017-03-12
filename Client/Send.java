import java.net.Socket;

public class Send
{
	private ParseToServer parser; 
	private OutServer output; 
	
	public Send(Socket socket)
	{
		parser = new ParseToServer();
		output = new OutServer(socket); 
	}
	
	public void sendMessage(byte b)
	{
		// this will be a byte signifying the type of the following object/message
		output.write(b);
	}
	
	public void sendMessage(Object message)
	{
		output.write(message);
	}
	
	public void sendMove(Space clicked)
	{
		// get translated move as string 
		String xy = parser.translate(clicked);
		output.write(xy);
	}
	
	public void sendMove(Space lastClicked, Space clicked)
	{
		// get translated move as string 
		String xyxy = parser.translate(lastClicked, clicked);
		output.write(xyxy);
	}
	
	public void sendPairRequest()
	{
		
	}

}
