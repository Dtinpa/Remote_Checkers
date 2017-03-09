import java.net.Socket;

public class Listen
{
	private InClient input; 
	private Transcription transcription; 
	
	public Listen(Integer socketIndex)
	{
		transcription = transcription.getTranscription(); 
		Socket socket = transcription.getSocket(socketIndex); 
		input = new InClient(socket); 
	}
	
	public void retrieveMessages(Integer socketIndex)
	{
		//Do this for now
		Byte messageType = input.readByte();
		String message = input.read(); 
		Object[] retVal = {messageType, message}; 
		System.out.println(retVal[0].toString());
		System.out.println(retVal[1].toString());
		
		//while loop here that keeps listening to messages....then spawn thing to process those messages (queue)
		/*while(true)
		{
			
		}*/
	}

}
