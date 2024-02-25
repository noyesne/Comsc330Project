//package grpFileSearch; // Eclipse BS

//This is an early draft of the code, just to demonstrate the function
//A more robust version shall be released and developed in the future
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

//Test of the GRP file
public class TestGRP 
{
	public static void main(String[] args) throws java.io.IOException
	{
		String grp = "C:\\Users\\nicho\\eclipse-workspace\\ComSc330Project\\ComSc110.txt"; //FILE PATH

    //Uses the bufferReader to more effectively go through the file, to accommodate for file size
		try (BufferedReader brgrp = new BufferedReader(new FileReader(grp))) 
		{
		     String line; //String reader
		     while ((line = brgrp.readLine()) != null) //While there is SOMETHING to read...
		     {
		         // Print what it says
		         System.out.println("Directory: " + grp + " Class: " + line);
		     }
		     
		//The try-catch function
		} catch (IOException e) 
		
		{
		   System.err.println("Error reading the file: " + e.getMessage());
		}
	}

}

//Nicholas Vieira - around February 2024
