
public class Parser
{
	private final int SELECTED_ROW_INDEX = 0;
	private final int SELECTED_COLUMN_INDEX = 1;
	private final int DESTINATION_ROW_INDEX = 2;
	private final int DESTINATION_COLUMN_INDEX = 3;
	
	public void translate(Byte messageType, Object message, Integer matchIndex)
	{
		GameManagement gM = GameManagement.getInstance();
		
		System.out.println(messageType);
		System.out.println("Message:\n" + message);
		
		switch(messageType)
		{
			case 'P':
				String messageP = (String) message;
				int selected_row = Character.getNumericValue(messageP.charAt(SELECTED_ROW_INDEX));
				int selected_column = Character.getNumericValue(messageP.charAt(SELECTED_COLUMN_INDEX));
				
				gM.processSelectPieceMessage(matchIndex, selected_row, selected_column);
				break;
			case 'S': // Board
				String messageS = (String) message;
				int s_row = Character.getNumericValue(messageS.charAt(SELECTED_ROW_INDEX));
				int s_column = Character.getNumericValue(messageS.charAt(SELECTED_COLUMN_INDEX));
				int d_row = Character.getNumericValue(messageS.charAt(DESTINATION_ROW_INDEX));
				int d_column = Character.getNumericValue(messageS.charAt(DESTINATION_COLUMN_INDEX));
				Move move = new Move(s_row, s_column, d_row, d_column);
				
				gM.processMoveMessage(matchIndex, move);
				break;
			case 'Y': // Yes to rematch
				gM.rematch(matchIndex);
				break;
		}
	}

}
