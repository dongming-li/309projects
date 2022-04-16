package Enemy;

/**
 *
 * @author aaronhudson
 */
import java.util.concurrent.ThreadLocalRandom;

public class Boss implements GenericEnemy {

    public int health;
    public int damageTaken;
    public int difficulty;
    //public int attackType;
    public boolean alive;
    public int expReward;
    public int numHeroes = 1; //test.numHeroes; 
    public String enemyType;
    
    public Boss(){  
        setDifficultyLevel(); 
        setExpReward();
        health = 50 * this.difficulty;
        this.alive = true;
        this.enemyType = "boss";
    }
    
    public void setExpReward(){ //experience awarded to players when the boss is killed
        expReward = this.difficulty * 100;
    }
    
    @Override
    public String type() {
        return this.enemyType;
    }
    
    @Override
    public int getHealth() { //returns the current health of the boss
        return this.health;
    }

    @Override
    public void takeDamage(int damage){ //subtracts damage that the boss takes from its health
        if (damage > this.health){
            damage = this.health;
        }
        this.health -= damage; //updates health to account for damage
        System.out.println(type() + " takes " + damage + " damage!");
        checkAlive(); //checks to see whether or not the boss is dead
    }
    
    @Override
    public int getDifficultyLevel() {
        return this.difficulty;
    }

    @Override
    public void setDifficultyLevel() { //sets the difficulty of the boss based on the number of players
        this.difficulty = numHeroes;
    }
    
    @Override 
    public int attack(){
        //randomly generates the type of attack, will need to make the algorithm
        //a little more advanced or make some kind of queueing system
        int attackType = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        
        if (attackType == 1){ //Single target
            int damage = ThreadLocalRandom.current().nextInt(8, 10 + 1); //damage between 8 and 10 for ST
            String target = "warrior"; //needs to be updated to target
            System.out.println("Boss attacks " + target + " for " + damage);
            return damage;
        }
        
        else if (attackType == 2){ //AoE attack
            int damage = ThreadLocalRandom.current().nextInt(3, 6 + 1); //damage between 3 and 6 for AoE
            System.out.println("Boss does an AoE attack for " + damage);
            return damage;
        }
        return 0;
    }
    
    @Override
    public void checkAlive() { //checks if the boss is alive or not
        if (this.health == 0){
            this.alive = false;
            System.out.println("The " + type() + " has been defeated!");
            System.out.println("You gain " + this.expReward + " experience.");
        }
    }
    
}