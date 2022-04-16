package units;

public abstract class Unit {
	private final int unitCost;
	private Position unitPosition;
	private int unitVisionRange;
	private int unitDefense;
	private int unitAttack;
	private int unitAttackRange;
	private Player unitOwner;
	private int currentHP;
	private int maxHP;
	private int unitMovementRange;
	
	//method to perform special ability
	//method to get info on special ability
	//level variable

	public int getCost(){
		return unitCost;
	}
	
	public void specialAbility() {
	}
	
	}

	//upgrade with player points
	public void upgrade(){
	}
}
