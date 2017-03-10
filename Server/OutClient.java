import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OutClient extends Output
{
	//This is where talking to the server actually takes place....
	//but it is not where the connection happens. The connection happens else where

	private Socket socket; 
	private OutFile logging;
 
	public OutClient()
	{
		logging = OutFile.getInstance();
	}
	
	public OutClient(Socket s)
	{
		socket = s; 
		logging = OutFile.getInstance(); 
	}

	/*public void write(Object message)
	{
		logging.write("Getting socket.");	
		
		write(socket, message); 
	}*/
	
	public void write(String[] messages)
	{
		logging.write("Getting socket.");	
		
		for (int i = 0; i < messages.length; i++)
		{
			write(messages[i]); 
		}
	}
	
	public void write(Object message)
	{
		write(socket, message); 
	}
	
	public void write(Socket s, Object message)
	{
		logging.write("Wrote to server.");
		if (s == null) return; 
		try 
		{
			ObjectOutputStream stream = new ObjectOutputStream(s.getOutputStream());
			stream.writeObject(message);
			stream.flush(); 
		}
		catch (IOException e) 
		{
			logging.write(e.getMessage());
			return; 
		} 
		
		logging.write("Sucessfully wrote to client.");
	}
	
	public void writeError(String message)
	{
		logging.writeError(message);
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
		catch (IOException e) 
		{
			logging.write(e.getMessage());
			return; 
		} 
		
		logging.write("Sucessfully wrote to client.");
	}*/
}
