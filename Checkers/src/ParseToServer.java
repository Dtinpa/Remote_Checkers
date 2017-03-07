
public class ParseToServer extends Parser
{
	public void translate(Space clicked)
	{
		String xy = Integer.toString(clicked.getRow()) + Integer.toString(clicked.getCol());
		Transcription.getTranscription().write(xy);
	}
}
