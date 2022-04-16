/**
 * 
 * @author Grant Duncan
 *
 */
package main;

public class mapNode implements java.io.Serializable{

	char symbol = ' ';
	//items item = new items();
	char underItem;
	boolean isWalkable;
	boolean interactable;
	
	//players
	static char PC = '@';
	static char BULLY = '!';
	static char STUDENT = '|';
	static char SECURITY = '+';
	static char BUS = '=';
	
	//terrain
	static char GRASS = '`';
	static char BUILDING = 'b';
	static char EDGE = '*';
	static char BUSSTOP = '#';
	static char PATH = 's';
	static char DOOR = '/';
	static char ROAD = 'r';

	//items 
	
	public mapNode() {
	}

	public void setSymb(char symbol) {
		this.symbol = symbol;
		
	}

	public char getSymbol() {
		return symbol;
	}
	/*
	public boolean hasItem(){
		return (item!=null);
	}

	public void addItem(items item){
		this.item=item;
		underItem=symbol;
		symbol=item.getSymbol();
	
	}
	public void removeItem(){
		this.item=null;
		symbol=underItem;
	}
	*/
	public boolean isWalkable(){
		//return isWalkable=(symbol=='^'||symbol=='/'||symbol=='$'||symbol=='+'||symbol=='-'||symbol=='&'||symbol=='@');
		return isWalkable=(symbol==GRASS||symbol==PATH||symbol==DOOR||symbol==BUSSTOP||symbol==PC||symbol==BULLY||symbol==STUDENT||symbol==SECURITY||symbol==BUS||symbol==ROAD);
		
	}
	//students can't walk on grass, player, buildings, bullies, busses, security, or other students 
	public boolean isStudentWalkable(){
		//return isWalkable=(symbol=='/'||symbol=='$'||symbol=='-'||symbol=='&');
		return isWalkable=(symbol==DOOR||symbol==PATH||symbol==BUSSTOP||symbol==ROAD);
	}
	
	public boolean interactable(){
		return interactable=(symbol=='/'||symbol=='#');
		
	}

}
