import java.net.Socket;
import java.time.LocalDateTime;

public abstract class IO
{
	//For backup if we need to re-make the socket to the client 
	//private ClientInfo client; 
	
	private String configFile = "res/config.txt";
	private String logFile = "logs/log.txt";
	private String errorFile = "logs/error.txt";
	
	protected Socket useSocket; 
	
	protected ClientInfo clientRecepient;  
	protected String dateTimeString = LocalDateTime.now().toString();
	

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
	/*public Socket getSocketToUse()
	{
		//TODO: Get read index here
		int index = 0; 
		
		useSocket = Transcription.getTranscription().getSocket(index); 
		return useSocket; 
	}*/
	
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
	public void setClientReceiptent(ClientInfo client)
	{
		clientRecepient = client; 
	}
}
