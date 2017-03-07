import java.io.IOException;
import java.net.Socket;


public class Connect
{
	private int port = 9900;
	private InFile inFile = InFile.getInstance();
	private OutFile output = OutFile.getInstance(); 
	
	public Socket connectSocket()
	{
		Socket clientSocket = null;
		try
		{
			String server = inFile.read();
			clientSocket = new Socket(server, port);
		}
		catch (IOException e)
		{ output.writeError(e.getMessage()); }
		return clientSocket;
	}

}
