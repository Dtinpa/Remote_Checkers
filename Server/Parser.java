
public class Parser
{
	private final int SELECTED_ROW_INDEX = 0;
	private final int SELECTED_COLUMN_INDEX = 1;
	private final int DESTINATION_ROW_INDEX = 2;
	private final int DESTINATION_COLUMN_INDEX = 3;
	
	public void translate(Byte messageType, Object message, Integer matchIndex)
	{
		GameManagement gM = GameManagement.getInstance();
		MatchMaking maker = MatchMaking.getInstance();
		
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
				Move move = new Move();
				move.selected_row = Character.getNumericValue(messageS.charAt(SELECTED_ROW_INDEX));
				move.selected_column = Character.getNumericValue(messageS.charAt(SELECTED_COLUMN_INDEX));
				move.destination_row = Character.getNumericValue(messageS.charAt(DESTINATION_ROW_INDEX));
				move.destination_column = Character.getNumericValue(messageS.charAt(DESTINATION_COLUMN_INDEX));
				
				gM.processMoveMessage(matchIndex, move);
				break;
			case 'Y': // Yes to rematch
				gM.rematch(matchIndex);
				break;
			case 'N': // No to rematch
				maker.unmatchClients(matchIndex);
				break;
		}
	}

}
