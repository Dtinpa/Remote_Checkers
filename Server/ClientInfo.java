
public class ClientInfo
{
	private String ipAddress = "";
	private int portNum = 0;
	
	public String GetIP()
	{
		return ipAddress; 
	}
	
	public int GetPort()
	{
		return portNum; 
	}
	
	public void SetIP(String ip)
	{
		ipAddress = ip; 
	}
	
	public void SetPort(int port)
	{
		portNum = port; 
	}
}
