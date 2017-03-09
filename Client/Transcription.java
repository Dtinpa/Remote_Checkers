import java.net.Socket;


public class Transcription
{
	private Connect connect;
	private Listen listen;
	private Send send;
	//private ParseToServer parseToServer;
	//private ParseFromServer parseFromServer;
	private Socket socket;
	private OutFile logging; 
	private static Transcription singleton;
	
	private static boolean serverCheck; 
	
	private Transcription()
	{
		serverCheck = false; 
		connect = new Connect();
		socket = connect.connectSocket(); 
		logging = OutFile.getInstance(); 

		if (socket == null)
		{
			//Error out...no server available 
			logging.writeError("No server to connect to.");
			return; 
		}
		
		serverCheck = true; 
		
		send = Send.getInstance(socket); 
		
		listen = Listen.getInstance(socket);
		Thread listenThread = new Thread()
		{
			public void run()
			{
				listen.retrieveMessages(); 
			}
		};  
		listenThread.start();
	}
	
	public static Transcription getTranscription()	// implements singleton
	{
		if(singleton == null)
		{ 
			singleton = new Transcription();
		}
		if (!serverCheck) singleton = null; 
			
		return singleton;  
	}
	
	public Object read()
	{ return (String) listen.getNextMessage()[1]; }
	
	public Byte readByte()
	{ return (Byte) listen.getNextMessage()[0]; }
	
	public void write(String message)
	{ send.sendMessage(message); }
	
	public void sendMove(Space clicked)
	{ send.sendMove(clicked); }
	
	public void listen()
	{ listen.retrieveMessages(); }
}
