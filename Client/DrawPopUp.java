import java.awt.Component;
import javax.swing.JOptionPane;


public class DrawPopUp extends DrawUI
{
	private String message;
	private Component parentComponent;
	private String title;
	private int messageType;
	
	DrawPopUp()		// calls createElements(), drawElements(), and registerEventHandlers()
	{ super(); }
	
	@Override
	public void createElements()
	{
		message = "";
		title = "";
		parentComponent = frame;
		messageType = JOptionPane.INFORMATION_MESSAGE;
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public void setMessage(String m) {
		message = m;
	}
	
	public void setMessageType(int ty) {
		messageType = ty;
	}
	
	@Override
	public void drawElements()
	{  }
	
	@Override
	public void registerEventHandlers()		// JOptionPane automatically closes on OK click
	{ }
	
	@Override
	public void show()
	{
		if(messageType == JOptionPane.INFORMATION_MESSAGE) {
			JOptionPane.showMessageDialog(
                parentComponent,
                message,
                title, 
                messageType);
		}
		else if(messageType == JOptionPane.YES_NO_OPTION) {
			int result = JOptionPane.showConfirmDialog(parentComponent, message, title, messageType);
			if(result == JOptionPane.YES_OPTION && title == "Rematch") {
				Transcription.getInstance().write((byte)'Y');
				Transcription.getInstance().write("");
			}
			else if(result == JOptionPane.NO_OPTION && title == "Rematch") {
				Transcription.getInstance().closeSocket();
				Game.getGame().dispose();
				MainScreen.getMainScreen().execute();
			}
			else if(result == JOptionPane.YES_OPTION && title == "Quit") {
				System.exit(0);
			}
			else if(result == JOptionPane.YES_OPTION && title == "Resign") {
				Transcription.getInstance().closeSocket();
				Game.getGame().dispose();
				MainScreen.getMainScreen().execute();
			}
			else {
				//return to Main Screen
			}
		}
		
	}
	
	@Override
	public void hide()		// JOptionPane automatically closes on OK click
	{ }
}


