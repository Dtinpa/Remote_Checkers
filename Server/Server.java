//import java.util.*;

public class Server
{
	public String version = "0.1.0";

	public static void main(String[] args)
	{
		Transcription transcription = Transcription.getTranscription();
		transcription.openToConnections();
	}

}
