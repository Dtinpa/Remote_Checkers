import java.net.Socket;
import java.util.*;


public class Transcription
{
	public InClient inClient;
	public OutClient outClient;
	public ArrayList<Socket> socketIndicies; 
	public HashMap<Socket, ClientInfo> clientsInfo;
	public Connect connect;
	public Listen listen;
	public Send send;
	public ParseToClient parseToClient;
	public ParseFromClient parseFromClient;
	private Socket socket;
	private static Transcription singleton;

	private Transcription()
	{

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
		clientsInfo = new HashMap<Socket, ClientInfo>();
		
		connect = new Connect();
		connect.acceptConnections(); 
		//socket = connect.getSocket(); 
		
		/*outClient = new OutClient(socket);
		outClient.write("Sending to client");
		inClient = new InClient(socket);*/
		
		/*Thread thread = new Thread()
		{
			public void run()
			{
				inClient = new InClient(socket);
			}
		}; 
		thread.start();*/
		
		//listen = new Listen();
		//send = new Send();
		//parseToClient = new ParseToClient();
		//parseFromClient = new ParseFromClient();
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
		clientsInfo.put(clientSocket, client); 
	}
	
	public ClientInfo getClientForMatching()
	{
		//ClientInfo client = clientsInfo.remove(0);
		//return client;
		return null;
	}
	
	public int getClientsCount()
	{
		//return clientsInfo.size();
		return 0; 
	}
	
	public void removeDisconnectedClients()
	{
		/*ArrayList<ClientInfo> disconnectedClients = new ArrayList<ClientInfo>();
		for (int i = 0; i < clientsInfo.size(); i++)
		{
			ClientInfo client = clientsInfo.get(i);
			// if (Socket of ClientInfo is disconnected) (test through reading or writing)
			if (false)
				disconnectedClients.add(client);
		}
		
		clientsInfo.removeAll(disconnectedClients);*/
	}
	
	public Object read()
	{ return inClient.read(); }
	
	public Byte readByte()
	{ return inClient.readByte(); }
	
	public void write(String message)
	{ outClient.write(message); }
	
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
