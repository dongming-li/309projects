/**
 * 
 * @author Grant Duncan
 *
 */

package main;

@SuppressWarnings("serial")
public class PC implements java.io.Serializable{
	private int row;
	private int col;
	private char underPC;
	
	private static String username;
	private  int health;
	private  int stress;
	private int intellect;
	private  int cash;
	public   int level;
	private static int xp;
	private static int bullyCount;
	private static int hour;
	private static int minute;
	private String mins;
	private static String name;
	private static int gender;
	private static int major;
	private static int club;
	private static int clothes;
	private int speed;

	public PC(int row, int col, map map) {
		this.row = row;
		this.col = col;
		underPC=map.getRowCol(row, col);
		
		health=100;
		stress=50;
		intellect=25;
		cash=100;
		level=1;
		xp=0;
		hour=12;
		minute=1;
		mins="01";
		name= "";
		gender=0;
		major=0;
		club=0;
		clothes=0;
		speed = 10;
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
	public void setUsername(String username){
		PC.username=username;
	}
	public static String getUsername(){
		return username;
	}
	public  int getHealth(){
		return health;
	}
	public void setHealth(int number) {
		
	}
	public int getStress(){
		return stress;
	}
	public void setStress(int number) {
		stress = number;
	}
	public  int getIntellect(){
		return intellect;
	}
	public void setIntellect(int number) {
		intellect = number;
	}
	public  int getBullyCount(){
		return bullyCount;
	}
	public void increaseBullyCount() {
		bullyCount++;
	}
	public static int getXP(){
		return xp;
	}
	public  int getCash(){
		return cash;
	}
	public void setCash(int number) {
		cash = number;
	}
	
	public void setName(String name){
		PC.name=name;
	}
	public static String getName(){
		return name;
	}
	public void setGender(int gender){
		PC.gender=gender;
	}
	public static int getGender(){
		return gender;
	}
	public void setMajor(int major){
		PC.major=major;
	}
	public static int getMajor(){
		return major;
	}
	public void setClub(int club){
		PC.club=club;
	}
	public static int getClub(){
		return club;
	}
	public void setClothes(int clothes){
		PC.clothes=clothes;
	}
	public static int getClothes(){
		return clothes;
	}
	public static int getHour() {
		return hour;
	}
	public void giveXP(int amount){
		xp=xp+amount;
		if(xp>=100){
			level++;
			xp=0+(xp-100);
		}
	}
	public void dealDamage(int amount){
		health-=amount;
	}
	
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public int getSpeed(){
		return speed;
	}
	public void printStats(){
		System.out.println("Health: "+health+"    Stress: "+stress+"    Intellect: "+intellect + "    Cash: "+cash+ "    level: "+level+"    XP: "+xp+"    Time: "+hour+":"+mins);
	}
	public void update(){
		minute++;
		if(minute==60){
			minute=0;
			hour++;
		}
		if(hour==24){
			hour=0;
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
