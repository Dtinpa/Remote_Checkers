
public class Transcription
{
	public InServer inServer;
	public OutServer outServer;
	public ServerInfo serverInfo;
	public Connect connect;
	public Listen listen;
	public Send send;
	public ParseToServer parseToServer;
	public ParseFromServer parseFromServer;
	
	private static Transcription singleton;
	
	private Transcription()
	{
		//inServer = new InServer();
		//outServer = new OutServer();	// these are done in Game.Connect, when we have a socket
		serverInfo = new ServerInfo();
		connect = new Connect();
		listen = new Listen();
		send = new Send();
		parseToServer = new ParseToServer();
		parseFromServer = new ParseFromServer();
	}
	
	public static Transcription getTranscription()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Transcription(); }
		return singleton;
	}
	
	public Object read()
	{ return inServer.read(); }
	
	public Byte readByte()
	{ return inServer.readByte(); }
	
	public void write(String message)
	{ outServer.write(message); }
	
	public void sendMove(Space clicked)
	{ send.sendMove(clicked); }
	
	public void translateToServer(Space clicked)
	{ parseToServer.translate(clicked); }
	
	public void translateFromServer(Byte messageType)
	{ parseFromServer.translate(messageType); }
}
