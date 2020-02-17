package DeserializeDemo;
import java.io.*;

import serialization.Employee;
public class DeserializeDemo {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Employee e= null;
		try
		{
			FileInputStream fileIn = new FileInputStream("temp/e.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Employee) in .readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException e1) 
		{
			// TODO: handle exception
			e1.printStackTrace();
			return;
		}
		catch (Exception e2) 
		{
			// TODO: handle exception
			System.out.println("employee class not found");
			e2.printStackTrace();
			return;
		}
		System.out.println(e.name);
		System.out.println(e.address);
		System.out.println(e.SSN);
		System.out.println(e.number);
	}

}
