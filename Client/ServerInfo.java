import java.net.Socket;

public class ServerInfo 
{
	private static ServerInfo instance; 
	private String ipAddress;
	private int portNum;
	private Socket socket;
	
	private ServerInfo()
	{
		ipAddress = ""; 
		portNum = 0; 
		socket = null; 
	}
	
	public static ServerInfo getInstance()
	{
		if (instance == null)
		{
			instance = new ServerInfo(); 
		}
		return instance; 
	}
	
	public String getIP()
	{
		return ipAddress; 
	}
	
	public int getPort()
	{
		return portNum; 
	}
	
	public Socket getSocket()
	{
		return socket;
	}
	
	public void setIP(String ip)
	{
		ipAddress = ip; 
	}
	
	public void setPort(int port)
	{
		portNum = port; 
	}
	
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
}
