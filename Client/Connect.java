import java.io.IOException;
import java.net.Socket;


public class Connect
{
	private int port;
	private InFile inFile; 
	private OutFile output; 
	private Socket useSocket; 
	private ServerInfo serverInfo; 
	
	public Connect()
	{
		port = 9900; 
		inFile = InFile.getInstance(); 
		output = OutFile.getInstance();
		serverInfo = ServerInfo.getInstance(); 
	}
	
	public Socket connectSocket()
	{
		try
		{
			String server = inFile.read();
			useSocket = new Socket(server, port);
			serverInfo.setIP(useSocket.getInetAddress().getHostAddress());
			serverInfo.setPort(useSocket.getPort());
			serverInfo.setSocket(useSocket);
			output.write("Connected to server: " + useSocket.getInetAddress() + ":" + useSocket.getPort());
		}
		catch (IOException e)
		{ output.writeError(e.getMessage()); }
		return useSocket;
	}

}
