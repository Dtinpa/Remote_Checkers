import java.net.Socket;

public class Listen
{
	private InClient input; 
	private Transcription transcription; 
	private OutFile output; 
	
	public Listen(Integer socketIndex)
	{
		transcription = transcription.getTranscription(); 
		Socket socket = transcription.getSocket(socketIndex); 
		input = new InClient(socket); 
		output = OutFile.getInstance(); 
	}
	
	public void retrieveMessages(Integer socketIndex)
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
		}
	}

}
