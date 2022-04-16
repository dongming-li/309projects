package Mapping;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * A class to read in and store the map that is used to play LFW.
 * Created by sauerbreiale on 9/20/2017.
 */
public class BoardMap {
    //Holds the hashmap of the nodes that are part of the board
    private HashMap<String, Node> mainMapNodes;
    public final static String serverpath = "/var/lib/tomcat/webapps/LFW/WEB-INF/classes/Mapping/";

    /**
     * Constructor for a board to be read in to the application for use in the game
     *
     * @param fileName - the xml filename of the nodes to be read into the game as the game board
     */
    public BoardMap(String fileName){
        try{
        //Call the actual board loader
    	loadBoard(fileName);
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
        catch(SAXException e)
        {
        	e.printStackTrace();
        }
        catch(ParserConfigurationException e)
        {
        	e.printStackTrace();
        }
    }

    /**
     * Actually reads the board in from the xml file.
     *
     * @param fileName - the xml filename of the nodes to be read into the game as the game board
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private void loadBoard(String fileName) throws IOException, SAXException, ParserConfigurationException {
        //initializes the map nodes hashmap
        mainMapNodes = new HashMap<>();

        //builds the document to be used
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));
        doc.normalize();

        //gets the list of nodes based on the node tagname
        NodeList nodes = doc.getElementsByTagName("node");

        //stores the number of nodes to read in
        int nodesLength = nodes.getLength();

        //for every node in the document
        for (int i = 0; i < nodesLength; i++) {
            Element n = (Element) nodes.item(i);
            //if the node has attributes, read them into the application for that node
            if (n.hasAttributes()) {
                mainMapNodes.put(n.getAttribute("id"), new Node(n.getAttribute("id"),
                        n.getAttribute("color").charAt(0), Integer.parseInt(n.getAttribute("xCo")),
                        Integer.parseInt(n.getAttribute("yCo"))));
            }
        }

        //calls the helper method to do neighborgroups and alleyways
        loadBoardHelper("neighborGroup", doc);
        loadBoardHelper("alleyway", doc);
    }

    /**
     * Helper method for reading in the game board that reads in the neighbor groups and alleyways as separate things.
     *
     * @param tagName - parameter to tell the method whether it is an alleyway or a neighborgroup being read in
     * @param doc     - the document object that holds the xml file
     */
    private void loadBoardHelper(String tagName, Document doc) {
        //gets the groupings used based on the tagname given
        NodeList groupings = doc.getElementsByTagName(tagName);

        //holds the number of those groupings
        int groupingsLength = groupings.getLength();

        //for every grouping in the document
        for (int i = 0; i < groupingsLength; i++) {
            Element group = (Element) groupings.item(i);
            //if the nodes map contains this nodename
            if (mainMapNodes.containsKey(group.getAttribute("id"))) {
                //get the list of the neighbors based on the nd tag
                NodeList neighbors = group.getElementsByTagName("nd");
                int gNeighborsLength = neighbors.getLength();
                //get a neighbors hashmap to set the neighbors as
                HashMap<String, Node> ngSetNeighbors = new HashMap<>();
                //for every neighbor in the set
                for (int j = 0; j < gNeighborsLength; j++) {
                    Element neighbor = (Element) neighbors.item(j);
                    //get the nodename of the neighbor from the ref tagname
                    String neighborID = neighbor.getAttribute("ref");
                    //if the nodename is part of the map
                    if (mainMapNodes.containsKey(neighborID)) {
                        //add the node to be in the neighbors map to be set to the neighbors attribute
                        ngSetNeighbors.put(neighborID, mainMapNodes.get(neighborID));
                    }
                }
                //for the node that the neighborgroup/alleyway is for
                Node ngID = mainMapNodes.get(group.getAttribute("id"));
                //set it to neighbors if the tagname was neighborgroup
                if ("neighborGroup".equalsIgnoreCase(tagName))
                    ngID.setNeighbors(ngSetNeighbors);
                    //set it to alleyways if the tagname was alleywayaccess
                else if ("alleyway".equalsIgnoreCase(tagName))
                    ngID.setAlleywayAccess(ngSetNeighbors);
            }
        }
    }

    /**
     * Method to get a node from the map based on the name of it
     *
     * @param nodeName - the name of the node to find on the map
     * @return - a Node object of the node specified
     */
    public Node getNode(String nodeName) {
        return mainMapNodes.get(nodeName);
    }


}
