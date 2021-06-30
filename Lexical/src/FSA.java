public class FSA {
	
	
	private int stateTable[][]; 

	  public FSA(){	   
	     stateTable = new int[16][8]; 
	     stateTable[0][0] = 5; stateTable[0][1] = 3; stateTable[0][2] = 0; stateTable[0][3] = 7; stateTable[0][4] = 11; stateTable[0][5] = 14; stateTable[0][6] = 0; stateTable[0][7] = 0;
	     stateTable[3][0] = 4; stateTable[3][1] = 3; stateTable[3][2] = 4; stateTable[3][3] = 4; stateTable[3][4] = 4; stateTable[3][5] = 4; stateTable[3][6] = 4; 
	     stateTable[5][0] = 5; stateTable[5][1] = 5; stateTable[5][2] = 6; stateTable[5][3] = 6; stateTable[5][4] = 6; stateTable[5][5] = 6; stateTable[5][6] = 6;
	     stateTable[7][0] = 10; stateTable[7][1] = 10; stateTable[7][2] = 8; stateTable[7][3] = 10; stateTable[7][4] = 10; stateTable[7][5] = 10; stateTable[7][6] = 10;
	     stateTable[8][0] = 8; stateTable[8][1] = 8; stateTable[8][2] = 9; stateTable[8][3] = 8; stateTable[8][4] = 8; stateTable[8][5] = 8; stateTable[8][6] = 8;
	     stateTable[9][0] = 8; stateTable[9][1] = 8; stateTable[9][2] = 8; stateTable[9][3] = 0; stateTable[9][4] = 8; stateTable[9][5] = 8; stateTable[9][6] = 8;
	     stateTable[11][0] = 12; stateTable[11][1] = 12; stateTable[11][2] = 12; stateTable[11][3] = 12; stateTable[11][4] = 13; stateTable[11][5] = 12; stateTable[11][6] = 12;
	     stateTable[14][0] = 15; stateTable[14][1] = 15; stateTable[14][2] = 15; stateTable[14][3] = 15; stateTable[14][4] = 16; stateTable[14][5] = 15; stateTable[14][6] = 15;
	  }

	  public FSA(int tabData[], int nrows, int ncols){  //User supplied credit policy.
	    stateTable = new int[nrows][ncols];
	    for(int row = 0; row < nrows; row++)
		  for(int col = 0; col < ncols; col++) 
		    stateTable[row][col] = tabData[row*3+col];
	  }

	  public void ProcessRequest(String str){
	    boolean finished = false;
	    boolean first = true;
	    boolean flag = false;
	    boolean f5 = false;
	    boolean f3 = false;
	    int index = 0;
	    int end = 0;
	    int start = 0;
	    boolean check = false;
	    int finalindex = str.length();
	    char x;
	    int knt = 0;
	    String s = "";
	    TokenList list = new TokenList();
	    SymbolTable sym = new SymbolTable();
		
		int nextState = 0;
		do{
		  switch(nextState){
		  case 0: 
			  Token d = null;
			  f5 = false; f3 = false;
			  x = str.charAt(index);
			  nextState = stateTable[0][toInt(x)];
			 // System.out.println(nextState);
			//  if(x == ' ')
			//	  System.out.println("EMPTY SPACE");
			//  else
			//	  System.out.println(x);
			  
			  if(!(isSingleDelim(x).equals(" ")))
			  {
				  
				  
				  s = isSingleDelim(x);
				  if((index + 1) == finalindex) {
					  d = new Token(str.substring(index,index+1),s);
					  list.addToken(d);
					 // list.printTokens();
					  finished = true;
					  break;
					  
				  }
				  if(isSingleDelim(str.charAt(index+1)).equals(" ")) {
					  d = new Token(str.substring(index,index+1),s);
					 // if(str.substring(index,index+1).equals(" "))
						//  System.out.println("EMPTY SPACE");
					  list.addToken(d);
					  //list.printTokens();
				  }
			  }  
			  index++;
			 /* if(nextState != 0)
				  first = false;
			  if(first == false && nextState != 0) 
			  {
				  start = index;
				  for(int k = start; k < str.length(); k++)
				  {
					 
					  if(str.charAt(k) == ' ' || str.charAt(k) == '{' || str.charAt(k) == '}' || str.charAt(k) == ',' || str.charAt(k) == ';')
					  {
						  if(str.charAt(k) == '}')
							  finished = true;
						  end = k;
						  k = str.length();
						  
					  }	  
				  }
				  Token t = new Token(str.substring(start,end),str.substring(start,end));
				  t.addToken(t);
				  t.printTokens();
				  
				  break;
			  }
				*/  
			  
				  
			  break;
		  case 1: 
			  System.out.println("Error, illegal character in input!");
			  finished = true;
			  break;
		  case 2: 
			  x = str.charAt(index);
			  nextState = stateTable[2][toInt(x)];
			  break;
		  case 3: 
			  x = str.charAt(index);
			  if(f3 == false)
			  {
				  start = index - 1;
				  f3 = true;
			  }
			  index++;
			  nextState = stateTable[3][toInt(x)];
			  if(nextState != 3) {
				  end = index - 1;
				  index--;
			  }
			  
			  //System.out.println(nextState);
			 // if(x == ' ')
			//	  System.out.println("EMPTY SPACE");
			 // else
				 // System.out.println(x);
			  
			  break;
		  case 4: 
			  
			  Token i = null;
			  x = str.charAt(index-1);
			  nextState = 0;
			  i = new Token(str.substring(start,end),"<integer>");
			  list.addToken(i);
			//  list.printTokens();
			  break;
		  case 5: 
			  x = str.charAt(index);
			  if(f5 == false)
			  {
				  start = index - 1;
				  f5 = true;
			  }	  
			  index++;
			  nextState = stateTable[5][toInt(x)];
			  if(nextState != 5) { 
				  end = index - 1;
				  index--;
			  }
			  
			 // if(x == ' ')
				//  System.out.println("EMPTY SPACE");
			 // else
				//  System.out.println(x);
			  break;
		  case 6: 
			  Token t = null;
			 // x = str.charAt(index);
			  nextState = 0;
			  if(str.substring(start,end).equals("CLASS")) 
			  {
				  t = new Token(str.substring(start,end),"$class");
				  check = true;
			  }
				   
			  
			  if(str.substring(start,end).equals("IF"))
			  {
				  t = new Token(str.substring(start,end),"$if");
				  check = true;
			  }
				  
			  if(str.substring(start,end).equals("VAR"))
			  {
				  t = new Token(str.substring(start,end),"$var");
				  check = true;
			  }
			  
			  
				   
			  if(str.substring(start,end).equals("THEN"))
			  {
				  t = new Token(str.substring(start,end),"$then");
				  check = true;
				  
			  }
				  
			  if(str.substring(start,end).equals("ELSE"))
			  {
				  t = new Token(str.substring(start,end),"$else");
				  check = true;
			  }
				   
			  if(str.substring(start,end).equals("CALL"))
			  {
				  t = new Token(str.substring(start,end),"$call");
				  check = true;
			  }
				  
			  if(str.substring(start,end).equals("PROCEDURE"))
			  {
				  t = new Token(str.substring(start,end),"$procedure");
				  check = true;
			  }
				   
			  
			  if(str.substring(start,end).equals("CONST"))
			  {
				  t = new Token(str.substring(start,end),"$const");
				  check = true;
			  }
			  
			  if(check == false)
				  t = new Token(str.substring(start,end),"<var>");
			  check = false;
			  list.addToken(t);
			  //list.printTokens();
			  break;
		  case 7: 
		          finished = true; break;
		  case 8: 
		          finished = true; break;
		  case 9:  finished = true;
		  
		  case 11: finished = true; break;
		  
		  }
		 // knt++;
		 // if(knt > 125) {
		//	  list.printTokens();
			// // finished = true;
		 // }
			  
		  //System.out.println();
		  //System.out.println(nextState);
		  if(index == finalindex)
			  finished = true;
			
		}while(!finished);
		
		
		System.out.println("TOKEN       CLASSIFICATION");
		list.printTokens();
		sym.buildTable(list);
		
		
	  }
	  
	  public boolean isLetter(char c)
	  {
		  return Character.isLetter(c);
	  }
	  public int toInt(char c)
	  {
		  if(c == ' ')
			  return 6;
		  if(c == '{')
			  return 6;
		  if(c == '=')
			  return 6;
		  if(c == ',')
			  return 6;
		  if(c == ';')
			  return 6;
		  if(c == '}')
			  return 6;
		  
		  if(Character.isLetter(c) == true)
			  return 0;
		  if(Character.isDigit(c) == true)
			  return 1;
		  if(c == '*')
			  return 2;
		  if(c == '/')
			  return 3;
		  if(c == '=')
			  return 4;
		  if(c == '<')
			  return 5;
		  
		  return 7;
		  
	  }
	  public String isSingleDelim(char i)
	  {
		  if(i == '*')
			  return "<mop>";
		  if(i == '/')
			  return "<mop>";
		  if(i == '=')
			  return "<assign>";
		  if(i == '<')
			  return "<relop>";
		  if(i == '>')
			  return "<relop>";
		  if(i == '{')
			  return "$lb";
		  if(i == '}')
			  return "$rb";
		  if(i == ',')
			  return "<comma>";
		  if(i == ';')
			  return "<semi>";
		  if(i == '+')
			  return "<addop>";
		  if(i == '-')
			  return "<addop>";
		  if(i == '(')
			  return "$lp";
		  if(i == ')')
			  return "$rp";
		  return " ";
		  
	  }
	  
	  
	  

	
	

}
