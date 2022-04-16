package sql_insert;

/**
 * Created by arhudson on 10/7/2017.
 */

import java.sql.*;
import characters.*;

public class mysql_insert {
    public static void main(String[] args){
        CharCreate char1 = new CharCreate('w');

        try {
            //sql connection
            String url = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309gpb2";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,"dbu309gpb2","#bEdxrXT");

            //sql insert statement
            String query = "insert into character_object (health, strength, intelligence, agility, level, experience, class)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, char1.getHealth());
            ps.setInt(2, char1.getStrength());
            ps.setInt(3, char1.getIntellect());
            ps.setInt(4, char1.getAgility());
            ps.setInt(5, char1.getCharLevel());
            ps.setInt(6, char1.getCharXP());
            ps.setString(7, char1.getType());

            //execute ps
            ps.execute();

            conn.close();
        }
        catch(Exception e){
            System.out.println("You got an error");
            e.printStackTrace();
        }
    }
}
