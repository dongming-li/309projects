package Combat;

/**
 *
 * @author Aaron
 */
//Implement a priority queue or stack to handle multiple actors
import Enemy.Boss;
import proj.CharCreate;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CombatSystem {
    public static void main(String[] args){
        fight();
    }
   
    public static int damageCalcST(){
        return ThreadLocalRandom.current().nextInt(5, 8 + 1);
    }
    
    public static int damageCalcAoE(){
        return ThreadLocalRandom.current().nextInt(2, 5 + 1);
    }
        
    public static void fight(){      
        Boss boss = new Boss();
        
        Scanner sc = new Scanner(System.in);
        
        CharCreate warrior = new CharCreate('w');
        
        boolean engaged = true;
        
        while (engaged) {
            System.out.println("Enter Attack Type: (1 for single target) (2 for AoE)");
            int attackType = sc.nextInt();
            switch (attackType) {
                case 1:
                    System.out.println("You perform a single target attack!");
                    //boss.takeDamage(damageCalcST());
                    //warrior.attack();
                    boss.takeDamage(warrior.attack());
                    break;
                case 2:
                    System.out.println("You perform an AoE attack!");
                    //boss.takeDamage(damageCalcAoE());
                    //warrior.attack();
                    boss.takeDamage(warrior.attack());
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
                    break;
            }
            
            if (warrior.health <= 0 || !boss.alive){
                //engaged = false;
                break;
            }
            
            System.out.println("Remaining enemy health: " + boss.health + "\n");
            //boss.attack();
            warrior.addHealth(-boss.attack());
            System.out.println("You have " + warrior.health + " health left.");
        }
    }
}
