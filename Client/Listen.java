
public class Listen
{
	public Object retrieveMessages()
	{
		while (true)
		{
			Byte messageType = Transcription.getTranscription().inServer.readByte();	// read just first byte and give that to parser
			Transcription.getTranscription().parseFromServer.translate(messageType);		// to interpret the rest of the message
		}
	}

}
