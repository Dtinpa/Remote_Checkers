import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Listen
{
	private Lock lock;  
	private ParseFromServer parser; 
	private InServer input; 
	private Queue<Byte> types; 
	private Queue<Object> messages; 
	
	public Listen(Socket socket)
	{
		lock = new ReentrantLock(); 
		parser = new ParseFromServer();
		types = new LinkedList<Byte>(); 
		messages = new LinkedList<Object>();
		input = new InServer(socket); 
	}
	
	public Object retrieveMessages()
	{
		while (true)
		{
			lock.tryLock();
			Object message = input.read(); 
			Byte messageType = (Byte)message; 
			types.add(messageType); 
			messages.add(input.read());
			lock.unlock();
		}
	}
	
	//First is always the byte, next is the message
	public Object[] getNextMessage()
	{
		while (messages.peek() == null)
		{
			try 
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		} 
		
		Byte type = types.remove(); 
		Object message = messages.remove();
		Object[] retValue = {type, message};
		parser.translate(type, message);
		
		return retValue; 
	}

}
