import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.SwingWorker;


public class Connect
{
	private Lock lock; 
	private Socket socket;
	private ServerSocket serverSocket;
	private OutFile outFile; 
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
					socket = serverSocket.accept();
					outFile.write("Socket connected: " + socket.getInetAddress() + ":" + socket.getPort());
					lock.tryLock();
					transcription.addClientSocket(socket);
					lock.unlock();
					int index = transcription.getSocketIndex(socket); 
					outFile.write("Added client to client storage.");
					
					lock.tryLock(); 
					Thread listenThread = new Thread()
					{
						public void run()
						{
							listen = new Listen(); 
							listen.retrieveMessages(index);
						}
					}; 
					listenThread.start();
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
