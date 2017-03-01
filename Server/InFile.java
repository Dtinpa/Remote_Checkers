import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


//SHOULD NOT NEED THIS CLASS?
public class InFile extends Input
{
	private static InFile instance; 
	private ObjectInputStream input; 
	private OutFile output = OutFile.GetInstance(); 
	private File file = new File(getLogFilePath()); 
	
	private InFile(){}
	
	public static InFile GetInstance()
	{
		if(instance == null)
		{
			instance = new InFile(); 
		}
		return instance; 
	}
	
	public Object read()
	{
		Object inputObj = new Object(); 
		
		if (!file.mkdirs())
		{
			//Error
			output.writeError("Could not create parent directory for file"); 
		}

		try
		{
			if (!file.exists())
			{
				output.writeError("Cannot read file, file does not exist");
			}
			
			input = new ObjectInputStream(new FileInputStream(file.getName())); 
			inputObj = input.readObject(); 
		}
		catch(Exception ex)
		{
			output.writeError(ex.getMessage());
		}
		
		return inputObj; 
	}
}
