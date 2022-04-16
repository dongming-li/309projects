/**
 * 
 * @author Grant Duncan
 *
 */
package gameMain;

/**
 * The Class mapNode.
 */
public class mapNode implements java.io.Serializable{

	/** The symbol. */
	char symbol = ' ';
	
	/** The under item. */
	//items item = new items();
	char underItem;
	
	/** The is walkable. */
	boolean isWalkable;
	
	/** The interactable. */
	boolean interactable;
	
	/** The pc. */
	//players
	static char PC = '@';
	
	/** The bully. */
	static char BULLY = '!';
	
	/** The student. */
	static char STUDENT = '|';
	
	/** The security. */
	static char SECURITY = '+';
	
	/** The bus. */
	static char BUS = '=';
	
	/** The walkspace. */
	static char WALKSPACE = 'n';
	
	/** The hurtbully. */
	static char HURTBULLY ='H';
	
	/** The hurtguard. */
	static char HURTGUARD ='L';
	
	/** The hurtstudent. */
	static char HURTSTUDENT ='M';
	
	/** The grass. */
	//terrain
	static char GRASS = '`';
	
	/** The building. */
	static char BUILDING = 'b';
	
	/** The edge. */
	static char EDGE = '*';
	
	/** The busstop. */
	static char BUSSTOP = '{';
	
	/** The path. */
	static char PATH = 's';
	
	/** The door. */
	static char DOOR = '/';
	
	/** The edge door. */
	static char EDGE_DOOR='[';
	
	/** The road. */
	static char ROAD = 'r';

	//items 
	
	/**
	 * Instantiates a new map node.
	 */
	public mapNode() {
	}

	/**
	 * Sets the symb.
	 *
	 * @param symbol the new symb
	 */
	public void setSymb(char symbol) {
		this.symbol = symbol;
		
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Checks if is walkable.
	 *
	 * @return true, if is walkable
	 */
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
		return isWalkable=(symbol==GRASS||symbol==PATH||symbol==DOOR||symbol==BUSSTOP||symbol==PC||symbol==BULLY||symbol==STUDENT||symbol==SECURITY||symbol==BUS||symbol==ROAD||symbol==WALKSPACE||symbol==EDGE_DOOR);
		//return isWalkable=(symbol==GRASS||symbol==PATH||symbol==DOOR||symbol==BUSSTOP||symbol==PC||symbol==ROAD||symbol==WALKSPACE||symbol==EDGE_DOOR);
	}
	
	/**
	 * Checks if is PC walkable.
	 *
	 * @return true, if is PC walkable
	 */
	public boolean isPCWalkable(){
		return isWalkable=(symbol==GRASS||symbol==PATH||symbol==DOOR||symbol==BUSSTOP||symbol==PC||symbol==ROAD||symbol==WALKSPACE||symbol==EDGE_DOOR);
		
	}
	
	/**
	 * Checks if is student walkable.
	 *
	 * @return true, if is student walkable
	 */
	//students can't walk on grass, player, buildings, bullies, busses, security, or other students 
	public boolean isStudentWalkable(){
		//return isWalkable=(symbol=='/'||symbol=='$'||symbol=='-'||symbol=='&');
		return isWalkable=(symbol==DOOR||symbol==PATH||symbol==BUSSTOP||symbol==ROAD||symbol==WALKSPACE||symbol==EDGE_DOOR||symbol==STUDENT);
	}
	
	/**
	 * Interactable.
	 *
	 * @return true, if successful
	 */
	public boolean interactable(){
		return interactable=(symbol=='/'||symbol=='#');
		
	}
	
	

}
