package proj;

public class main {

	public static void main(String[] args) {
		CharCreate first = new CharCreate('w');
		
		RoomCreate testy = new RoomCreate(1, 1, 550);
		
		System.out.println("class: " + first.getType());
		System.out.println("health: " + first.getHealth());
		System.out.println("intellect: " + first.getIntellect());
		System.out.println("strength: " + first.getStrength());
		System.out.println("agility: " + first.getAgility());
		System.out.println("level: " + first.getCharLevel());
		System.out.println("xp: " + first.getCharXP());
		System.out.println("Are you still alive: " + first.isAlive());
		
		System.out.println("stat change boys");
		
		first.addHealth(4);
		first.addIntellect(2);
		first.addStrength(-2);
		first.addAgility(3);
		
		System.out.println("class: " + first.getType());
		System.out.println("health: " + first.getHealth());
		System.out.println("intellect: " + first.getIntellect());
		System.out.println("strength: " + first.getStrength());
		System.out.println("agility: " + first.getAgility());
		System.out.println("level: " + first.getCharLevel());
		System.out.println("xp: " + first.getCharXP());
		System.out.println("Are you still alive: " + first.isAlive());
		
		System.out.println("time to Beat the room!!!!! \n");
		
		testy.isComplete = true;
		
		if(testy.isComplete = true){
			first.addCharXP(testy.xpReward);
		}
		System.out.println("class: " + first.getType());
		System.out.println("health: " + first.getHealth());
		System.out.println("intellect: " + first.getIntellect());
		System.out.println("strength: " + first.getStrength());
		System.out.println("agility: " + first.getAgility());
		System.out.println("level: " + first.getCharLevel());
		System.out.println("xp: " + first.getCharXP());
		System.out.println("Are you still alive: " + first.isAlive());
	
		
		
		System.out.println("time to die!!!! \n");
		
		first.addHealth(-7);
		System.out.println("class: " + first.getType());
		System.out.println("health: " + first.getHealth());
		System.out.println("intellect: " + first.getIntellect());
		System.out.println("strength: " + first.getStrength());
		System.out.println("agility: " + first.getAgility());
		System.out.println("Are you still alive: " + first.isAlive());
		
		
	}

}
