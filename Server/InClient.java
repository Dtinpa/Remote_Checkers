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
		clientSocket = s; 
	}
	
	public String read() 
	{
		logging.write("Getting socket.");
		stream = null; 
		
		clientSocket = getSocketToUse();
		
		try 
		{
			stream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			return reader.readLine(); 
		}
		catch (SocketException e)
		{
			//Connection Reset....disconnection 
			logging.writeError("Client disconnected");
		}
		catch (IOException e) 
		{
			logging.writeError("Could not read line from socket.");
		} 
		
		return null; 
	}
	
	public Byte readByte()		// read first byte to determine message type
	{
		/*Byte b = null;
		try
		{ b = stream.readByte(); }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;*/
		
		logging.write("Getting socket.");
		stream = null; 
		
		clientSocket = getSocketToUse();
		
		try 
		{
			stream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			return (byte)reader.read(); 
		}
		catch (SocketException e)
		{
			//Connection Reset....disconnection 
			logging.writeError("Client disconnected");
		}
		catch (IOException e) 
		{
			logging.writeError("Could not read byte from socket.");
		} 
		
		return null;
	}
}
