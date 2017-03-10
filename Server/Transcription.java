import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Transcription
{
	public Lock lock; 
	public InClient inClient;
	public OutClient outClient;
	public ArrayList<Socket> socketIndicies; 
	public ArrayList<ClientInfo> clientsInfo;
	public Connect connect;
	public Listen listen;
	public Send send;
	public ParseToClient parseToClient;
	public ParseFromClient parseFromClient;
	private static Transcription singleton;

	private Transcription()
	{
		lock = new ReentrantLock(); 
		outClient = new OutClient(); 
	}
	
	public static Transcription getTranscription()	// implements singleton
	{
		if(singleton == null)
		{ 
			singleton = new Transcription(); 
		}
		return singleton;
	}
	
	public void openToConnections()
	{
		socketIndicies = new ArrayList<Socket>(); 
		clientsInfo = new ArrayList<ClientInfo>(); 
		
		connect = new Connect();
		connect.acceptConnections(); 
	}
	
	public Socket getSocket(int index)
	{
		return socketIndicies.get(index);  
	}
	
	public Integer getSocketIndex(Socket socket)
	{
		//To get the socket from this index....get the socket from the index arraylist
		//then get from HashMap as the key to get ClientInfo...which is rendered useless now
		//but keeping for older versions
		return socketIndicies.indexOf(socket);
	}
	
	public void addClientSocket(Socket clientSocket)
	{
		ClientInfo client = new ClientInfo(); 
		client.setSocket(clientSocket);
		socketIndicies.add(clientSocket); 
		clientsInfo.add(client); 
	}
	
	public ClientInfo peakClientForMatching()
	{
		ClientInfo client = clientsInfo.get(0); 
		return client; 
	}
	
	public ClientInfo getClientForMatching()
	{
		ClientInfo client = clientsInfo.remove(0);
		return client;
	}
	
	public int getClientsCount()
	{
		return clientsInfo.size();
	}
	
	public void removeDisconnectedClients()
	{
		while (!lock.tryLock())
		{
			try 
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e){}
		}
		//ArrayList<ClientInfo> disconnectedClients = new ArrayList<ClientInfo>();
		for (int i = 0; i < clientsInfo.size(); i++)
		{
			ClientInfo client = clientsInfo.get(i);
			if (client.getSocket().isClosed())
			{
				clientsInfo.remove(client); 
			}
			// if (Socket of ClientInfo is disconnected) (test through reading or writing)
			/*if (false)
			{
				//disconnectedClients.add(client);
				clientsInfo.remove(client); 
			}*/
		}
		lock.unlock(); 
		
		//clientsInfo.remo.removeAll(disconnectedClients);
	}
	
	public void write(Socket s, Object message)
	{ outClient.write(s, message); }
	
	public void readMove()
	{
		
	}
	
	public void translateToClient()
	{
		
	}
	
	public void translateFromClient(Byte messageType)
	{
		
	}
}
