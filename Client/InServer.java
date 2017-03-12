import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class InServer extends Input
{
	private Socket clientSocket; 
	private OutFile logging = OutFile.getInstance();
	
	public InServer(Socket s)
	{
		clientSocket = s; 
	}
	
	public Object read() 
	{
		try 
		{
			ObjectInputStream stream = new ObjectInputStream(clientSocket.getInputStream());
			return stream.readObject(); 
		}
		catch (IOException e) 
		{
			logging.writeError("Could not read from socket.");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		return null; 
	}
}
