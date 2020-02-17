package serialization;
import java.io.*;

public class Serializedemo
{
	public static void main(String[] args) 
	{
		Employee e = new Employee();
		e.name = "jack";
		e.address = "konghong";
		e.SSN = 123321;
		e.number = 101;
	try
	{
		FileOutputStream file_out = new FileOutputStream("temp/e.ser");
		ObjectOutputStream out = new ObjectOutputStream(file_out);
		out.writeObject(e);
		out.close();
		file_out.close();
		System.out.printf("already save yet");
		
	}
	catch (IOException i) {
		i.printStackTrace();
	}
	}
}
