import java.awt.event.ActionEvent;


public class Settings extends Screen
{
	private static Settings singleton;
	private DrawSettings drawSettings;
	private OutFile out;
	private InFile in;

	private Settings()
	{
		out = OutFile.getInstance();
		in = InFile.getInstance();
	}
	
	public static Settings getSettings()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Settings(); }
		return singleton;
	}
	
	public boolean validateField()
	{
		String ip = drawSettings.getIP();

		if(ip.isEmpty())	// field left blank
		{
			drawSettings.errorBlank();
			return false;
		}
		return true;
	}

	@Override
	public void execute()
	{ 
		if (drawSettings == null)
		{ drawSettings = new DrawSettings(); }
		String ip = in.read();
		drawSettings.populateIP(ip);
		drawSettings.show();
	}
	
	@Override
	public void dispose()
	{ drawSettings.hide(); }

	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		switch(e.getActionCommand())
		{
			case("Save"):
				if(validateField())
				{
					ConfigData cd = new ConfigData(drawSettings.getIP());
					out.write(cd);
					dispose();
					MainScreen.getMainScreen().execute();
				}
				break;
			case("Cancel"):
				dispose();
				MainScreen.getMainScreen().execute();
				break;
		}
	}
}
