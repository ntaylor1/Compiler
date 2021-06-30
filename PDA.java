import java.io.File;
import java.util.Stack;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class PDA {
	
	TokenList tokList = new TokenList();
	String tokenArray[];
	int numTokens;
	int tokensUsed;
	Stack<Token> stack = new Stack<>();
	Stack<Token> opstack = new Stack<>();
	Stack<Token> fixup = new Stack<>();
	ArrayList<Quad> QuadList = new ArrayList<>();
	int temp = 1;
	int ptemp = 0;
	
	
	public PDA() {
	int[][] precTable = new int[8][8]; 
    precTable[0][1] = 1; precTable[1][0] = 2; precTable[1][2] = 1; precTable[1][3] = 1; precTable[1][4] = 1; precTable[1][6] = 1; precTable[1][7] = 1;
    precTable[2][0] = 2; precTable[2][2] = 2; precTable[2][3] = 2; precTable[2][4] = 1;
    precTable[2][5] = 2; precTable[2][6] = 1; precTable[2][7] = 1;
    precTable[3][0] = 2; precTable[3][2] = 2; precTable[3][3] = 2; precTable[3][4] = 1;
    precTable[3][5] = 2; precTable[3][6] = 1; precTable[3][7] = 1; precTable[4][2] = 1;
    precTable[4][3] = 1; precTable[4][4] = 1; precTable[4][5] = 0; precTable[4][6] = 1; 		 
    precTable[4][7] = 1; precTable[5][0] = 2; precTable[5][2] = 2; precTable[5][3] = 2;
    precTable[5][5] = 2; precTable[5][6] = 2; precTable[5][7] = 2; precTable[6][0] = 2;
    precTable[6][2] = 2; precTable[6][3] = 2; precTable[6][4] = 1; precTable[6][5] = 2;
    precTable[6][6] = 2; precTable[6][7] = 2; precTable[7][0] = 2; precTable[7][2] = 2; 
    precTable[7][3] = 2; precTable[7][4] = 1; precTable[7][5] = 2; precTable[7][6] = 2;
    
    		
	}


	public void StartLexicalAnalyzer(String str){
		tokenArray = new String [100];
		String tempToken;
		StringTokenizer tokens = new StringTokenizer(str, " \n\t\r+-*/{}();=",true);
		numTokens = tokens.countTokens();
		int cntTokens = 0;
		while(tokens.hasMoreTokens()){
			 tempToken = tokens.nextToken();
			 if(!tempToken.equals(" ")) tokenArray[cntTokens++] = tempToken;
			}
		numTokens = cntTokens;
		tokensUsed = 0;
	
		tokList.addToken(new Token("$","$"));
		for(int x = 0; x < tokenArray.length - 1; x++)
		{
			if(tokenArray[x+1] != null)
			{
				if(x % 2 == 0)
					tokList.addToken(new Token(tokenArray[x],tokenArray[x+1]));
					
			}
					
			
		}
		tokList.addToken(new Token("$","$"));
		//tokList.printTokens();
		parseTokens(tokList);
	}
	public Token NextToken(){
	 if(tokensUsed < numTokens)
	 return tokList.getToken(tokensUsed++);
	 else
	 return new Token("$","$");
	}
	
	public int precedence(Token a, Token b)
	{
		if(a.type.contentEquals("$")) 
		{
			return 1;
		}
		if(b.type.contentEquals("<semi>"))
			return 2;
		if(a.type.contentEquals("<var>") || a.type.contentEquals("<integer>") || b.type.contentEquals("<var>") || b.type.contentEquals("<integer>"))
			return 1;
		if(a.type.contentEquals("<assign>"))
		{
			if(b.type.contentEquals("$"))
				return 2;
			if(b.type.contentEquals("<addop>"))
				return 1;
			if(b.type.contentEquals("$lp"))
				return 1;
			if(b.type.contentEquals("<mop>"))
				return 1;	
			if(b.type.contentEquals("<semi>"))
				return 2;
		}
		if(a.type.contentEquals("<addop>"))
		{
			if(b.type.contentEquals("$"))
				return 2;
			if(b.type.contentEquals("<addop>"))
				return 2;
			if(b.type.contentEquals("$lp"))
				return 1;
			if(b.type.contentEquals("$rp"))
				return 2;
			if(b.type.contentEquals("<mop>"))
				return 1;	
		}
		if(a.type.contentEquals("$lp"))
		{
			
			if(b.type.contentEquals("<addop>"))
				return 1;
			if(b.type.contentEquals("$lp"))
				return 1;
			if(b.type.contentEquals("$rp"))
				return 0;
			if(b.type.contentEquals("<mop>"))
				return 1;	
		}
		if(a.type.contentEquals("$rp"))
		{
			if(b.type.contentEquals("$"))
				return 2;
			if(b.type.contentEquals("<addop>"))
				return 2;
			if(b.type.contentEquals("$rp"))
				return 2;
			if(b.type.contentEquals("<mop>"))
				return 2;	
		}
		if(a.type.contentEquals("<mop>"))
		{
			if(b.type.contentEquals("$"))
				return 2;
			if(b.type.contentEquals("<addop>"))
				return 2;
			if(b.type.contentEquals("$lp"))
				return 1;
			if(b.type.contentEquals("$rp"))
				return 2;
			if(b.type.contentEquals("<mop>"))
				return 2;	
		}
		
		else
			return 1;
		return -1;
		/*
		if(a.type.contentEquals("<var>"))
		{
			return 1;	
		}
		
		if(a.type.contentEquals(""))
		{
			return 1;	
		}
		if(a.type.contentEquals("$lb"))
		{
			return 1;	
		}
		if(a.type.contentEquals("<var>"))
		{
			return 1;	
		}
		*/
			
				
		
		
		
		
	}
	
	
	public int findTok(TokenList t, Token tok)
	{
		for(int x = 0; x < t.getListSize(); x++)
		{
			if(t.getToken(x).name.contentEquals(tok.name))
				return x;
		}
		return -1;
	}
	

	
	public int findprevOp(int index,TokenList t)
	{
		for(int x = index - 1; x >= 0; x--)
		{
			if(t.getToken(x).name.equals(";"))
				return -1;
			
			if(t.getToken(x).type.equals("<assign>"))
			{
				return x;
			}
			if(t.getToken(x).type.contentEquals("<mop>"))
			{
				return x;
			}
			if(t.getToken(x).type.equals("$rp"))
			{
				return x;
			}
			if(t.getToken(x).type.contentEquals("$lp"))
			{
				return x;
			}
			
					
		}
		return -1;
	}
	
	public String getTemp()
	{
		if(temp == 1)
			return "T1";
		if(temp == 2)
			return "T2";
		if(temp == 3)
			return "T3";
		if(temp == 4)
			return "T4";
		if(temp == 5)
			return "T5";
		if(temp == 6)
			return "T6";
		if(temp == 7)
			return "T7";
		if(temp == 8)
			return "T8";
		if(temp == 9)
			return "T9";
		
		return "ERROR";
		
		
		
	}
	
	
	public void parseTokens(TokenList t)
	{
		Token current,next,prev,start,tempcurr,temp2,temp3,temp4;
		TokenList tempList = new TokenList();
		Quad q;
		int k = 0;
		QuadList quadList = new QuadList();
		start = t.getToken(0);
		opstack.push(start);
		
		for(int x = 0; x < t.getListSize() - 1; x++)
		{
			current = t.getToken(x);
			
			if(current.name.contentEquals("CONST"))
			{
				for(int i = x; i < t.getListSize() - 1; i++)
				{
					if(t.getToken(i).name.equals(";"))
					{
						x = i + 1;
						break;
					}
				}
			}
			
			

			
			if(isNonTerm(current))
			{
				int z = x;
				if(t.getToken(x+1).name.contentEquals("="))
					stack.push(current);
				else
				{
					while(!t.getToken(z).name.contentEquals(";") && z > 0)
					{
						if(t.getToken(z).name.contentEquals("="))
							stack.push(current);
						z--;
					}
				}
			}
			
			
			if(isOper(current))
			{
				if(precedence(start,current) == 1)
					opstack.push(current);
				else
				{
					
					
					tempcurr = opstack.pop();
					temp3 = stack.pop();
					temp4 = stack.pop();
					q = new Quad(tempcurr.name,temp4.name,temp3.name,getTemp());
					quadList.addQuad(q);
					stack.push(new Token(getTemp(), "$" + getTemp()));
					temp++;
					
					if(precedence(opstack.peek(), current) == 1)
						opstack.push(current);
					else
					{
						while(precedence(opstack.peek(),current) == 2)
						{
							tempcurr = opstack.pop();
							temp3 = stack.pop();
							temp4 = stack.pop();
							q = new Quad(tempcurr.name,temp4.name,temp3.name,getTemp());
							quadList.addQuad(q);
							stack.push(new Token(getTemp(), "$" + getTemp()));
							temp++;
						}
						opstack.push(current);
					}
					
				
				}
				start = current;
			}
			
			if(current.type.equals("<semi>") && !opstack.isEmpty() && !stack.isEmpty())
			{
				while(!opstack.peek().name.contentEquals("="))
				{
					tempcurr = opstack.pop();
					temp3 = stack.pop();
					temp4 = stack.pop();
					q = new Quad(tempcurr.name,temp4.name,temp3.name,getTemp());
					stack.push(new Token(getTemp(), "$" + getTemp()));
					temp++;
					quadList.addQuad(q);
				}
				tempcurr = opstack.pop();
				temp3 = stack.pop();
				temp4 = stack.pop();
				q = new Quad(tempcurr.name,temp4.name,temp3.name,"-");
				quadList.addQuad(q);
				
			}
			
			if(current.name.equals("}"))
			{
				printOpStack();
				System.out.println("----------");
				
				printStack();
				break;
			}
			
			
			
			
				/*
				current = t.getToken(x);
				
				
				if(current.name.contentEquals("CONST"))
				{
					for(int i = x; i < t.getListSize() - 1; i++)
					{
						if(t.getToken(i).name.equals(";"))
						{
							x = i + 1;
							break;
						}
					}
				}
				
				if(current.name.equals(";"))
				{
					//break;
					k++;
					while(stack.size() > 1) {
						
						
					q = new Quad(stack.get(stack.size()-2).name,stack.get(stack.size()-3).name,stack.get(stack.size()-1).name,"nop");
					quadList.addQuad(q);
					
					stack.pop();
					stack.pop();
					stack.pop();
					
					
					}
					//if(k == 3) {
						//break;}
				}
				
				if(isOper(current))
				{
					//System.out.println("WORK" + x);
					next = t.getToken(x+1);
					prev = t.getToken(x-1);
					
					if(precedence(start,current) != 2)
					{
						if(!stack.peek().equals(prev))
							stack.push(prev);
						stack.push(current);
						stack.push(next);
						
							
					}
					else
					{
						//System.out.println("REDUCE");
						//q = new Quad(start.name,t.getToken(findTok(t,start) - 1).name,t.getToken(findTok(t,start) + 1).name,"T1");
						q = new Quad(stack.get(stack.size()-2).name,stack.get(stack.size()-3).name,stack.get(stack.size()-1).name, getTemp());
						//q.printQuad();
						quadList.addQuad(q);
						stack.pop();
						stack.pop();
						stack.pop();
						stack.push(new Token(getTemp(),"$" + getTemp()));
						stack.push(current);
						stack.push(next);
						if(t.getToken(x+2).name.equals(";"))
						{
							temp++;
							q = new Quad(stack.get(stack.size()-2).name,stack.get(stack.size()-3).name,stack.get(stack.size()-1).name, getTemp());
							quadList.addQuad(q);
							//printStack();
							stack.pop();
							stack.pop();
							stack.pop();
							stack.push(new Token(getTemp(),"$" + getTemp()));
						}
						temp++;
						ptemp++;
						
							
					}
						
					
					start = t.getToken(x);
					//current = t.getToken(x);
				
						
					
				}
				
				
				*/
			}
		
			//printStack();
			//quadList.printQuadList();
			
			
			
		
	
		//printStack();
		quadList.printQuadList();
		generateAssembly(quadList);
		
		
	}
	
	
	public void generateAssembly(QuadList list)
	{
		//System.out.println("mov ax,@data");
		//System.out.println("mov ds,ax");
		for(int x = 0; x < list.getListSize(); x++)
		{
			if(list.getQuad(x).operator.contentEquals("="))
			{
				System.out.println("mov eax," + list.getQuad(x).operand2);
				System.out.println("mov " + list.getQuad(x).operand1 + ",eax");
			}
			if(list.getQuad(x).operator.contentEquals("+"))
			{
				System.out.println("mov eax," + list.getQuad(x).operand1);
				System.out.println("add eax," + list.getQuad(x).operand2);
				System.out.println("mov " + list.getQuad(x).operand3 + ",eax");
			}
			if(list.getQuad(x).operator.contentEquals("*"))
			{
				System.out.println("mov eax," + list.getQuad(x).operand1);
				System.out.println("mul " + list.getQuad(x).operand2);
				System.out.println("mov " + list.getQuad(x).operand3 + ",eax");
			}
			if(list.getQuad(x).operator.contentEquals("-"))
			{
				System.out.println("mov eax," + list.getQuad(x).operand1);
				System.out.println("sub eax," + list.getQuad(x).operand2);
				System.out.println("mov " + list.getQuad(x).operand3 + ",eax");
			}
			if(list.getQuad(x).operator.contentEquals("/"))
			{
				System.out.println("mov edx, 0");
				System.out.println("mov eax, " + list.getQuad(x).operand1);
				System.out.println("mov ebx, " +  list.getQuad(x).operand2);
				System.out.println("div ebx");
				System.out.println("mov " + list.getQuad(x).operand3 + ", eax");
				
			}
			
				
		}
	}
	
	
	public boolean isOper(Token t)
	{
		if(t.type.equals("<assign>") || t.type.equals("<addop>") ||  t.type.equals("$lp") || t.type.equals("$rp") || t.type.equals("<mop>"))
		{
			return true;
		}
		return false;
	}
	
	public boolean isBinOp(Token t)
	{
		if(t.type.equals("<assign>") || t.type.equals("<addop>") || t.type.equals("<mop>"))
		{
			return true;
		}
		return false;
	}
	public boolean isNonTerm(Token t)
	{
		if(t.type.contentEquals("<var>") || t.type.contentEquals("<integer>"))
			return true;
		return false;
	}
	
	
	public void printStack()
	{
		for(int x = stack.size() - 1; x >= 0; x--)
		{
			System.out.println(stack.get(x).name);
		}
	}
	
	public void printOpStack()
	{
		for(int x = opstack.size() - 1; x >= 0; x--)
		{
			System.out.println(opstack.get(x).name);
		}
	}
	
	
	//public int toInt(String op)
	//{
		
	//}
	
	
	
	
	
	
	
	


}
