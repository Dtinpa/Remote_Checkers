import java.awt.Component;
import java.awt.Dimension;

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
		try {
			icon = new ImageIcon(Client.class.getResource("/help.png"));
		}
		catch(NullPointerException e)	// load images - command line
		{
			icon = new ImageIcon("../res/help.png");
		}
	}
	
	@Override
	public void drawElements()
	{ 
		JLabel l = new JLabel(icon);
		scrollPane = new JScrollPane(l);
		scrollPane.setPreferredSize(new Dimension(icon.getIconWidth(), 450));
	}
	
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


