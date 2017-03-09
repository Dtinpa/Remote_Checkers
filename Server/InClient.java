import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class InClient extends Input
{
	private Socket clientSocket; 
	private OutFile logging;
	
	
	public InClient(Socket s)
	{
		clientSocket = s;
		logging = OutFile.getInstance(); 
	}
	
	public Object read() 
	{
		logging.write("Getting socket.");
		
		clientSocket = getSocketToUse();
		
		try 
		{
			ObjectInputStream stream = new ObjectInputStream(clientSocket.getInputStream());
			return stream.readObject(); 
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
		catch (ClassNotFoundException e)
		{
			logging.writeError(e.getMessage());
		} 
		return null; 
	}
	
	/*public Byte readByte()		// read first byte to determine message type
	{
		logging.write("Getting socket.");
		
		clientSocket = getSocketToUse();
		
		try 
		{
			ObjectInputStream stream = new ObjectInputStream(clientSocket.getInputStream());
			return (Byte)stream.readObject();
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
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		
		return null;
	}*/
}
