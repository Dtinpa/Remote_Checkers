import java.net.Socket;
import java.util.ArrayList;

public class Transcription
{
	private static Transcription instance;
	
	public IO io;
	public ArrayList<ClientInfo> clientsInfo;

	
	public static Transcription GetInstance()
	{
		if (instance == null)
		{
			instance = new Transcription(); 
		}
		return instance; 
	}
	
	public Socket GetClientSocket(int index)
	{
		if (clientsInfo != null && clientsInfo.size() >= index)
		{
			return clientsInfo.get(index).GetSocket(); 
		}
		return null; 
	}
}
