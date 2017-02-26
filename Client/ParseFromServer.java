
public class ParseFromServer extends Parser
{
	public void translate(Byte messageType)
	{
		switch(messageType)
		{
			case 'B': // Board
				Board newBoard = (Board) Transcription.getTranscription().inServer.read();
				Game.getGame().gameUI.reDraw(newBoard);
				break;
			case 'M': // String Message
				String message = (String) Transcription.getTranscription().inServer.read();
				Game.getGame().gameUI.displayMessage(message);
				break;
			default:
				break;
		}
	}
}
