import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class DrawHelp extends DrawUI
{
	public JPanel panel;
	public JButton btnClose;
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
		parentComponent = null;
		title = "Checkers Help";
		messageType = JOptionPane.PLAIN_MESSAGE;
		icon = new ImageIcon("res/homer.jpg");
	}
	
	@Override
	public void drawElements()
	{
		scrollPane = new JScrollPane(new JLabel(icon));
	}
	
	@Override
	public void registerEventHandlers()
	{
		
	}
	
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
	public void hide()
	{
	}

}
