package enemy;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author aaronhudson
 */
public class BossMainTest {
    public static void main(String[] args){
        Boss boss = new Boss();
        System.out.println("Initial boss health: " + boss.health); 
        
        while (boss.alive){
            System.out.println("The boss has " + boss.health + " health remaining");
            boss.attack();
            System.out.println("You attack!");
            boss.takeDamage(damageCalc());
            System.out.println("\n");
        }
    }
    
    public static int damageCalc(){
        return ThreadLocalRandom.current().nextInt(3, 7 + 1);
    }
}
