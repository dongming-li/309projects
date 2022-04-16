package Enemy;

/**
 *
 * @author Aaron
 */
public interface GenericEnemy {
    
    public String type(); //returns the type of enemy
    public int getHealth(); //returns health
    public void takeDamage(int damage); //changes the enemy health based on damage taken
    public int getDifficultyLevel(); //returns the difficulty level
    public void setDifficultyLevel(); //sets the difficulty based on the number of heroes
    public int attack(); //the enemy makes an attack
    public void checkAlive(); //checks if enemy is alive or not
    public void setExpReward();
    
}
