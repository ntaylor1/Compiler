import java.util.ArrayList;

public class SymbolTable {
	
	
	ArrayList<Symbol> table = new ArrayList<Symbol>();
	private int stateTable[][];
	boolean finished = false;
	private int nextState;
	private int knt = 0;
	public SymbolTable()
	{
		stateTable = new int[16][8];
		stateTable[0][1] = 1;
		stateTable[1][1] = 2;
		stateTable[2][1] = 3;
		stateTable[3][1] = 4;
		stateTable[3][2] = 8;
		stateTable[3][3] = 10;
		stateTable[4][1] = 5;
		stateTable[5][1] = 6;
		stateTable[6][1] = 7;
		stateTable[7][1] = 4;
		stateTable[7][2] = 3;
		stateTable[8][1] = 9;
		stateTable[9][1] = 8;
		stateTable[9][2] = 3;
		stateTable[10][1] = 10;
		stateTable[10][2] = 11;
		stateTable[10][3] = 12;
		stateTable[11][1] = 10;
		stateTable[11][2] = 12;
	}

	
	
	public void buildTable(TokenList t)
	{
		int addrknt = 0;
		Token curr = t.getToken(knt);
		do{
			switch(nextState) {
			case 0: 
				if(curr.type.equals("$class")) {
					nextState = stateTable[0][1];
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else {
					finished = true;
					System.out.println("Error, no class");
				}
				break;
			case 1:
				if(curr.type.equals("<var>")) {
					nextState = stateTable[1][1];
					table.add(new Symbol(curr.name,"<Program name>",0,"      CS"));
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else {
					finished = true;
					System.out.println("Error, no class identifier");
				}
				break;
			case 2: 
				if(curr.type.equals("$lb")) {
					nextState = stateTable[2][1];
					System.out.println("work3");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else
				{
					finished = true;
					System.out.println("Error, no left brace after class identifier");
				}
				break;
			case 3:
				if(curr.type.equals("$const")) {
					nextState = stateTable[3][1];
					System.out.println("work4const");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else if(curr.type.equals("$var")) {
					nextState = stateTable[3][2];
					System.out.println("work4var");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else
				{
					nextState = stateTable[3][3];
					System.out.println("work4else");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
					
				//finished = true;
			//	System.out.println("Reached point");
				break;
			case 4:
				if(curr.type.equals("<var>")) {
					nextState = stateTable[4][1];
					System.out.println("work5var");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				break;
			case 5:
				if(curr.type.equals("<assign>")) {
					nextState = stateTable[5][1];
					System.out.println("work6assign");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				break;
			case 6:
				if(curr.type.equals("<integer>")) {
					nextState = stateTable[6][1];
					System.out.println("work7int");
					table.add(new Symbol(t.getToken(knt-2).name,"      Constvar",(curr.name + "   "),addrknt,"     DS"));
					addrknt += 2;
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				break;
			case 7:
				if(curr.type.equals("<comma>")) {
					nextState = stateTable[7][1];
					System.out.println("work4comma");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else if(curr.type.equals("<semi>")) {
					nextState = stateTable[7][2];
					System.out.println("work4const");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				break;
			case 8:
				if(curr.type.equals("<var>")) {
					nextState = stateTable[8][1];
					System.out.println("work9var");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				break;
			case 9:
				if(curr.type.equals("<comma>")) {
					nextState = stateTable[9][1];
					System.out.println("work8comma");
					table.add(new Symbol(t.getToken(knt - 1).name,"      Var","     ?    ",addrknt,"     DS"));
					addrknt += 2;
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else if(curr.type.equals("<semi>")) {
					nextState = stateTable[9][2];
					System.out.println("work3semi");
					table.add(new Symbol(t.getToken(knt - 1).name,"      Var","     ?    ",addrknt,"     DS"));
					addrknt += 2;
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				break;
			case 10:
				if(curr.type.equals("<integer>")) {
					nextState = stateTable[10][2];
					System.out.println("work11integer");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else if(curr.type.equals("$rb")) {
					nextState = stateTable[10][3];
					System.out.println("work12eof");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else
				{
					nextState = stateTable[10][1];
					System.out.println("work10else");
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
					
				}
				break;
			case 11:
				if(curr.type.equals("$rb")) {
					nextState = stateTable[11][2];
					System.out.println("work11eof");
					table.add(new Symbol(t.getToken(knt-1).name,"  Numeric Lit",(t.getToken(knt-1).name + "   "),addrknt,"    DS"));
					addrknt += 2;
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				else
				{
					nextState = stateTable[11][1];
					System.out.println("work10else");
					table.add(new Symbol(t.getToken(knt-1).name,"  Numeric Lit",(t.getToken(knt-1).name + "   "),addrknt,"    DS"));
					addrknt += 2;
					if(knt < t.getListSize() - 1)
						knt++;
					curr = t.getToken(knt);
				}
				break;
			case 12:
				finished = true;
				break;
					
					
				
				
				
			}
			//finished = true;
		}while(!finished);
		
		
		System.out.print("Token           ");
		System.out.print("Class           ");
		System.out.print("Value        ");
		System.out.print("Address      ");
		System.out.print("Segment      ");
		System.out.println();
		for(int x = 0; x < table.size(); x++)
		{
			table.get(x).printSymbol();
			System.out.println();
		}
	}
	
	

}


