
public class Symbol {
	private String token;
	private String Class;
	private String value;
	private int address;
	private String segment;
	
	public Symbol(String t, String c, String v, int a, String s)
	{
		token = t;
		Class = c;
		value = v;
		address = a;
		segment = s;
	}
	public Symbol(String t, String c, int a, String s)
	{
		token = t;
		Class = c;
		address = a;
		segment = s;
		value = " ";
	}
	
	public void printSymbol()
	{
		System.out.print(token + "         ");
		System.out.print(Class + "         ");
		System.out.print(value + "         ");
		//if(value.equals(" "));
			//System.out.print("           ");
		System.out.print(address + "       ");
		System.out.print(segment + "       ");
	}
	

}
