import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class OutServer extends Output
{
	ObjectOutputStream stream;
	
	OutServer(Socket s)
	{
		try
		{ stream = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream())); }
		catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void write(String message)	// message is "xy" position of click
	{
		try
		{ stream.writeUTF(message); }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
