import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InClient extends Input
{
	private Socket clientSocket; 
	private BufferedReader reader; 
	private OutFile logging = OutFile.getInstance();
	
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
}
