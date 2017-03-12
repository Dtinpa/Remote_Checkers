import javax.swing.JOptionPane;

public class ParseFromServer extends Parser
{
	public void translate(Byte messageType, Object message)
	{
		switch(messageType)
		{
			case 'C':	// Connection
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
			case 'E': // End
				String messageV = (String) message;
				PopUp p = PopUp.getPopUp();
				p.setMessageType(JOptionPane.INFORMATION_MESSAGE);
				p.setMessage(messageV);
				p.setTitle("Game Over");
				p.execute();
				PopUp p1 = PopUp.getPopUp();
				p1.setMessageType(JOptionPane.YES_NO_OPTION);
				p1.setMessage("Would you like to rematch?");
				p1.setTitle("Rematch");
				p1.execute();
				break;
			case 'D': //Disconnect
				Game.getGame().dispose();
				MainScreen.getMainScreen().execute();
				PopUp p2 = PopUp.getPopUp();
				p2.setMessageType(JOptionPane.INFORMATION_MESSAGE);
				p2.setMessage("Opponent has been disconnected from the server.");
				p2.setTitle("Disconnected");
				p2.execute();
				break;
			default:
				break;
		}
	}

	public String translate(Space clicked) 
	{
		return null;
	}
}
