
public class Parser
{
	public void translate(Byte messageType)
	{
		switch(messageType)
		{
			case 'C':
				String color = (String) Transcription.getTranscription().read();
				break;
			case 'B': // Board
				Board newBoard = (Board) Transcription.getTranscription().read();
				
				break;
			case 'M': // String Message
				String message = (String) Transcription.getTranscription().read();
				
				break;
			case 'V': // Victory
				String messageV = (String) Transcription.getTranscription().read();
				
			case 'D': // Defeat
				String messageD = (String) Transcription.getTranscription().read();
							default:
				break;
		}
	}

}
