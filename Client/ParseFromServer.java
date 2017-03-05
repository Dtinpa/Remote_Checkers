
public class ParseFromServer extends Parser
{
	public void translate(Byte messageType)
	{
		switch(messageType)
		{
			case 'C':
				String color = (String) Transcription.getTranscription().read();
				Game.getGame().setColor(color);
				break;
			case 'B': // Board
				Board newBoard = (Board) Transcription.getTranscription().read();
				Game.getGame().reDraw(newBoard);
				break;
			case 'M': // String Message
				String message = (String) Transcription.getTranscription().read();
				Game.getGame().displayMessage(message);
				break;
			default:
				break;
		}
	}
}
