import java.net.Socket;

public class Listen
{
	private InClient input;
	//private Transcription transcription; 
	private OutFile output; 
	private Parser parser; 
	
	//public Listen(Integer socketIndex)
	public Listen(Socket socket)
	{
		parser = new Parser(); 
		//transcription = Transcription.getTranscription(); 
		//Socket socket = transcription.getSocket(socketIndex); 
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
				return; 
			}
			System.out.println(retVal[0].toString());
			System.out.println(retVal[1].toString());
			parser.translate(messageType, message, matchIndex);
		}
	}

}
