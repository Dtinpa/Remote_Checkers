import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public abstract class IO
{
	private String configFile = "res/config.txt";
	private String logFile = "logs/log.txt";
	private String errorFile = "logs/error.txt";
	
	protected ClientInfo clientRecepient; 
	protected SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	protected String dateTimeString = dateTime.format(LocalDateTime.now());
	

	//Getters
	public String GetConfigFilePath()
	{
		return configFile; 
	}
	public String GetLogFilePath()
	{
		return logFile; 
	}
	public String GetErrorFilePath()
	{
		return errorFile; 
	}
	
	//Setters
	public void SetConfigFilePath(String filePath)
	{
		configFile = filePath; 
	}
	public void SetLogFilePath(String filePath)
	{
		logFile = filePath; 
	}
	public void SetErrorFilePath(String filePath)
	{
		errorFile = filePath; 
	}
	public void SetClientReceiptent(ClientInfo client)
	{
		clientRecepient = client; 
	}
}
