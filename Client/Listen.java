
public class Listen
{
	public Object retrieveMessages()
	{
		while (true)
		{
			Byte messageType = Transcription.getTranscription().readByte();		// read just first byte and give that to parser
			Transcription.getTranscription().translateFromServer(messageType);		// to interpret the rest of the message
		}
	}

}
