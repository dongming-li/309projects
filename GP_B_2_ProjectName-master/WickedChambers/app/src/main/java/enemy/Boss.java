package enemy;

/**
 *
 * @author aaronhudson
 */
import android.graphics.Canvas;

import java.util.concurrent.ThreadLocalRandom;
/*
*   This class entails the implementation of a Boss object, which are a type of enemy the players will encounter within rooms. Bosses can have varying difficulty depending on the
*   desires of the GM player. Boss's are usually the more difficult of the enemies the characters will encounter within the game, while also dropping more valuable rewards.
*
 */
public class Boss implements GenericEnemy {

    public int health;
    public int damageTaken;
    public int difficulty;
    //public int attackType;
    public boolean alive;
    public int expReward;
    public int numHeroes = 1; //test.numHeroes; 
    public String enemyType;
    /*
    *   Basic Constructor for Boss object. Difficulty level is set to a default value, as is exp reward. Boss health is determined based on difficulty.
    *
    *
     */
    public Boss(){  
        setDifficultyLevel(); 
        setExpReward();
        health = 50 * this.difficulty;
        this.alive = true;
        this.enemyType = "Boss";
    }
    /*
    *   Returns the xp reward for defeating this boss to be awarded to the players in the game.
    *
    *   @return int xp reward
     */
    public void setExpReward(){ //experience awarded to players when the boss is killed
        expReward = this.difficulty * 100;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    /*
        *   Returns the boss type
        *
        *   @return String type
         */
    @Override
    public String type() {
        return this.enemyType;
    }
    /*
    *   Returns the health of the given Boss object. To be used in combat for determining boss health both in gameplay and graphics
    *
    *   @return int health of boss
     */
    @Override
    public int getHealth() { //returns the current health of the boss
        return this.health;
    }
    /*
    *   Reduces the health of the given Boss object by the given amount. Also determines if the boss has died due to the damage taken
    *
    *   @param int amount to reduce health of boss object by
     */
    @Override
    public void takeDamage(int damage){ //subtracts damage that the boss takes from its health
        if (damage > this.health){
            damage = this.health;
        }
        this.health -= damage; //updates health to account for damage
        System.out.println(type() + " takes " + damage + " damage!");
        checkAlive(); //checks to see whether or not the boss is dead
    }
    /*
    *   Returns the difficulty level of this boss
    *
    *   @return int difficulty level.
     */
    @Override
    public int getDifficultyLevel() {
        return this.difficulty;
    }
    /*
    *   Sets the difficulty level of the given boss to the given level
    *
    *   @param int desired difficulty
     */
    @Override
    public void setDifficultyLevel() { //sets the difficulty of the boss based on the number of players
        this.difficulty = numHeroes;
    }
    /*
    *   Simulates an attack by the given boss and determines the damage caused by this attack. Attacks can be either single target or AoE, which deal more and less damage than
    *   one another, respectively.
    *
    *   @return int amount of damage to be taken by player/players
     */
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
    /*
    *   Determines if the boss has died or not
    *
    *
     */
    @Override
    public void checkAlive() { //checks if the boss is alive or not
        if (this.health == 0){
            this.alive = false;
            System.out.println("The " + type() + " has been defeated!");
            System.out.println("You gain " + this.expReward + " experience.");
        }
    }
    
}