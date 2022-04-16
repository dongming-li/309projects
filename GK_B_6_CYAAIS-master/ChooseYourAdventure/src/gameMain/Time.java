package gameMain;

/**
 * The Class Time.
 */
public class Time {

	/** The seconds. */
	private int seconds;
	
	/** The minutes. */
	private int minutes;
	
	/** The hours. */
	private int hours;
	
	/** The day. */
	private int day;
	
	/** The week. */
	private int week;
	
	/** The name date. */
	private String nameDate;
	
	/** The sunday. */
	static int SUNDAY = 0;
	
	/** The monday. */
	static int MONDAY = 1;
	
	/** The tuesday. */
	static int TUESDAY = 2;
	
	/** The wednesday. */
	static int WEDNESDAY = 3;
	
	/** The thursday. */
	static int THURSDAY= 4;
	
	/** The friday. */
	static int FRIDAY = 5;
	
	/** The saturday. */
	static int SATURDAY =6;
	
	/** The sun. */
	static String SUN = "Sunday";
	
	/** The mon. */
	static String  MON = "Monday";
	
	/** The tues. */
	static String  TUES = "Tuesday";
	
	/** The wed. */
	static String WED = "Wednesday";
	
	/** The thurs. */
	static String THURS= "Thursday";
	
	/** The fri. */
	static String FRI = "Friday";
	
	/** The sat. */
	static String SAT ="Saturday";
	
	/** The time. */
	boolean time =false;
	
	/**
	 * Instantiates a new time.
	 *
	 * @param startHour the start hour
	 * @param startMinutes the start minutes
	 * @param startSeconds the start seconds
	 */
	public Time(int startHour, int startMinutes, int startSeconds ) {
		day=MONDAY;
		setNameDate(MON);
		hours=startHour;
		minutes=startMinutes;
		seconds=startSeconds;
		setWeek(1);
		
	}
	
	/**
	 * Update time.
	 */
	public void updateTime() {
		seconds+=4;
	}
	
	/**
	 * Calculate time.
	 */
	public void calculateTime() {
		if(seconds>10) {
			seconds=0;
			minutes++;
		}
		if(minutes>59) {
			minutes=0;
			hours+=100;
		}
		
		if(hours>2300) {
			 if(hours>2400) {
			hours=100;
			time=false;
		}
			 else if(!time) {
			calculateDate();
			time=true;
			if(day==SUNDAY){
				setWeek(getWeek() + 1);
			}
			
			 }
		}
		
		
	}
	
	/**
	 * Calculate date.
	 */
	public void calculateDate() {
		if(day==SUNDAY) {
			day=MONDAY;
			nameDate=MON;
		}
		else if(day==MONDAY) {
			day=TUESDAY;
			nameDate=TUES;
		}
		else if(day==TUESDAY) {
			day=WEDNESDAY;
			nameDate=WED;
		}
		else if(day==WEDNESDAY) {
			day=THURSDAY;
			nameDate=THURS;
		}
		else if(day==THURSDAY) {
			day=FRIDAY;
			nameDate=FRI;
		}
		else if(day==SATURDAY) {
			day=SUNDAY;
			nameDate=SUN;
		}
		
	}
	
	/**
	 * Gets the hours.
	 *
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}
	
	/**
	 * Sets the hours.
	 *
	 * @param hours the new hours
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	/**
	 * Gets the minutes.
	 *
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * Sets the minutes.
	 *
	 * @param minutes the new minutes
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	/**
	 * Gets the seconds.
	 *
	 * @return the seconds
	 */
	public int getSeconds() {
		return seconds;
	}
	
	/**
	 * Sets the seconds.
	 *
	 * @param seconds the new seconds
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Sets the day.
	 *
	 * @param day the new day
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * Gets the name date.
	 *
	 * @return the name date
	 */
	public String getNameDate() {
		return nameDate;
	}
	
	/**
	 * Sets the name date.
	 *
	 * @param nameDate the new name date
	 */
	public void setNameDate(String nameDate) {
		this.nameDate = nameDate;
	}
	
	/**
	 * Gets the week.
	 *
	 * @return the week
	 */
	public int getWeek()
	{
			return week;
	}
	
	/**
	 * Sets the week.
	 *
	 * @param week the new week
	 */
	public void setWeek(int week)
	{
			this.week = week;
	}
}
