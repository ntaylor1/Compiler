
public class Quad {
	
	String operator;
	String operand1;
	String operand2;
	String operand3;
	
	public Quad(String o, String o1, String o2, String o3)
	{
		
		operator = o;
		operand1 = o1;
		operand2 = o2;
		operand3 = o3;
		
	}
	
	public void printQuad()
	{
		System.out.println(operator + ", " + operand1 + ", " + operand2 + ", " + operand3);
	}
	

}
