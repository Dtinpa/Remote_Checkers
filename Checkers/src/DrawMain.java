import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


// Responsible for creating, showing, and hiding the MainScreen GUI.
public class DrawMain extends DrawUI
{
	private JPanel panel;			// main panel of content
	private JLabel lblTitle;
	private JButton btnGame;		// connects to server and moves to Game screen
	private JButton btnSettings;	// moves to Settings screen
	private JButton btnHelp;		// launches Help window
	private JButton btnQuit;		// exits application
	
	DrawMain()		// calls createElements(), drawElements(), and registerEventHandlers()
	{ super(); }
	
	@Override
	public void createElements()		// initializes each GUI element
	{
		panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

		lblTitle = new JLabel("Remote_Checkers");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(TITLE_FONT);
		
		btnGame = new JButton("Start Game");
		btnGame.setActionCommand("Start Game");
		btnGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGame.setFont(BUTTON_FONT);
		btnGame.setToolTipText("Connect to Server");
		btnGame.setMinimumSize(buttonSize);
		btnGame.setMaximumSize(buttonSize);
		btnGame.setPreferredSize(buttonSize);
		
		btnSettings = new JButton("Settings");
		btnSettings.setActionCommand("Settings");
		btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSettings.setFont(BUTTON_FONT);
		btnSettings.setToolTipText("View and Edit Connection Settings");
		btnSettings.setMinimumSize(buttonSize);
		btnSettings.setMaximumSize(buttonSize);
		btnSettings.setPreferredSize(buttonSize);
		
		btnHelp = new JButton("Help");
		btnHelp.setActionCommand("Help");
		btnHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHelp.setFont(BUTTON_FONT);
		btnHelp.setToolTipText("View Rules and Usage Guide");
		btnHelp.setMinimumSize(buttonSize);
		btnHelp.setMaximumSize(buttonSize);
		btnHelp.setPreferredSize(buttonSize);
		
		btnQuit = new JButton("Quit");
		btnQuit.setActionCommand("Quit");
		btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuit.setFont(BUTTON_FONT);
		btnQuit.setToolTipText("Exit Remote_Checkers");
		btnQuit.setMinimumSize(buttonSize);
		btnQuit.setMaximumSize(buttonSize);
		btnQuit.setPreferredSize(buttonSize);
	}
	
	@Override
	public void drawElements()			// places GUI elements
	{
		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		panel.add(lblTitle);
		panel.add(Box.createRigidArea(new Dimension(0, 120)));
		panel.add(btnGame);
		panel.add(Box.createRigidArea(new Dimension(0, 60)));
		panel.add(btnSettings);
		panel.add(Box.createRigidArea(new Dimension(0, 60)));
		panel.add(btnHelp);
		panel.add(Box.createRigidArea(new Dimension(0, 60)));
		panel.add(btnQuit);
		frame.add(panel);
	}
	
	@Override
	public void registerEventHandlers()		// adds click handlers to buttons
	{
		btnGame.addActionListener(MainScreen.getMainScreen());
		btnSettings.addActionListener(MainScreen.getMainScreen());
		btnHelp.addActionListener(MainScreen.getMainScreen());
		btnQuit.addActionListener(MainScreen.getMainScreen());
	}
	
	@Override
	public void show()
	{
		frame.add(panel);
        frame.revalidate();
        frame.repaint();
	}
	
	@Override
	public void hide()
	{ frame.remove(panel); }
}
