import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;


public class InServer extends Input
{
	private Socket clientSocket; 
	//private BufferedReader reader; 
	private OutFile logging = OutFile.getInstance();
	
	//private BufferedInputStream stream;
	private BufferedReader stream; 
	
	public InServer(Socket s)
	{
		clientSocket = s; 
	}
	
	public Byte readByte()		// read first byte to determine message type
	{
		Byte b = null;
		stream = null; 
		try
		{ 
			//stream = new BufferedInputStream(clientSocket.getInputStream());
			stream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			b = (byte) stream.read(); 
			System.out.println(b);
		}
		catch (IOException e)
		{
			logging.writeError(e.getMessage());
		}
		return b;
	}
	
	public Object read() 
	{		
		stream = null; 
		
		try 
		{
			//stream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			//return stream.readObject();
			
			//Does this have to be like this?
			//stream = new BufferedInputStream(clientSocket.getInputStream()); 
			stream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			return stream.readLine();
			/*int available = stream..available();
			byte[] array = new byte[available]; 
			stream.read(array, 0, available); 
			return Arrays.toString(array);  */
		}
		catch (IOException e) 
		{
			logging.writeError("Could not read from socket.");
		} 
		return null; 
	}
	
	/*ObjectInputStream stream;
	
	InServer(Socket s)
	{
		try
		{ stream = new ObjectInputStream(new BufferedInputStream(s.getInputStream())); }
		catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
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
	
	public Object read()		// read rest of message
	{
		Object o = null;
		try
		{ o = stream.readObject(); }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}*/
}
