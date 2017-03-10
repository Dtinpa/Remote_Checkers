import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;

public class InFile extends Input
{
	private static InFile instance; 
	private BufferedReader input; 
	private OutFile output = OutFile.getInstance(); 
	private File file = new File(getConfigFilePath()); 
	
	private InFile(){}
	
	public static InFile getInstance()
	{
		if(instance == null)
		{
			instance = new InFile(); 
		}
		return instance; 
	}
	
	public String read()
	{
		String inputObj = ""; 
		
		try
		{
			if (!file.exists())
			{
				file = new File("../" + getConfigFilePath());
			}
			
			input = new BufferedReader(new FileReader(file.getAbsolutePath()));
			inputObj = input.readLine();
		}
		catch(Exception ex)
		{
			System.out.println("hey");
			output.writeError(ex.getMessage());
		}
		
		return inputObj; 
	}
}

