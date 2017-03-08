import java.awt.event.ActionListener;


// Each screen/window subclasses Screen and implements the ActionListener and execute()
	// and dispose() methods
public abstract class Screen implements ActionListener
{
	// moves to this screen/window, including instantiating the singleton if necessary and drawing the UI
	public abstract void execute();
	
	// removes this screen/window from the frame when moving to another one
	public abstract void dispose();
}
