import java.net.Socket;
import java.util.*;

public class Transcription
{
	private static Transcription instance;
	
	public IO io;
	public ArrayList<ClientInfo> clientsInfo;

	
	public static Transcription getInstance()
	{
		if (instance == null)
		{
			instance = new Transcription(); 
		}
		return instance; 
	}
	
	public Socket getClientSocket(int index)
	{
		if (clientsInfo != null && clientsInfo.size() >= index)
		{
			return clientsInfo.get(index).getSocket(); 
		}
		return null; 
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
		ArrayList<ClientInfo> disconnectedClients = new ArrayList<ClientInfo>();
		for (int i = 0; i < clientsInfo.size(); i++)
		{
			ClientInfo client = clientsInfo.get(i);
			// if (Socket of ClientInfo is disconnected) (test through reading or writing)
			if (false)
				disconnectedClients.add(client);
		}
		
		clientsInfo.removeAll(disconnectedClients);
	}
}
