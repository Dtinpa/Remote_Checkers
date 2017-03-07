import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class DrawPopUp extends DrawUI
{
	private String message;
	private Component parentComponent;
	private String title;
	private int messageType;
	private JScrollPane scrollPane;
	
	DrawPopUp()		// calls createElements(), drawElements(), and registerEventHandlers()
	{ super(); }
	
	@Override
	public void createElements()
	{
		parentComponent = frame;
		messageType = JOptionPane.INFORMATION_MESSAGE;
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public void setMessage(String m) {
		message = m;
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
		JOptionPane.showMessageDialog(
                parentComponent,
                message,
                title, 
                messageType);
	}
	
	@Override
	public void hide()		// JOptionPane automatically closes on OK click
	{ }
}


