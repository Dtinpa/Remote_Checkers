import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public abstract class IO
{
	private String configFile = "res/config.txt";
	private String logFile = "logs/log.txt";
	private String errorFile = "logs/error.txt";
	
	protected Socket useSocket; 
	
	protected SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	protected String dateTimeString = dateTime.format(LocalDateTime.now());
	
	//Getters
	public String getConfigFilePath()
	{
		return configFile; 
	}
	public String getLogFilePath()
	{
		return logFile; 
	}
	public String getErrorFilePath()
	{
		return errorFile; 
	}
	public Socket getSocketToUse()
	{
		useSocket = Transcription.getTranscription().GetServerSocket(); 
		return useSocket; 
	}
	
	//Setters
	public void setConfigFilePath(String filePath)
	{
		configFile = filePath; 
	}
	public void setLogFilePath(String filePath)
	{
		logFile = filePath; 
	}
	public void setErrorFilePath(String filePath)
	{
		errorFile = filePath; 
	}
}
