import java.awt.event.ActionEvent;


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
				dispose();
				//Game.getGame().connect();
				Game.getGame().execute();
				
				break;
			case("Settings"):
				dispose();
				Settings.getSettings().execute();
				break;
			case("Help"):
				Help.getHelp().execute();
				break;
			case("Quit"):
				System.exit(0);
				break;
		}
	}
}