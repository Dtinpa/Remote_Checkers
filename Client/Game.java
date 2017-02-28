import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;


public class Game extends Screen implements MouseListener
{
	private static Game singleton;
	
	private DrawGame gameUI;
	private Board playerPos;
	private Transcription t;
	
	private Game()
	{
		playerPos = new Board();
		t = Transcription.getTranscription();
	}
	
	public static Game getGame()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Game(); }
		return singleton;
	}
	
	public void connect()
	{
	/*	Socket socket = t.connect.connectSocket();
		t.outServer = new OutServer(socket);
		t.inServer = new InServer(socket);*/
	}
	
	public void reDraw(Board board)
	{ gameUI.reDraw(board); }
	
	public void displayMessage(String message)
	{ gameUI.displayMessage(message); }
	
	@Override
	public void execute()
	{
		if (gameUI == null)
		{ gameUI = new DrawGame(); }
		gameUI.show();
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
		{ t.sendMove(clicked); }
		
		/*System.out.println(clicked.getContents().toString());
		playerPos.setAt(clicked.getRow(), clicked.getCol(), Element.GREENSPACE);
		gameUI.reDraw(playerPos);*/
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
