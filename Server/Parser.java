
public class Parser
{
	public void translate(Byte messageType, Object message, Integer matchIndex)
	{
		switch(messageType)
		{
			case 'C':
				String color = (String) message;
				break;
			case 'B': // Board
				Board newBoard = (Board) message;
				break;
			case 'M': // String Message
				String messageStr = (String) message;
				break;
			case 'V': // Victory
				String messageV = (String) message;
				break;
			case 'D': // Defeat
				String messageD = (String) message;
				break;
		}
	}

}
