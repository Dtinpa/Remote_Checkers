import java.awt.event.ActionEvent;


public class PopUp extends Screen
{
	public static PopUp singleton;
	public DrawPopUp drawPopUp;

	public static PopUp getPopUp()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new PopUp(); }
		return singleton;
	}
	
	PopUp()
	{ }
	
	@Override
	public void execute()
	{ 
		if (drawPopUp == null)
		{ drawPopUp = new DrawPopUp();  }
		drawPopUp.show();
	}
	
	@Override
	public void dispose()
	{ drawPopUp.hide(); }
	
	public void setTitle(String t) {
		drawPopUp.setTitle(t);
	}
	
	public void setMessage(String m) {
		drawPopUp.setMessage(m);
	}

	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		
	}
}
