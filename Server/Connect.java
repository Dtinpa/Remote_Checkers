import java.io.IOException;
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
	private Send send; 
	private Listen listen; 
	private Transcription transcription; 
	
	public Connect()
	{
		lock = new ReentrantLock(); 
		outFile = OutFile.getInstance();
		transcription = transcription.getTranscription(); 
	}
	
	public void acceptConnections()
	{
		try 
		{
			serverSocket = new ServerSocket(9900);
			while (true)
			{
					outFile.write("Sever ready to accept a new client.");
					//Blocking call until a client is connected 
					socket = serverSocket.accept();
					outFile.write("Socket connected: " + socket.getInetAddress() + ":" + socket.getPort());
					lock.tryLock();
					transcription.addClientSocket(socket);
					int index = transcription.getSocketIndex(socket);
					//Stop passing sockets, start passing the index to align with 
					//the other parallel arrays 
					lock.unlock();
					
					outFile.write("Added client to client storage.");
					
					lock.tryLock(); 
					send = new Send(index);
					byte thisByte = (byte) 'C'; 
					send.sendMessage(thisByte);
					//send.sendMessage("Messgage for Server");
					
					Thread listenThread = new Thread()
					{
						public void run()
						{
							listen = new Listen(index); 
							listen.retrieveMessages(index);
						}
					}; 
					listenThread.start();
					

					//Search for matches here?
					//MatchMaker.matchClients()
					
					//Then is that it????!?!???!
					
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
