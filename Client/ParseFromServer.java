
public class ParseFromServer extends Parser
{
	public void translate(Byte messageType, Object message)
	{
		//TODO: MOVE THIS LOGIC ELSE WHERE THIS IS MEANT TO PARSE ONLY NOT 
		//EXECUTE THESE THINGS
		
		System.out.println(messageType);
		System.out.println(message);
		
		switch(messageType)
		{
			case 'C':
				String color = (String) message; 
				Game.getGame().setColor(color);
				break;
			case 'B': // Board
				Board newBoard = (Board) message; 
				Game.getGame().reDraw(newBoard);
				break;
			case 'M': // String Message
				Game.getGame().displayMessage((String)message);
				break;
			case 'V': // Victory
				String messageV = (String) message;
				PopUp.getPopUp().setMessage(messageV);
				PopUp.getPopUp().setTitle("Victory");
				PopUp.getPopUp().execute();
			case 'D': // Defeat
				String messageD = (String) message;
				PopUp.getPopUp().setMessage(messageD);
				PopUp.getPopUp().setTitle("Defeat");
				PopUp.getPopUp().execute();
			default:
				break;
		}
	}

	public String translate(Space clicked) 
	{
		return null;
	}
}
