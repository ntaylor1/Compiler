import java.util.ArrayList;

public class QuadList {
	
	ArrayList<Quad> quadList = new ArrayList<>();
	
	
	public void addQuad(Quad q)
	{
		quadList.add(q);
	}
	
	
	public Quad getQuad(int index)
	{
		return quadList.get(index);
	}
	
	
	public int getListSize()
	{
		return quadList.size();
	}
	
	public void printQuadList()
	{
		for(int x = 0; x < quadList.size(); x++)
		{
			System.out.println(quadList.get(x).operator + ", " + quadList.get(x).operand1  + ", " + quadList.get(x).operand2 + ", " + quadList.get(x).operand3);
		}
	}
	
	

}
