import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class InClient extends Input
{
	private Socket clientSocket; 
	private BufferedReader reader; 
	private OutFile logging = OutFile.getInstance();
	
	private ObjectInputStream stream;
	
	public InClient(Socket s)
	{
		try
		{ 
			stream = new ObjectInputStream(new BufferedInputStream(s.getInputStream())); 
		}
		catch (SocketException e)
		{
			//Connection Reset....disconnection 
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Object read() 
	{
		logging.write("Getting socket.");		
		clientSocket = getSocketToUse();
		
		try 
		{
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			return reader.readLine(); 
		}
		catch (IOException e) 
		{
			logging.writeError("Could not read from socket.");
		} 
		
		return null; 
	}
	
	public Byte readByte()		// read first byte to determine message type
	{
		Byte b = null;
		try
		{ b = stream.readByte(); }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}