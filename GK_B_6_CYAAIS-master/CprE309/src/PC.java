/**
 * 
 * @author Grant Duncan
 *
 */


public class PC implements java.io.Serializable{
	private int row;
	private int col;
	private char underPC;
	
	private int health;
	private int stress;
	private double GPA;
	private int cash;
	public int level;
	private int xp;
	private int hour;
	private int minute;
	private String mins;

	public PC(int row, int col) {
		this.row = row;
		this.col = col;
		underPC='^';
		
		health=100;
		stress=50;
		GPA=4.0;
		cash=100;
		level=1;
		xp=0;
		hour=12;
		minute=1;
		mins="01";
	}
	
	public void placePC(int row, int col){
		this.row=row;
		this.col=col;
	}
	
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public char getUnderPC(){
		return underPC;
	}
	public void setUnderPC(char underpc){
		underPC=underpc;
	}
	public int getHealth(){
		return health;
	}
	public int getStress(){
		return stress;
	}
	public double getGPA(){
		return GPA;
	}
	public int getXP(){
		return xp;
	}
	public void giveXP(int amount){
		xp=xp+amount;
		if(xp>=100){
			level++;
			xp=0+(xp-100);
		}
	}
	public void printStats(){
		System.out.println("Health: "+health+"    Stress: "+stress+"    GPA: "+GPA + "    Cash: "+cash+ "    level: "+level+"    XP: "+xp+"    Time: "+hour+":"+mins);
	}
	public void update(){
		minute++;
		if(minute==60){
			minute=0;
			hour++;
		}
		if(hour==13){
			hour=1;
		}
		if(minute<10)
			mins="0"+minute;
		else
			mins=""+minute;
		
		if (minute%10==0)
			health--;
		if(minute%5==0)
			stress++;
		
	}
	public void PCLoc(){
		System.out.println("Row: "+getRow()+" Col: "+getCol());
	}
}
