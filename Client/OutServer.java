import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;


public class OutServer extends Output
{
	//This is where talking to the server actually takes place....
	//but it is not where the connection happens. The connection happens else where
 
	private OutFile logging;
	private Socket socket; 
	
	public OutServer(Socket s)
	{
		socket = s; 
		logging = OutFile.getInstance(); 
	}
	
	/*public void write(Object message)
	{
		logging.write("Getting socket.");	
		
		write(socket, message); 
	}*/
	
	public void write(Object message)
	{
		logging.write("Wrote to server.");
		if (socket == null) return; 
		try 
		{
			ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
			stream.writeObject(message);
			stream.flush();
		}
		catch (SocketException e)
		{
			//Connection Reset....disconnection from server 
		}
		catch (IOException e) 
		{
			logging.write(e.getMessage());
			return; 
		} 
		
		logging.write("Sucessfully wrote to client.");
	}
	
	public void write(String[] messages)
	{
		logging.write("Getting socket.");		
		socket = getSocketToUse(); 
		
		for (int i = 0; i < messages.length; i++)
		{
			write(messages[i]); 
		}
	}
	
	/*public void write(byte b)
	{
		logging.write("Wrote to server.");
		if (socket == null) return; 
		try 
		{
			ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
			stream.writeObject(b);
			stream.flush(); 
			
		}
		catch (SocketException e)
		{
			//Connection Reset....disconnection from server 
		}
		catch (IOException e) 
		{
			logging.write(e.getMessage());
			return; 
		} 
		
		logging.write("Sucessfully wrote to client.");
	}*/
	
	public void writeError(String message)
	{
		logging.writeError(message);
	}
}
