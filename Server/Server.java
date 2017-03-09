import java.util.*;

public class Server
{
	public String version = "0.1.0";

	public static void main(String[] args)
	{
		//GameManagement gM = GameManagement.getInstance();
		//MatchMaking matchMaking = new MatchMaking();
		Transcription transcription = Transcription.getTranscription();
		transcription.openToConnections();
		//ArrayList<IO> io;
	}

}
