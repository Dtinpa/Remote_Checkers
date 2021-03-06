import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Space extends JPanel
{
	private static final long serialVersionUID = 1L;
	public static ImageIcon WHITE_SQUARE;			// board images shared across all instances of the Space class
	public static ImageIcon BLACK_SQUARE;
	public static ImageIcon GREEN_SQUARE;
	public static ImageIcon RED_MAN;
	public static ImageIcon BLUE_MAN;
	public static ImageIcon RED_KING;
	public static ImageIcon BLUE_KING;
	
	private Element contents;		// enum Element representation of the space's contents
	private JLabel lblContents;		// label where image is displayed
	private ImageIcon icon;			// picture set on the label
	private int row;				// space's row location
	private int col;				// space's column location
	
	Space(int r, int c)
	{
		setLayout(new BorderLayout());
		lblContents = new JLabel();
		add(lblContents);
		row = r;
		col = c;
	}
	
	// Changes the space's image based on the new Element provided.  Player color is
		//  necessary to differentiate the non-unique images for valid pieces and kings.
	public void setImage(Element element, String color)
	{
		switch (element)
		{
		case WHITESPACE:
			icon = WHITE_SQUARE;
			break;
		case BLACKSPACE:
			icon = BLACK_SQUARE;
			break;
		case GREENSPACE:
			icon = GREEN_SQUARE;
			break;
		case RED:
			icon = RED_MAN;
			break;
		case BLUE:
			icon = BLUE_MAN;
			break;
		case REDKING:
			icon = RED_KING;
			break;
		case BLUEKING:
			icon = BLUE_KING;
			break;
		case VALID:
			if (color.equals("Red"))
			{ icon = RED_MAN; }
			else
			{ icon = BLUE_MAN; }
			break;
		case VALIDKING:
			if (color.equals("Red"))
			{ icon = RED_KING; }
			else
			{ icon = BLUE_KING; }
			break;
		}
		
		contents = element;
		lblContents.setIcon(icon);
	}
	
	public Element getContents()
	{ return contents; }
	
	public int getRow()
	{ return row; }
	
	public int getCol()
	{ return col; }
	
	public static void loadImages()
	{
		try		// load images - Eclipse
		{
			WHITE_SQUARE = new ImageIcon(Client.class.getResource("/white_square.png"));
			BLACK_SQUARE = new ImageIcon(Client.class.getResource("/black_square.png"));
			GREEN_SQUARE = new ImageIcon(Client.class.getResource("/legal.png"));
			RED_MAN = new ImageIcon(Client.class.getResource("/red.png"));
			BLUE_MAN = new ImageIcon(Client.class.getResource("/blue.png"));
			RED_KING = new ImageIcon(Client.class.getResource("/red_king.png"));
			BLUE_KING = new ImageIcon(Client.class.getResource("/blue_king.png"));
		}
		catch(NullPointerException e)	// load images - command line
		{
			WHITE_SQUARE = new ImageIcon("../res/white_square.png");
			BLACK_SQUARE = new ImageIcon("../res/black_square.png");
			GREEN_SQUARE = new ImageIcon("../res/legal.png");
			RED_MAN = new ImageIcon("../res/red.png");
			BLUE_MAN = new ImageIcon("../res/blue.png");
			RED_KING = new ImageIcon("../res/red_king.png");
			BLUE_KING = new ImageIcon("../res/blue_king.png");
		}
	}
}
