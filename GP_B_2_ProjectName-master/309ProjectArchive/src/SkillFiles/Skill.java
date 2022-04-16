package SkillFiles;

public class Skill
{

	private String skillID;
	private int skillNumber;
	private boolean skillIsUnlocked;
	private String skillDescription;

	/*
	 * Details the construction of a Skill object. A skill object holds the
	 * skills ID, the number it can be found in the skill tree, whether or not
	 * it has been unlocked, as well as a short description of what bonuses the
	 * skill provides.
	 * 
	 * @param String sID an identification for the given skill.
	 * 
	 * @param int sNumber an identification number for the given skill in
	 * relation to the skill tree.
	 * 
	 * @param boolean sUnlocked a boolean detailing whether or not the given
	 * skill has been unlocked by the character.
	 * 
	 * @param String sDescription a short description for the given skill.
	 * 
	 */
	public Skill(String sID, int sNumber, boolean sUnlocked, String sDescription) {
		skillID = sID;
		skillNumber = sNumber;
		skillIsUnlocked = sUnlocked; // by default should be false, but there
										// could be default skills for warriors,
										// rogues, mages, etc.
		skillDescription = sDescription;

	}

	/*
	 * Returns the ID of the given skill.
	 * 
	 * @return ID of given skill.
	 */
	public String getID() {
		return skillID;
	}

	/*
	 * Returns the ID number of the given skill
	 * 
	 * @return ID number of given skill
	 */
	public int getSkillNumber() {
		return skillNumber;
	}

	/*
	 * Returns whether or not the given skill has been unlocked
	 * 
	 * @return unlock status
	 */
	public boolean getUnlockedValue() {
		return skillIsUnlocked;
	}

	/*
	 * Unlocks the given skill
	 */
	public void unlock() {
		this.skillIsUnlocked = true;
	}

	/*
	 * Locks the given skill
	 */
	public void lock() {
		this.skillIsUnlocked = false;
	}

	/*
	 * Returns a short description of the given skill
	 * 
	 * @return description of the given skill
	 */
	public String getDescription() {
		return skillDescription;
	}

	/*
	 * Returns a string representation of a skill object
	 * 
	 * @return string description of skill object.
	 */
	public String toString() {
		return this.getClass().getName() + "\n" + "ID: " + skillID + "\n" + "Number: " + skillNumber + "\n"
				+ "Unlocked: " + skillIsUnlocked + "\n" + "Description: " + skillDescription;
	}

