package skillfiles;

/**
 * A Skill is an unlockable token within the game that affects the stats of a character and the way the user can play the game. By completing rooms, characters are able to gain experience
 * points that they are able to spend on new skills.
 */
public class Skill
{

	private String skillID;
	private int skillNumber;
	public boolean skillIsUnlocked;
	private String skillDescription;
	private int HP, STR, INT, AGI;

	/**
	 * Details the construction of a Skill object. A skill object holds the
	 * skills ID, the number it can be found in the skill tree, whether or not
	 * it has been unlocked, as well as a short description of what bonuses the
	 * skill provides.
	 * 
	 * @param sID an identification for the given skill.
	 * 
	 * @param sNumber an identification number for the given skill in
	 * relation to the skill tree.
	 * 
	 * @param sUnlocked a boolean detailing whether or not the given
	 * skill has been unlocked by the character.
	 * 
	 * @param sDescription a short description for the given skill.
	 * 
	 */
	public Skill(String sID, int sNumber, boolean sUnlocked, String sDescription, int HP, int STR, int INT, int AGI) {
		skillID = sID;
		skillNumber = sNumber;
		skillIsUnlocked = sUnlocked; // by default should be false, but there
										// could be default skills for warriors,
										// rogues, mages, etc.
		skillDescription = sDescription;
		this.HP = HP;
		this.STR = STR;
		this.INT = INT;
		this.AGI = AGI;
	}

	/**
	 *
	 * @return hp increase for this skill
	 */
	public int getHP()
	{
		return HP;
	}

	/**
	 *
	 * @return strength incrase for this skill
	 */
	public int getSTR()
	{
		return STR;
	}

	/**
	 *
	 * @return intellect increase for this skill
	 */
	public int getINT()
	{
		return INT;
	}

	/**
	 *
	 * @return agility increase for this skill
	 */
	public int getAGI()
	{
		return AGI;
	}



	/**
	 * Returns the ID of the given skill.
	 * 
	 * @return ID of given skill.
	 */
	public String getID() {
		return skillID;
	}

	/**
	 * Returns the ID number of the given skill
	 * 
	 * @return ID number of given skill
	 */
	public int getSkillNumber() {
		return skillNumber;
	}

	/**
	 * Returns whether or not the given skill has been unlocked
	 * 
	 * @return unlock status
	 */
	public boolean getUnlockedValue() {
		return skillIsUnlocked;
	}

	/**
	 * Unlocks the given skill
	 */
	public void unlock() {
		this.skillIsUnlocked = true;
	}

	/**
	 * Locks the given skill
	 */
	public void lock() {
		this.skillIsUnlocked = false;
	}

	/**
	 * Returns a short description of the given skill
	 * 
	 * @return description of the given skill
	 */
	public String getDescription() {
		return skillDescription;
	}

	/**
	 * Returns a string representation of a skill object
	 * 
	 * @return string description of skill object.
	 */
	public String toString() {
		return this.getClass().getName() + "\n" + "ID: " + skillID + "\n" + "Number: " + skillNumber + "\n"
				+ "Unlocked: " + skillIsUnlocked + "\n" + "Description: " + skillDescription;
	}

	/**
	 * Determines equality of two skill objects. Equality is determined by skill
	 * ID only, as logically no two skills will have the same skill ID.
	 * 
	 * @return whether or not the two skills are equal
	 */
	public boolean equals(Skill s) {
		if (s == null)
			return false;
		if (this.skillID.equals(s.getID()))
			return true;
		return false;
	}

}
