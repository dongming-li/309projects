package Enemy;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author aaronhudson
 */
public class Enemy implements GenericEnemy {
    
    public int health;
    public int difficulty;
    public boolean alive;
    public int expReward; 
    public String enemyType;
    public String[] typeList = {"Skeleton", "Slime", "Goblin", "Zombie", "Bandit"};
                
    //creates a new enemy object with type, health, difficulty.
    //type must be capitalized, ex. Skeleton, Zombie, Slime etc.
    public Enemy(String type, int health, int difficulty) throws IllegalArgumentException {
        boolean legal = false;
        for (String i : typeList) {
            if (type.equals(i)) {
                legal = true;
            }
        }
        if (!legal) throw new IllegalArgumentException("Enemy type not valid.");
        this.health = health;
        this.difficulty = difficulty;
        this.enemyType = type;
    }
    
    @Override
    public String type() {
        return this.enemyType;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > this.health) {
            damage = this.health;
        }
        this.health -= damage;
        System.out.println(type() + " takes " + damage + " damage!");
    }

    @Override
    public int getDifficultyLevel() {
        return this.difficulty;
    }

    @Override
    public void setDifficultyLevel() {
    }

    @Override
    public int attack() {
        int damage = 0;
        switch (type()){
            case "Skeleton":
                System.out.println(type() + " attacks!");
                damage = ThreadLocalRandom.current().nextInt(3, 7 + 1) * getDifficultyLevel();
                return damage;
            case "Slime":
                System.out.println(type() + " attacks!");
                damage = ThreadLocalRandom.current().nextInt(1, 3 + 1) * getDifficultyLevel();
                return damage;
            case "Goblin":
                System.out.println(type() + " attacks!");
                damage = ThreadLocalRandom.current().nextInt(2, 3 + 1) * getDifficultyLevel();
                return damage;
            case "Zombie":
                System.out.println(type() + " attacks!");
                damage = ThreadLocalRandom.current().nextInt(4, 6 + 1) * getDifficultyLevel();
                return damage;
            case "Bandit":
                System.out.println(type() + " attacks!");
                damage = ThreadLocalRandom.current().nextInt(5, 8 + 1) * getDifficultyLevel();
                return damage;
            default:
                break;
        }
        return damage;
    }

    @Override
    public void checkAlive() {
        if (this.health == 0){
            this.alive = false;
            System.out.println("The " + type() + " has been defeated!");
            System.out.println("You gain " + this.expReward + " experience.");
        }
    }

    @Override
    public void setExpReward() {
        this.expReward = 10*getDifficultyLevel();
    }
    
}