	/*
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

	/*
	 * Generates a skill tree for the Warrior class. The tree hierarchy is as
	 * follows: WR_00 | WR_01 | WR_02---| | | WR_03 WR_06 | | WR_04 WR_07 | |
	 * WR_05 WR_08
	 * 
	 * @return warrior skill tree
	 */
	public static BinarySkillTree generateWarriorSkillTree() {
		BinarySkillTree warriorTree = new BinarySkillTree(
				new Skill("WR_00", 0, true, "Path of the Warrior: +10 Strength, +5 Health"));
		// nodes will be leftNodes unless there are two nodes necessary
		// (branches)
		warriorTree.addNode(new Skill("WR_01", 1, false, "Path of the Warrior 2: +15 Strength, +10 Health"), false);
		warriorTree.getLeftChildSkillTree().addNode(
				new Skill("WR_02", 2, false, "Path of the Warrior 3: +10 Base Armor, +5 Strength, +5 Health"), false);
		warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("WR_03", 3, false, "Path of the Paladin: +15 Base Armor, +5 Health, Add ability: protect"),
				false);
		warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("WR_06", 6, false, "Path of the Berserker: +15 Strength, +5 Base Weapon Damage"), true); // creates
																													// first
																													// and
																													// only
																													// branch
		warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree()
				.addNode(new Skill("WR_04", 4, false, "Path of the Paladin 2: +20 Base Armor, +10 Health"), false);
		warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().addNode(
				new Skill("WR_07", 7, false, "Path of the Berserker 2: +15 Strength, +15 Base Weapon Damage"), false);
		warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree()
				.addNode(
						new Skill("WR_05", 5, false,
								"Path of the Paladin 3: +20 Health, +25 Base Armor, Add ability: Healing Spell"),
						false);
		warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree()
				.addNode(
						new Skill("WR_08", 8, false,
								"Path of the Berserker 3: +15 Strength, +20 Base Weapon Damage, Add ability: AoE Attack"),
						false);
		return warriorTree;
	}

	/*
	 * Generates a skill tree for the Mage class. The tree hierarchy is as
	 * follows: MG_00 | MG_01 | MG_02---| | | MG_03 MG_06 | | MG_04 MG_07 | |
	 * MG_05 MG_08
	 * 
	 * @return mage skill tree
	 */
	public static BinarySkillTree generateMageSkillTree() {
		BinarySkillTree mageTree = new BinarySkillTree(new Skill("MG_00", 0, true, "Path of the Mage: +10 Intellect"));
		// nodes will be leftNodes unless there are two nodes necessary
		// (branches)
		mageTree.addNode(new Skill("MG_01", 1, false, "Path of the Mage 2: +15 Intellect"), false);
		mageTree.getLeftChildSkillTree().addNode(new Skill("MG_02", 2, false, "Path of the Mage 3: +20 Intellect"),
				false);
		mageTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("MG_03", 3, false, "Path of the Warlock: +25 Intellect, +10 Base Spell Damage, -20 Health"),
				false);
		mageTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("MG_06", 6, false, "Path of the Healer: +5 Health, Add Ability: Healing Spell"), true); // creates
																													// first
																													// and
																													// only
																													// branch
		mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("MG_04", 4, false, "Path of the Warlock 2: +30 Intellect, +10 Base Spell Damage"), false);
		mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().addNode(
				new Skill("MG_07", 7, false, "Path of the Healer 2: +10 Health, Add Ability: Strong Healing Spell"),
				false);
		mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree()
				.addNode(
						new Skill("MG_05", 5, false,
								"Path of the Warlock 3: +50 Intellect, -10 Health, +10 Base Spell Damage, Add ability: AoE Attack"),
						false);
		mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree()
				.addNode(new Skill("MG_08", 8, false,
						"Path of the Healer 3: +10 Health, +5 Intellect, Add ability: Protect"), false);
		return mageTree;
	}

	/*
	 * Generates a skill tree for the Rogue class. The tree hierarchy is as
	 * follows: RG_00 | RG_01 | RG_02---| | | RG_03 RG_06 | | RG_04 RG_07 | |
	 * RG_05 RG_08
	 * 
	 * @return rogue skill tree
	 */
	public static BinarySkillTree generateRogueSkillTree() {
		BinarySkillTree rogueTree = new BinarySkillTree(new Skill("RG_00", 0, true, "Path of the Rogue: +10 Agility"));
		// nodes will be leftNodes unless there are two nodes necessary
		// (branches)
		rogueTree.addNode(new Skill("RG_01", 1, false, "Path of the Rogue 2: +10 Agility, +5 Health"), false);
		rogueTree.getLeftChildSkillTree().addNode(new Skill("RG_02", 2, false, "Path of the Rogue 3: +15 Agility"),
				false);
		rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("RG_03", 3, false,
						"Path of the Bowman: +15 Agility, +5 Base Weapon Damage, -20 Health, Add Ability: Ranged Attack"),
				false);
		rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("RG_06", 6, false,
						"Path of the Assassin: +15 Agility, +15 Base Weapon Damage, -20 Health, Add Ability: Backstab"),
				true); // creates first and only branch
		rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(
				new Skill("RG_04", 4, false, "Path of the Bowman 2: +20 Agility, +5 Base Weapon Damage"), false);
		rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().addNode(
				new Skill("RG_07", 7, false, "Path of the Assassin 2: +10 Agility, +20 Base Weapon Damage, -5 Health"),
				false);
		rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree()
				.addNode(new Skill("RG_05", 5, false,
						"Path of the Bowman 3: +20 Agility, +5 Base Weapon Damage, Add ability: Snipe"), false);
		rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree()
				.addNode(
						new Skill("RG_08", 8, false,
								"Path of the Assassin 3: +10 Agility, +20 Base Weapon Damage, Add ability: Dodge"),
						false);
		return rogueTree;
	}
}
