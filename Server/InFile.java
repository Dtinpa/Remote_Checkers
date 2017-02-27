import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class InFile extends Input
{
	private ObjectInputStream input; 
	private OutFile output = new OutFile(); 
	private File file = new File(""); 
	
	public Object Read(String fileName)
	{
		Object inputObj = new Object(); 
		
		if (!file.mkdirs())
		{
			//Error
			output.WriteError("Could not create parent directory for file"); 
		}

		try
		{
			if (!file.exists())
			{
				output.WriteError("Cannot read file, file does not exist");
			}
			
			input = new ObjectInputStream(new FileInputStream(fileName)); 
			inputObj = input.readObject(); 
		}
		catch(Exception ex)
		{
			output.WriteError(ex.getMessage());
		}
		
		return inputObj; 
	}
}
