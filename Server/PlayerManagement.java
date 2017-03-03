import java.util.*;

public class PlayerManagement
{	
	public ArrayList<Player> playerOnes;
	public ArrayList<Player> playerTwos;
	public ArrayList<Player> activePlayers;
	public MatchMaking matchMaking;
	
	public PlayerManagement()
	{
		playerOnes = new ArrayList<Player>();
		playerTwos = new ArrayList<Player>();
		activePlayers = new ArrayList<Player>();
		matchMaking = new MatchMaking();
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
	
	public void assignOrder(Player firstClient, Player secondClient)
	{
		firstClient.color = Player.BLUE;
		firstClient.score = 0;
		playerOnes.add(firstClient);
		
		secondClient.color = Player.RED;
		secondClient.score = 0;
		playerTwos.add(secondClient);
		
		activePlayers.add(firstClient);
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
		activePlayers.set(game_index, null);
		
	}
}
