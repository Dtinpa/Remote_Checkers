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
	
	/*public Byte readByte()		// read first byte to determine message type
	{
		try
		{ 
			ObjectInputStream stream = new ObjectInputStream(clientSocket.getInputStream());
			
			Byte b = (Byte)stream.readObject();
			System.out.println(b);
			return b; 
		}
		catch (IOException e)
		{
			logging.writeError(e.getMessage());
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return null;
	}*/
	
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
