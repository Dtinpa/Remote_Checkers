
public abstract class Input extends IO
{
	public Object Read(String fileName)
	{
		InFile input = new InFile(); 
		return input.Read(fileName); 
	}
}
