package rooms;

/**
 * Created by aaronhudson on 10/3/17.
 *
 * This is to test the creation of rooms with lists of characters/enemies/bosses
 */

import rooms.*;
import enemy.*;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import characters.*;

public class roomTest {
    public static void main(String[] args) {

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        //Enemy bandit = new Enemy("Bandit", 10, 1);
        //Enemy slime = new Enemy("Slime", 5, 1);
       //enemies.add(bandit);
        //enemies.add(slime);

        ArrayList<Boss> bosses = new ArrayList<Boss>();

        Boss boss = new Boss();
        bosses.add(boss);

        ArrayList<CharCreate> characters = new ArrayList<CharCreate>();

        CharCreate char1 = new CharCreate('w');
        CharCreate char2 = new CharCreate('m');
        CharCreate char3 = new CharCreate('r');
        CharCreate char4 = new CharCreate('w');
        characters.add(char1);
        characters.add(char2);
        characters.add(char3);
        characters.add(char4);

        //Room constructor sequence : characters, enemies, bosses ALWAYS
 /*       RoomCreate room = new RoomCreate(characters, enemies, bosses);

        System.out.println(room.getCharacters().toString());
        System.out.println(room.getEnemies().toString());
        System.out.println(room.getBoss().toString());*/

    }
}
