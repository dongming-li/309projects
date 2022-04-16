/**
 * 
 * @author Grant Duncan
 *
 */
import java.util.ArrayList;

public class items implements java.io.Serializable{
ArrayList<char[]> item = new ArrayList();

	char symbol;
	int health;
	int stress;
	double GPA;
	int cash;
	
	public items(){
		
	}
	
	public void addItem(char symbol,int healthVal, int stressVal, double GPAVal, int cashVal){
		this.symbol= symbol;
		this.health=healthVal;
		this.stress=stressVal;
		this.GPA= GPAVal;
		this.cash= cashVal;
		char[] itemVal ={symbol, (char) health,(char) stress,(char) GPA,(char) cash};
		item.add(itemVal);
	}
	
	public ArrayList<char[]> getItems(){
		return item;
	}
	
	public void addHealth(){
		addItem('+',5,0,0,0);
	}
	public void addStress(){
		addItem('-',0,5,0,0);
	}
	public void addGPA(){
		addItem('&',0,0,.1,0);
	}
	public void addCash(){
		addItem('$',0,0,0,5);
	}
	
	public char getSymbol(){
		return symbol;
	}
}
