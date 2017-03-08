import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;


public abstract class DrawUI
{
	public static JFrame frame;							// frame is treated as a singleton
	public Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 50);
	public Font BUTTON_FONT = new Font("Tahoma", Font.BOLD, 30);
	public Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 22); 
	public Dimension buttonSize = new Dimension(300, 70);
	public Dimension labelSize = new Dimension(300, 50);

	public abstract void createElements();				// initializes each GUI element
	public abstract void drawElements();				// places GUI elements
	public abstract void registerEventHandlers();		// adds click handlers to buttons
	public abstract void show();
	public abstract void hide();

	DrawUI()					// all subclasses call this super constructor
	{
		getFrame();
		createElements();
		drawElements();
		registerEventHandlers();
	}
	
	public static void getFrame()		// frame is treated as a singleton
	{
		if(frame == null)
		{
			frame = new JFrame("Remote_Checkers");
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setSize(1000, 1000);
	    	frame.setResizable(false);
	    	frame.setVisible(true);
		}
	}


}
