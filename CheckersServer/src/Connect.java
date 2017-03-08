import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Connect
{
	private Socket socket;
	private ServerSocket serverSocket;
	private OutFile outFile; 
	
	public Connect()
	{
		outFile = OutFile.getInstance();
		socket = new Socket();
		try 
		{
			serverSocket = new ServerSocket(9900);
			outFile.write("Server socket was connected");
		}
		catch (IOException e) 
		{
			outFile.write(e.getMessage());
		} 
	}
	
	public Socket connectSocket()
	{
		try 
		{
			socket = serverSocket.accept();
			outFile.write("Socket connected: " + socket.getInetAddress() + ":" + socket.getPort());
		}
		catch (IOException e) 
		{
			outFile.write(e.getMessage());
			socket = null; 
		}
		return socket; 
	}

}