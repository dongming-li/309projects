package Mapping;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * Class used for testing various aspects of the application
 * Created by sauerbreiale on 9/20/2017.
 */
@Deprecated
public class Testing {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        BoardMap map = new BoardMap("src\\Mapping\\board.xml");

        Node n1 = map.getNode("61");
        HashMap<String, Node> whiteneighbors = n1.getWhiteNeighbors(true, false);
        Set<String> s = whiteneighbors.keySet();
        for(String neighbor: s)
            System.out.println(neighbor);

        /*
        Node a = new Node("a", 'w', 2, 2);
        Node b = new Node("b", 'b', 2, 2);
        Node c = new Node("c", 'b', 2, 2);
        Node d = new Node("d", 'b', 2, 2);
        Node e = new Node("e", 'b', 2, 2);
        Node f = new Node("f", 'w', 2, 2);
        Node g = new Node("g", 'w', 2, 2);
        Node h = new Node("h", 'b', 2, 2);
        Node i = new Node("i", 'w', 2, 2);
        HashMap<String, Node> aneighbors = new HashMap<>();
        aneighbors.put("b",b);
        aneighbors.put("c",c);
        aneighbors.put("d",d);
        a.setNeighbors(aneighbors);

        HashMap<String, Node> bneighbors = new HashMap<>();
        bneighbors.put("e",e);
        b.setNeighbors(bneighbors);
        HashMap<String, Node> eneighbors = new HashMap<>();
        eneighbors.put("f", f);
        e.setNeighbors(eneighbors);
        HashMap<String, Node> cneighbors = new HashMap<>();
        cneighbors.put("g",g);
        c.setNeighbors(cneighbors);
        HashMap<String, Node> gneighbors = new HashMap<>();
        gneighbors.put("h",h);
        g.setNeighbors(gneighbors);
        HashMap<String, Node> hneighbors = new HashMap<>();
        hneighbors.put("i", i);
        h.setNeighbors(hneighbors);
        HashMap<String, Node> whiteneighbors = a.getWhiteNeighbors();
        Set<String> s = whiteneighbors.keySet();
        for(String neighbor: s)
        System.out.println(neighbor);

        e.setOccupied(true);
        System.out.println("e is occupied now");
        HashMap<String, Node> whiteneighbors2 = a.getWhiteNeighbors();
        Set<String> s2 = whiteneighbors2.keySet();
        for(String neighbor: s2)
            System.out.println(neighbor);
        */
    }
}
