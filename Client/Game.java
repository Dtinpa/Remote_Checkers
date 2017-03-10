import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;


public class Game extends Screen implements MouseListener
{
	private static Game singleton;
	
	private DrawGame gameUI; 
	private Transcription t;
	
	private Game()
	{
		//TODO: Check for null....if this does not happen then send message and quit
		t = Transcription.getTranscription();
	}
	
	public static Game getGame()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Game(); }
		return singleton;
	}
	
	public void reDraw(Board board)
	{ gameUI.reDraw(board); }
	
	public void setColor(String color)
	{ gameUI.setColor(color); }
	
	public void displayMessage(String message)
	{ gameUI.displayMessage(message); }
	
	@Override
	public void execute()
	{
		if (gameUI == null)
		{ gameUI = new DrawGame(); }
		gameUI.show();
		
		while(true)
		{
			t.read();
		}
	}
	
	@Override
	public void dispose()
	{ gameUI.hide(); }

	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		switch(e.getActionCommand())
		{
			case("Resign"):
				//TODO: Take this out
				System.out.println("resign");
				break;
			case("Help"):
				Help.getHelp().execute();
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e)	// logic for handling board clicks
	{
		Space clicked = (Space) e.getSource();
		if (clicked.getContents() == Element.VALID || clicked.getContents() == Element.VALIDKING || clicked.getContents() == Element.GREENSPACE)
		{
			t.write('S');
			t.sendMove(clicked); 
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{ }
	
	@Override
	public void mouseReleased(MouseEvent e)
	{ }

	@Override
	public void mouseEntered(MouseEvent e)
	{ }

	@Override
	public void mouseExited(MouseEvent e)
	{ }
}
