import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class OutFile extends Output
{
	private ObjectOutputStream output; 
	private File logFile = new File(""); 
	private File errorLog = new File("");
	
	private OutConsole console = new OutConsole(); 
	
	public void Write(String message)
	{
		this.Write(logFile, message);
		/*if (!logFile.mkdirs())
		{
			//Error
		}

		try
		{
			if (!logFile.exists())
			{
				//Make file 
				logFile.createNewFile(); 
			}
			
			output = new ObjectOutputStream(new FileOutputStream(logFile.getAbsolutePath())); 
			
			output.writeChars(dateTimeString + ": " + message);			
		}
		catch(NoSuchElementException ex)
		{
			
		}
		catch(FileNotFoundException ex)
		{
			
		}
		catch(IOException ex)
		{
			
		}*/
	}
	
	public void WriteError(String message)
	{
		this.Write(errorLog, message); 
	}
	
	private void Write(File file, String message)
	{
		String[] messages = {message}; 
		Write(file, messages); 
	}
	
	private void Write(File file, String[] messages)
	{
		if (!logFile.mkdirs())
		{
			//Error
		}

		try
		{
			if (!logFile.exists())
			{
				//Make file 
				logFile.createNewFile(); 
			}
			
			output = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath())); 
			
			output.writeObject(dateTimeString + ": " + messages);
		}
		catch(Exception ex)
		{
			console.WriteError(ex.getMessage());
		}
	}
}
