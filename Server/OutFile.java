import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class OutFile extends Output
{
	private ObjectOutputStream output; 
	private File logFile = new File(getLogFilePath()); 
	private File errorLog = new File(getErrorFilePath()); 
	
	private static OutFile outFile; 
	private OutConsole console = new OutConsole(); 
	
	private OutFile(){}
	
	public static OutFile getInstance()
	{
		if (outFile == null)
		{
			outFile = new OutFile(); 
		}
		return outFile; 
	}
	
	public void write(String message)
	{
		this.write(logFile, message);
	}
	
	public void write(String[] messages)
	{
		this.write(logFile, messages);
	}
	
	public void writeError(String message)
	{
		this.write(errorLog, message); 
	}
	
	private void write(File file, String message)
	{
		String[] messages = {message}; 
		write(file, messages); 
	}
	
	private void write(File file, String[] messages)
	{
		if (!logFile.getParentFile().mkdirs())
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
			console.writeError(ex.getMessage());
		}
	}
}
