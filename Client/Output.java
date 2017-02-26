import java.sql.Timestamp;


public class Output extends IO
{
	public Timestamp getTime()
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
	
	public void write(Object[] message)
	{
		
	}

}
