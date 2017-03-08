import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class OutClient extends Output
{
	//This is where talking to the server actually takes place....
	//but it is not where the connection happens. The connection happens else where

	private Socket socket;
	private BufferedWriter writer; 
	private OutFile logging = OutFile.getInstance(); 
 
	
	private ObjectOutputStream stream;
	public OutClient(Socket s)
	{
		try
		{ 
			stream = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream())); 
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void write(String message)
	{
		logging.write("Getting socket.");		
		socket = getSocketToUse(); 
		
		write(socket, message); 
	}
	
	public void write(Socket socket, String message)
	{
		logging.write("Wrote to server.");
		if (socket == null) return; 
		try 
		{
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			writer.write(message);
			writer.flush();
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
			write(socket, messages[i]); 
		}
	}
	
	public void writeError(String message)
	{
		logging.writeError(message);
	}
}