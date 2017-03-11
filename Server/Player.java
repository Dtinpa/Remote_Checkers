import java.net.Socket;

public class Player {

	final static int RED = 1;
	final static int BLUE = 2;
	
	private int color;
	private Socket socket;
	
	public int getColor()
	{
		return color;
	}
	
	public void setColor(int color)
	{
		this.color = color;
	}
	
	public Socket getSocket()
	{
		return socket;
	}
	
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
}
