
public class OutConsole extends Output
{
	public void write(String message)
	{
		System.out.println(dateTimeString + ": " + message);
	}
	
	public void write(String[] messages)
	{
		for(int i = 0; i < messages.length; i++)
		{
			System.out.println(dateTimeString + ": " + messages[i]);
		}
	}
	
	public void writeError(String message)
	{
		System.out.println("[Error] - " + dateTimeString + ": " + message);
	}
}