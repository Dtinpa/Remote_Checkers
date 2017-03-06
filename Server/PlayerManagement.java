import java.util.*;

public class PlayerManagement
{	
	public ArrayList<Player> playerOnes;
	public ArrayList<Player> playerTwos;
	public ArrayList<Player> activePlayers;
	
	public PlayerManagement()
	{
		playerOnes = new ArrayList<Player>();
		playerTwos = new ArrayList<Player>();
		activePlayers = new ArrayList<Player>();
	}
	
	public Player getPlayerOne(int game_index)
	{
		return playerOnes.get(game_index);
	}
	
	public Player getPlayerTwo(int game_index)
	{
		return playerTwos.get(game_index);
	}
	
	public Player getActivePlayer(int game_index)
	{
		return activePlayers.get(game_index);
	}
	
	public void assignOrder(int matchIndex)
	{
		Player firstPlayer = new Player();
		firstPlayer.color = Player.BLUE;
		firstPlayer.score = 0;
		
		Player secondPlayer = new Player();
		secondPlayer.color = Player.RED;
		secondPlayer.score = 0;
		
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
	
	public void swapTurnOrder(int game_index)
	{
		Player playerOne = playerOnes.get(game_index);
		Player playerTwo = playerTwos.get(game_index);
		playerOnes.set(game_index, playerTwo);
		playerTwos.set(game_index, playerOne);
	}
	
	public void changeActivePlayer(int game_index)
	{
		Player playerOne = playerOnes.get(game_index);
		Player playerTwo = playerTwos.get(game_index);
		if (activePlayers.get(game_index) == playerOne)
			activePlayers.set(game_index, playerTwo);
		else
			activePlayers.set(game_index, playerOne);
	}
	
	public void dismissPlayers(int game_index)
	{
		playerOnes.set(game_index, null);
		playerTwos.set(game_index, null);
		activePlayers.set(game_index, null);
	}
}
