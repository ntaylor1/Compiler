import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class Driver {

	public static void main(String[] args) throws Exception{
		
			  
			File file = new File("lexoutput.txt"); 
			  
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			  
			String st = "";
			String str = "";
			
		    while ((st = br.readLine()) != null)
		    {
		    	str += st;
		    	
		    }
		   // System.out.println(str);
		    PDA Automaton = new PDA();
		    Automaton.StartLexicalAnalyzer(str);
		   
		    
		    
		   
		    
		    
		   
		          
			    
			    

		}

}


