import java.net.Socket;
import java.util.*;

public class PlayerManagement
{	
	private static PlayerManagement instance; 
	private ArrayList<Player> playerOnes;
	private ArrayList<Player> playerTwos;
	private ArrayList<Player> activePlayers;
	
	private PlayerManagement()
	{
		playerOnes = new ArrayList<Player>();
		playerTwos = new ArrayList<Player>();
		activePlayers = new ArrayList<Player>();	
	}
	
	public static PlayerManagement getInstance()
	{
		if (instance == null)
		{
			instance = new PlayerManagement(); 
		}
		return instance; 
	}
	
	public Player getActivePlayer(int matchIndex)
	{
		return activePlayers.get(matchIndex);
	}
	
	public Player getInactivePlayer(int matchIndex)
	{
		Player playerOne = playerOnes.get(matchIndex);
		Player playerTwo = playerTwos.get(matchIndex);
		if (activePlayers.get(matchIndex) == playerOne)
			return playerTwo;
		else
			return playerOne;
	}
	
	public void assignOrder(int matchIndex, Socket clientOne, Socket clientTwo)
	{
		Player firstPlayer = new Player();
		firstPlayer.setColor(Player.BLUE);
		firstPlayer.setSocket(clientOne);
		
		Player secondPlayer = new Player();
		secondPlayer.setColor(Player.RED);
		secondPlayer.setSocket(clientTwo);
		
		if (matchIndex == activePlayers.size())
		{
			playerOnes.add(firstPlayer);
			playerTwos.add(secondPlayer);
			activePlayers.add(firstPlayer);
		}
		else
		{
			playerOnes.set(matchIndex, firstPlayer);
			playerTwos.set(matchIndex, secondPlayer);
			activePlayers.set(matchIndex, firstPlayer);
		}
	}
	
	public void swapTurnOrder(int matchIndex)
	{
		Player playerOne = playerOnes.get(matchIndex);
		Player playerTwo = playerTwos.get(matchIndex);
		playerOnes.set(matchIndex, playerTwo);
		playerTwos.set(matchIndex, playerOne);
		activePlayers.set(matchIndex, playerTwo);
	}
	
	public void changeActivePlayer(int matchIndex)
	{
		Player playerOne = playerOnes.get(matchIndex);
		Player playerTwo = playerTwos.get(matchIndex);
		if (activePlayers.get(matchIndex) == playerOne)
			activePlayers.set(matchIndex, playerTwo);
		else
			activePlayers.set(matchIndex, playerOne);
	}
	
	public void dismissPlayers(int matchIndex)
	{
		playerOnes.set(matchIndex, null);
		playerTwos.set(matchIndex, null);
		activePlayers.set(matchIndex, null);
	}
}
