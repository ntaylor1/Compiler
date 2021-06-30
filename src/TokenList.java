import java.util.ArrayList;

public class TokenList {
	
	
	ArrayList<Token> tokenList = new ArrayList<>();
	
	
	
	public void addToken(Token t)
	{
		 tokenList.add(t);
    }
	
	public Token getToken(int index)
	{
		return tokenList.get(index);
	}
	
	public void printTokens()
	{
		for(int x = 0; x < tokenList.size(); x++)
		{
			
			
			System.out.println(tokenList.get(x).name + "          " + tokenList.get(x).type);
			
			//Token t = tokenList.get(x);
			//System.out.print(t.name + "          ");
			//System.out.print(t.type);
		}
		
	}
	
	
	public int getListSize()
	{
		return tokenList.size();
	}
	
	public ArrayList<Token> getTokenList()
	{
		return tokenList;
	}

}
