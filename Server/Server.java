import java.util.ArrayList;

public class Server
{
	public String version = "0.1.0";
	private GameManagement gM;
	private Parser parser;
	private Transcription transciption;
	private IO[] io;

	public static void main(String[] args)
	{
		Server server = new Server();
		server.gM = GameManagement.getInstance();
	}

}
