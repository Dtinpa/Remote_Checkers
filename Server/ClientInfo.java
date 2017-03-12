import java.net.Socket;

public class ClientInfo
{
	private String ipAddress = "";
	private int portNum = 0;
	private Socket socket = null;
	
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
	
	public void setSocket(Socket socket)
	{
		this.socket = socket;
		ipAddress = socket.getInetAddress().getHostAddress(); 
		portNum = socket.getPort(); 
	}
}
