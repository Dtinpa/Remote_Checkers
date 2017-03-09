import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Listen
{
	private Lock lock; 
	private static Listen instance;  
	private ParseFromServer parser; 
	private InServer input; 
	private Queue<Byte> types; 
	private Queue<String> messages; 
	
	private Listen(Socket socket)
	{
		lock = new ReentrantLock(); 
		parser = new ParseFromServer();
		types = new LinkedList<Byte>(); 
		messages = new LinkedList<String>();
		input = new InServer(socket); 
	}
	
	public static Listen getInstance(Socket socket)
	{
		if (instance == null && socket != null)
		{
			instance = new Listen(socket); 
		}
		return instance; 
	}
	
	public Object retrieveMessages()
	{
		while (true)
		{
			lock.tryLock();
			Byte messageType = (Byte)input.read();
			types.add(messageType); 
			messages.add((String)input.read());
			lock.unlock();
		}
	}
	
	//First is always the byte, next is the message
	public Object[] getNextMessage()
	{
		if (messages.peek() == null) return null; 
		
		lock.tryLock(); 
		Byte type = types.remove(); 
		String message = messages.remove();
		Object[] retValue = {type, message}; 
		lock.unlock();
		
		return retValue; 
	}

}
