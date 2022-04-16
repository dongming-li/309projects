package Mapping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class is used for writing neighbor groups to the board to form the map.
 * Created by sauerbreiale on 3/9/2017.
 */
@Deprecated
public class printer {

    public static void main(String [] args)
    {
        try(FileWriter fw = new FileWriter("src\\Mapping\\board.xml", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            Scanner s = new Scanner(System.in);
            System.out.println("What is the node this is for?");
            String id = s.next();

            while(!"done".equalsIgnoreCase(id))
            {
                out.println("\t<neighborGroup id=\"" + id + "\">");
                id= s.next();
                while(!"end".equalsIgnoreCase(id)) {
                    out.println("\t\t<nd ref=\"" + id + "\"/>");
                    id = s.next();
                }
                out.println("\t</neighborGroup>");
                out.println();
                System.out.println("What is the node this is for?");
                id = s.next();
            }
            out.flush();
            out.close();
            bw.close();
            fw.close();



        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }


    }


}


