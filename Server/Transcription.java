import java.net.Socket;
import java.util.*;

public class Transcription
{
	private OutClient outClient;
	private ArrayList<ClientInfo> clientsInfo;
	private Connect connect;
	private static Transcription singleton;

	private Transcription()
	{
		outClient = new OutClient(); 
	}
	
	public static Transcription getInstance()
	{
		if(singleton == null)
		{ 
			singleton = new Transcription(); 
		}
		return singleton;
	}
	
	public void openToConnections()
	{
		clientsInfo = new ArrayList<ClientInfo>(); 
		
		connect = new Connect();
		connect.acceptConnections(); 
	}
	
	public void addClientSocket(Socket clientSocket)
	{
		ClientInfo client = new ClientInfo(); 
		client.setSocket(clientSocket);
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
	
	public void write(Socket s, Object message)
	{ outClient.write(s, message); }
}
