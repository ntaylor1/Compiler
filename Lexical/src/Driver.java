import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
public class Driver {

	public static void main(String[] args) throws Exception {
		  
		File file = new File("C:\\Users\\n8thegr8\\Desktop/input.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st = "";
		String str = "";
		
	    while ((st = br.readLine()) != null)
	    {
	    	str += st;
	    	
	    }
	    System.out.println(str);
	    FSA Automaton = new FSA();
	    Automaton.ProcessRequest(str);
	   
	    
	    
	   
	          
		    
		    

	}

}