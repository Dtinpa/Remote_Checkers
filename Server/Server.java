import java.util.*;

public class Server
{
	public String version = "0.1.0";
	private GameManagement gM;
	private Parser parser;
	private Transcription transcription;
	private ArrayList<IO> io;

	public static void main(String[] args)
	{
		Server server = new Server();
		server.gM = GameManagement.getInstance();
		server.parser = new Parser();
		server.transcription = new Transcription();
		server.io = new ArrayList<IO>();
	}

}
