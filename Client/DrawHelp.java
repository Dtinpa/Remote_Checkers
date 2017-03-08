import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class DrawHelp extends DrawUI
{
	private ImageIcon icon;
	private Component parentComponent;
	private String title;
	private int messageType;
	private JScrollPane scrollPane;
	
	DrawHelp()		// calls createElements(), drawElements(), and registerEventHandlers()
	{ super(); }
	
	@Override
	public void createElements()
	{
		parentComponent = frame;
		title = "Checkers Help";
		messageType = JOptionPane.PLAIN_MESSAGE;
		icon = new ImageIcon("res/homer.jpg");
	}
	
	@Override
	public void drawElements()
	{ scrollPane = new JScrollPane(new JLabel(icon)); }
	
	@Override
	public void registerEventHandlers()		// JOptionPane automatically closes on OK click
	{ }
	
	@Override
	public void show()
	{
		JOptionPane.showMessageDialog(
                parentComponent,
                scrollPane,
                title, 
                messageType);
	}
	
	@Override
	public void hide()		// JOptionPane automatically closes on OK click
	{ }
}


