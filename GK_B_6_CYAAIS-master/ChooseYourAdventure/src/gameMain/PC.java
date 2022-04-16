/**
 * 
 * @author Grant Duncan
 *
 */

package gameMain;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The Class PC.
 */
@SuppressWarnings("serial")
public class PC implements java.io.Serializable{
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The under PC. */
	private char underPC;
	
	/** The username. */
	private static String username;
	
	/** The health. */
	private  int health;
	
	/** The stress. */
	private  int stress;
	
	/** The intellect. */
	private int intellect;
	
	/** The cash. */
	private  int cash;
	
	/** The level. */
	public   int level;
	
	/** The xp. */
	private static int xp;
	
	/** The bully count. */
	private static int bullyCount;
	
	/** The hour. */
	private static int hour;
	
	/** The minute. */
	private static int minute;
	
	/** The mins. */
	private String mins;
	
	/** The name. */
	private static String name;
	
	/** The gender. */
	private static int gender;
	
	/** The major. */
	private static int major;
	
	/** The club. */
	private static int club;
	
	/** The clothes. */
	private static int clothes;
	
	/** The speed. */
	private int speed;
	
	/** The atk value. */
	private int atkValue;

	/**
	 * Instantiates a new pc.
	 *
	 * @param row the row
	 * @param col the col
	 * @param map the map
	 */
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
		atkValue = 10;
	}
	
	/**
	 * Place PC.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void placePC(int row, int col){
		this.row=row;
		this.col=col;
	}
	
	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol(){
		return col;
	}
	
	/**
	 * Gets the under PC.
	 *
	 * @return the under PC
	 */
	public char getUnderPC(){
		return underPC;
	}
	
	/**
	 * Sets the under PC.
	 *
	 * @param underpc the new under PC
	 */
	public void setUnderPC(char underpc){
		underPC=underpc;
	}
	
	/**
	 * Gets the atk value.
	 *
	 * @return the atk value
	 */
	public int getAtkValue(){
		return atkValue;
	}
	
	/**
	 * Sets the atk value.
	 *
	 * @param value the new atk value
	 */
	public void setAtkValue(int value){
		atkValue=value;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username){
		PC.username=username;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public  int getHealth(){
		return health;
	}
	
	/**
	 * Sets the health.
	 *
	 * @param number the new health
	 */
	public void setHealth(int number) {
		this.health=number;
	}
	
	/**
	 * Gets the stress.
	 *
	 * @return the stress
	 */
	public int getStress(){
		return stress;
	}
	
	/**
	 * Sets the stress.
	 *
	 * @param number the new stress
	 */
	public void setStress(int number) {
		stress = number;
	}
	
	/**
	 * Gets the intellect.
	 *
	 * @return the intellect
	 */
	public  int getIntellect(){
		return intellect;
	}
	
	/**
	 * Sets the intellect.
	 *
	 * @param number the new intellect
	 */
	public void setIntellect(int number) {
		intellect = number;
	}
	
	/**
	 * Gets the xp.
	 *
	 * @return the xp
	 */
	public int getXP(){
		return xp;
	}
	
	/**
	 * Gets the bully count.
	 *
	 * @return the bully count
	 */
	public  int getBullyCount(){
		return bullyCount;
	}
	
	/**
	 * Increase bully count.
	 */
	public void increaseBullyCount() {
		bullyCount++;
	}
	
	/**
	 * Gets the cash.
	 *
	 * @return the cash
	 */
	public  int getCash(){
		return cash;
	}
	
	/**
	 * Sets the cash.
	 *
	 * @param number the new cash
	 */
	public void setCash(int number) {
		cash += number;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		PC.name=name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(int gender){
		PC.gender=gender;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public int getGender(){
		return gender;
	}
	
	/**
	 * Sets the major.
	 *
	 * @param major the new major
	 */
	public void setMajor(int major){
		PC.major=major;
	}
	
	/**
	 * Gets the major.
	 *
	 * @return the major
	 */
	public int getMajor(){
		return major;
	}
	
	/**
	 * Sets the club.
	 *
	 * @param club the new club
	 */
	public void setClub(int club){
		PC.club=club;
	}
	
	/**
	 * Gets the club.
	 *
	 * @return the club
	 */
	public int getClub(){
		return club;
	}
	
	/**
	 * Sets the clothes.
	 *
	 * @param clothes the new clothes
	 */
	public void setClothes(int clothes){
		PC.clothes=clothes;
	}
	
	/**
	 * Gets the clothes.
	 *
	 * @return the clothes
	 */
	public int getClothes(){
		return clothes;
	}
	
	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * Give XP.
	 *
	 * @param amount the amount
	 */
	public void giveXP(int amount){
		xp=xp+amount;
		if(xp>=100){
			level++;
			xp=0+(xp-100);
		}
	}
	
	/**
	 * Deal damage.
	 *
	 * @param amount the amount
	 */
	public void dealDamage(int amount){
		health-=amount;
	}
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed){
		this.speed=speed;
	}
	
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * Prints the stats.
	 */
	public void printStats(){
		System.out.println("Health: "+health+"    Stress: "+stress+"    Intellect: "+intellect + "    Cash: "+cash+ "    level: "+level+"    XP: "+xp+"    Time: "+hour+":"+mins);
	}
	
	/**
	 * Update.
	 */
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
	
	/**
	 * PC loc.
	 */
	public void PCLoc(){
		System.out.println("Row: "+getRow()+" Col: "+getCol());
	}
	
	/**
	 * Attack.
	 *
	 * @param direction the direction
	 * @param map the map
	 */
	public void attack(String direction, map map){
		NPC target;
		if(direction.equals("Up")){
			target =map.getNPCOnNode(this.row-1, this.col);
			if(target!=null){
				target.setHealth(-(this.atkValue));
				if(target.isKO())
					setCash(target.getCash());
			}
		}
		else if(direction.equals("Down")){
			target =map.getNPCOnNode(this.row+1, this.col);
			if(target!=null){
				target.setHealth(-(this.atkValue));
				if(target.isKO())
					setCash(target.getCash());
			}
		}
		else if(direction.equals("Left")){
			target = map.getNPCOnNode(this.row, this.col-1);
			if(target!=null){
				target.setHealth(-(this.atkValue));
				if(target.isKO())
					setCash(target.getCash());
			}
		}
		else if(direction.equals("Right")){
			target =map.getNPCOnNode(this.row, this.col+1);
			if(target!=null){
				target.setHealth(-(this.atkValue));
				if(target.isKO())
					setCash(target.getCash());
			}
		}
	}
	
	/**
	 * Game over.
	 *
	 * @return true, if successful
	 */
	public boolean gameOver(){
		if (this.getHealth() <= 0) {
			infoBox("You were knocked out.\nGAME OVER!", "Death Screen");
			return true;
		} else if (this.getStress() == 100) {
			infoBox("You have had a mental breakdown\nGAME OVER!", "Death Screen");
			return true;
		} else if (this.getIntellect() < 0) {
			infoBox("You have failed out of college", "Death Screen");
			return true;
		}
		return false;
	}
	
	/**
	 * Info box.
	 *
	 * @param infoMessage the info message
	 * @param titleBar the title bar
	 */
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane pane = new JOptionPane(infoMessage);
		JDialog d = pane.createDialog((JFrame)null, titleBar);
		d.setLocation(0,0);
		d.setVisible(true);
		
		
		
	}

}
