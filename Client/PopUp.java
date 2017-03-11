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
	{ 
		if (drawPopUp == null)
		{ drawPopUp = new DrawPopUp();  }
	}
	
	@Override
	public void execute()
	{ 
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
	
	public void setMessageType(int ty) {
		drawPopUp.setMessageType(ty);
	}

	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		
	}
}
