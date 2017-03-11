import java.io.IOException;
import java.util.*;

public class MatchMaking
{
	private ArrayList<ClientInfo> clientOnes;
	private ArrayList<ClientInfo> clientTwos;
	
	private static MatchMaking singleton;
	
	private MatchMaking()
	{
		clientOnes = new ArrayList<ClientInfo>();
		clientTwos = new ArrayList<ClientInfo>();
	}
	
	public static MatchMaking getInstance()
	{
		if (singleton == null)
			singleton = new MatchMaking();
		return singleton;
	}
	
	private int getIndexForNewMatch()
	{
		for (int i = 0; i < clientOnes.size(); i++)
		{
			if (clientOnes.get(i) == null)
				return i;
		}
		
		return clientOnes.size();
	}
	
	public void matchClients()
	{
		Transcription transcription = Transcription.getTranscription();
		int matchIndex = getIndexForNewMatch();
		
		//was: transcription.getClientsCount() >= 2 
		//Check else 
		if (transcription.getClientsCount() % 2 == 0)
		{
			
			ClientInfo firstClient = transcription.getClientForMatching();
			ClientInfo secondClient = transcription.getClientForMatching();
			
			//Example of how to write to the client
			transcription.write(secondClient.getSocket(), (byte)'C');
			transcription.write(secondClient.getSocket(), "Red");
			
			
			firstClient.setGameIndex(matchIndex);
			secondClient.setGameIndex(matchIndex);
			
			Thread listenThread = new Thread()
			{
				public void run()
				{
					//listen = new Listen(matchIndex);
					Listen listen = new Listen(secondClient.getSocket());
					listen.retrieveMessages(matchIndex);
				}
			}; 
			listenThread.start();
			
			if (matchIndex == clientOnes.size())
			{
				clientOnes.add(firstClient);
				clientTwos.add(secondClient);
			}
			else
			{
				clientOnes.set(matchIndex, firstClient);
				clientTwos.set(matchIndex, secondClient);
			}
			GameManagement.getInstance().openGameForClients(matchIndex, firstClient.getSocket(), secondClient.getSocket());
		}
		else
		{
			ClientInfo firstClient = transcription.peakClientForMatching(); 
			
			Thread listenThread = new Thread()
			{
				public void run()
				{
					//listen = new Listen(matchIndex);
					Listen listen = new Listen(firstClient.getSocket());
					listen.retrieveMessages(matchIndex);
				}
			}; 
			listenThread.start();
			
			//Example of how to write to the client 
			transcription.write(firstClient.getSocket(), (byte)'C');
			transcription.write(firstClient.getSocket(), "Blue");
			transcription.write(firstClient.getSocket(), (byte)'M');
			transcription.write(firstClient.getSocket(), "Waiting for Opponent...");
		}
	}
	
	public int getClientIndex(ClientInfo disconnected)
	{
		int index = -1;
		index = clientOnes.indexOf(disconnected);
		if (index == -1)
			index = clientTwos.indexOf(disconnected);
		return index;
	}
	
	public void unmatchClients(int matchIndex)
	{
		try
		{
			GameManagement.getInstance().closeGame(matchIndex);
			
			clientOnes.get(matchIndex).getSocket().close();
			clientTwos.get(matchIndex).getSocket().close();
			clientOnes.set(matchIndex, null);
			clientTwos.set(matchIndex, null);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
