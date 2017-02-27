
public abstract class Output extends IO
{
	private OutConsole console = new OutConsole(); 
	
	public void Write(String message)
	{
		console.Write(message);
	}
	
	public void WriteError(String message)
	{
		console.WriteError(message);
	}
}
