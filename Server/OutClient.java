
public class OutClient extends Output
{
	//This is where talking to the server actually takes place....
	//but it is not where the connection happens. The connection happens else where
	
	
	private OutFile logging = new OutFile(); 
	
	public void Write(String message)
	{
		logging.Write("Wrote to server.");
	}
	
	public void Write(String[] messages)
	{
		logging.Write("Wrote to server.");
	}
	
	public void WriteError(String message)
	{
		logging.WriteError(message);
	}
}
