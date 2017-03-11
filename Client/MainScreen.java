import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;


public class MainScreen extends Screen
{
	private static MainScreen singleton;
	private DrawMain drawMain;
	
	private MainScreen()
	{ }
	
	public static MainScreen getMainScreen()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new MainScreen(); }
		return singleton;
	}

	@Override
	public void execute()
	{
		if (drawMain == null)
		{ drawMain = new DrawMain(); }
		//resets the singleton so a new connection can be established.
		Transcription.clear();
		drawMain.show();
	}
	
	@Override
	public void dispose()
	{ drawMain.hide(); }
	
	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		switch(e.getActionCommand())
		{
			case("Start Game"):
				Game g = Game.getGame();
				//Game.getGame().connect();
				if(g == null) {
					PopUp p = PopUp.getPopUp();
					p.setMessageType(JOptionPane.INFORMATION_MESSAGE);
					p.setMessage("Cannot connect to server.  Check configuration settings in the Settings Menu.");
					p.setTitle("Connection Error");
					p.execute();
				}
				else {
					dispose();
					g.execute();
				}
				
				break;
			case("Settings"):
				dispose();
				Settings.getSettings().execute();
				break;
			case("Help"):
				Help.getHelp().execute();
				break;
			case("Quit"):
				PopUp p = PopUp.getPopUp();
				p.setMessageType(JOptionPane.YES_NO_OPTION);
				p.setMessage("Do you wish to close the application?.");
				p.setTitle("Quit");
				p.execute();
				break;
		}
	}
}
