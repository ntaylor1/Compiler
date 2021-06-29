import java.util.ArrayList;
public class Token {

	String type;
	String name;


	public Token(String n, String t)
	{
		name = n;

		type = t;

	}

	public String getName(Token t)
	{
		return t.name;
	}

	public String getType(Token t)
	{
		return t.type;
	}











}
