import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Connect
{
	private Lock lock; 
	private Socket socket;
	private ServerSocket serverSocket;
	private OutFile outFile;
	private Transcription transcription; 
	private OutConsole console = new OutConsole();
	
	public Connect()
	{
		lock = new ReentrantLock(); 
		outFile = OutFile.getInstance();
		transcription = Transcription.getTranscription(); 
	}
	
	public void acceptConnections()
	{
		try 
		{
			serverSocket = new ServerSocket(9900);
			InetAddress server = InetAddress.getLocalHost();
			console.write("Server running at IP Address \"" + server.getHostName() + "\"");
			
			while (true)
			{
				outFile.write("Sever ready to accept a new client.");
				//Blocking call until a client is connected 
				socket = serverSocket.accept();
				outFile.write("Socket connected: " + socket.getInetAddress() + ":" + socket.getPort());
				lock.tryLock();
				transcription.addClientSocket(socket);
				//Stop passing sockets, start passing the index to align with 
				//the other parallel arrays 
				lock.unlock();
				
				outFile.write("Added client to client storage.");
				
				lock.tryLock();
				
				MatchMaking maker = MatchMaking.getInstance(); 
				maker.matchClients();
				
				lock.unlock();
			}
		}
		catch (IOException e) 
		{
			outFile.writeError(e.getMessage()); 
		}
	}
	
	public ServerSocket getServerSocket()
	{
		return serverSocket; 
	}
	
	public Socket getSocket()
	{
		return socket; 
	}

}
