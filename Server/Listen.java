import java.net.Socket;

public class Listen
{
	private InClient input;
	private OutFile output; 
	private Parser parser;
	private Socket socket;
	
	public Listen(Socket socket)
	{
		this.socket = socket;
		parser = new Parser();
		input = new InClient(socket);
		output = OutFile.getInstance(); 
	}
	
	public void retrieveMessages(Integer matchIndex)
	{
		while(true)
		{
			Byte messageType = (Byte)input.read();
			System.out.println(messageType);
			Object message = input.read();
			System.out.println((String)message);
			Object[] retVal = {messageType, message}; 
			if (retVal[0] == null || retVal[1] == null)
			{
				output.write("Client has disconnected.");
				MatchMaking.getInstance().unmatchClients(matchIndex, socket);
				return;
			}
			System.out.println(retVal[0].toString());
			System.out.println(retVal[1].toString());
			parser.translate(messageType, message, matchIndex);
		}
	}

}
