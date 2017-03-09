import java.net.Socket;

public class Send
{
	private OutClient output; 
	private Socket socket; 
	private Transcription transcription; 
	
	public Send(Integer index)
	{
		transcription = Transcription.getTranscription(); 
		socket = transcription.getSocket(index); 
		output = new OutClient(socket); 
	}
	
	public void sendMessage(byte b) 
	{
		output.write(b);
	}
	
	public void sendMessage(String message)
	{
		output.write(message);
	}
	
	public void sendMove()
	{ 
		//Transcription.getTranscription().translateToServer(); 
	}
	
	public void sendPairRequest()
	{
		
	}

	
}
