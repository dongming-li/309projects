/**
 * 
 * @author Grant Duncan
 *
 */
public class mapNode implements java.io.Serializable{

	char symbol = ' ';
	items item = new items();
	char underItem;
	boolean isWalkable;

	public mapNode() {
	}

	public void setSymb(char symbol) {
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}
	
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
	
	public boolean isWalkable(){
		return isWalkable=(symbol=='^'||symbol=='/'||symbol=='$'||symbol=='+'||symbol=='-'||symbol=='&');
		
	}

}
