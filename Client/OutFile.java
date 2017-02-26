import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class OutFile extends Output
{
	@Override
	public void write(Object[] message)
	{
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(configFile, "UTF-8");	// edit this line to an invalid file path to get the error to throw
			writer.println(message[0]);
		    writer.println(message[1]);
		    writer.close();
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			try
			{
				writer = new PrintWriter(logFile, "UTF-8");
				writer.println(getTime() + "  Error writing to config file");	// find way to display current method?
			    writer.close();
			}
			catch (FileNotFoundException | UnsupportedEncodingException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	    

	}

}
