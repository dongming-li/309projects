package rooms;

import enemy.*;
import characters.*;
import java.util.ArrayList;

public class RoomCreate {
    String roomName;
    String type;
    ArrayList<GenericEnemy> enemies;
    String treasure;

    /**
     * Default constructor to create a blank room.
     */
    public RoomCreate()
    {
        roomName = null;
        this.type = null;
        this.enemies = new ArrayList<GenericEnemy>();
        this.treasure = null;
    }


    /**
     * Creates a new room with the given parameters
     * @param name Name of the room
     * @param type type of the room
     * @param enemies arraylist of enemies
     */
    public RoomCreate(String name, String type, ArrayList<GenericEnemy> enemies) {
        roomName = name;
        this.type = type;
        this.enemies = enemies;
        this.treasure = treasure;
    }

    public String getRoomName() { return this.roomName; }
    public String getType() { return this.type; }
    public ArrayList<GenericEnemy> getEnemies() { return this.enemies; }

    /**
     * Serializes the enemies
     * @return the serialized string
     */
    public String enemiesToString(){
        String enemy_string = "";

        for (int i = 0; i < this.enemies.size(); i++){
            if(this.enemies.get(i) != null){
                enemy_string += enemies.get(i).type();
                enemy_string += ":";
            }
        }
        return enemy_string;
    }

    /**
     * Serializes the room into a string to be pushed to the database.
     * @return serialization string.
     */
    public String serializeRoom(){
        String serialization = roomName + ":" + this.type + ":" + enemiesToString();
        return serialization;
    }
}
