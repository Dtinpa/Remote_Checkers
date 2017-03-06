import java.util.*;

public class Server
{
	public String version = "0.1.0";

	public static void main(String[] args)
	{
		GameManagement gM = GameManagement.getInstance();
		MatchMaking matchMaking = new MatchMaking();
		Parser parser = new Parser();
		Transcription transcription = new Transcription();
		ArrayList<IO> io;
	}

}
