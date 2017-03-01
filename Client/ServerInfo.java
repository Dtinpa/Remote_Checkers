import java.net.Socket;

public class ServerInfo 
{
	private String ipAddress = "";
	private int portNum = 0;
	private Socket socket = null; 
	
	public String GetIP()
	{
		return ipAddress; 
	}
	
	public int GetPort()
	{
		return portNum; 
	}
	
	public Socket GetSocket()
	{
		return socket;
	}
	
	public void SetIP(String ip)
	{
		ipAddress = ip; 
	}
	
	public void SetPort(int port)
	{
		portNum = port; 
	}
	
	public void SetSocket(Socket socket)
	{
		this.socket = socket;
	}
}
