public class Listen
{
	private InClient input;
	private OutFile output; 
	private Parser parser;
	private ClientInfo client;
	
	public Listen(ClientInfo client)
	{
		this.client = client;
		parser = new Parser();
		input = new InClient(client.getSocket());
		output = OutFile.getInstance();
	}
	
	public void retrieveMessages(Integer matchIndex)
	{
		while(true)
		{
			Byte messageType = (Byte)input.read();
			Object message = input.read();
			Object[] retVal = {messageType, message}; 
			if (retVal[0] == null || retVal[1] == null)
			{
				output.write("Client: " + client.getIP() + " - " + client.getPort());
				output.write("Client has disconnected");
				MatchMaking.getInstance().unmatchClients(matchIndex, client);
				return;
			}
			output.write("Client: " + client.getIP() + " - " + client.getPort());
			output.write("Message type: " + (char)(byte) messageType);
			output.write("Message: " + message.toString());
			parser.translate(messageType, message, matchIndex);
		}
	}

}
