public class Server
{
	public static void main(String[] args)
	{
		Transcription transcription = Transcription.getInstance();
		transcription.openToConnections();
	}

}
