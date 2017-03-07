import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class OutFile extends Output
{
	private BufferedWriter outputWriter;
	private ObjectOutputStream outputStream; 
	private File logFile = new File(getLogFilePath()); 
	private File errorLog = new File(getErrorFilePath()); 
	private File configFile = new File(getConfigFilePath()); 
	
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
	
	public void write(ConfigData configuration) 
	{ 
		this.write(configFile, configuration); 
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
	
	private void write(File file, Object messages)
	{
		if (!file.getParentFile().mkdirs())
		{
			//Error
		}

		try
		{
			if (!file.exists())
			{
				//Make file 
				file.createNewFile(); 
			}
			
			if (messages instanceof String || messages instanceof String[])
			{
				writeString(file, (String[])messages);
			}
			else if (messages instanceof ConfigData)
			{
				writeConfig(file, messages);
			}
			else
			{
				writeObject(file, messages); 
			}
			
		}
		catch(Exception ex)
		{
			console.writeError(ex.getMessage());
		}
	}
			
	private void writeString(File file, String[] messages)
	{
		try {
			outputWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
			
			for(int i = 0; i < messages.length; i++)
			{
				outputWriter.write(dateTimeString + ": " + messages[i]);
				outputWriter.newLine();
			}
			outputWriter.flush();
			outputWriter.close();
		}
		catch (IOException ex)
		{
			console.writeError(ex.getMessage());
		}

	}
	
	private void writeConfig(File file, Object messages)
	{
		try
		{
			outputWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), false));
			String configIP = ((ConfigData) messages).getIP();
			outputWriter.write(configIP);
			outputWriter.flush();
			outputWriter.close();
		}
		catch (IOException ex)
		{
			console.writeError(ex.getMessage());
		}
	}
	
	private void writeObject(File file, Object messages) throws FileNotFoundException, IOException
	{
		outputStream = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath())); 
		
		outputStream.writeObject(dateTimeString + ": " + messages);
	}
}
