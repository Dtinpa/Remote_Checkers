import java.awt.event.ActionEvent;


public class Help extends Screen
{
	public static Help singleton;
	public DrawHelp drawHelp;

	public static Help getHelp()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Help(); }
		return singleton;
	}
	
	Help()
	{ }
	
	@Override
	public void execute()
	{ 
		if (drawHelp == null)
		{ drawHelp = new DrawHelp();  }
		drawHelp.show();
	}
	
	@Override
	public void dispose()
	{ drawHelp.hide(); }

	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		
	}
}
