package enemy;

import android.graphics.Canvas;

/**
 *
 * @author Aaron
 */
public interface GenericEnemy {

    void draw(Canvas canvas);

    void update();

    String type(); //returns the type of enemy

    int getHealth(); //returns health

    void takeDamage(int damage); //changes the enemy health based on damage taken

    int getDifficultyLevel(); //returns the difficulty level

    void setDifficultyLevel(); //sets the difficulty based on the number of heroes

    int attack(); //the enemy makes an attack

    void checkAlive(); //checks if enemy is alive or not

    void setExpReward();

    
}
