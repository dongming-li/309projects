package gameMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * The Class GraphicalStuff.
 */
public class GraphicalStuff extends JPanel    {

	/** The Constant IMAGE_TYPE. */
	private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;

	/** The img. */
	private BufferedImage img;
	
	/** The image dir. */
	private final String imageDir = System.getProperty("user.dir") + "\\Images";
	
	/** The corn. */
	private File corn = new File(imageDir + "\\corn.jpg");
	
	/** The sprite image. */
	private File spriteImage = new File(imageDir + "\\AvatarGrassUP2.jpg");
	
	/** The building. */
	private File building = new File(imageDir + "\\building.jpg");
	
	/** The grass. */
	private File grass = new File(imageDir + "\\grass.png");
	
	/** The sidewalk. */
	private File sidewalk = new File(imageDir + "\\sidewalk.png");
	
	/** The road. */
	private File road = new File(imageDir + "\\road.png");

	
	/** The Bully 1. */
	private File Bully1 = new File(imageDir + "\\Bully1.png");
	
	/** The Student 1. */
	private File Student1 = new File(imageDir + "\\Student1.png");


	/** The Blonde avatar S right. */
	private File BlondeAvatarSRight = new File(imageDir +"\\BlondeAvatarSideWalkRight.png");
	
	/** The Blonde avatar S right 2. */
	private File BlondeAvatarSRight2 = new File(imageDir +"\\BlondeAvatarSideWalkRight2.png");
	
	/** The Blonde avatar S left. */
	private File BlondeAvatarSLeft = new File(imageDir +"\\BlondeAvatarSideWalkLeft.png");
	
	/** The Blonde avatar S left 2. */
	private File BlondeAvatarSLeft2 = new File(imageDir +"\\BlondeAvatarSideWalkLeft2.png");
	
	/** The Blonde avatar S up. */
	private File BlondeAvatarSUp = new File(imageDir +"\\BlondeAvatarSideWalkUp.png");
	
	/** The Blonde avatar S up 2. */
	private File BlondeAvatarSUp2 = new File(imageDir +"\\BlondeAvatarSideWalkUp2.png");
	
	/** The Blonde avatar S down. */
	private File BlondeAvatarSDown = new File(imageDir +"\\BlondeAvatarSideWalkDown.png");
	
	/** The Blonde avatar S down 2. */
	private File BlondeAvatarSDown2 = new File(imageDir +"\\BlondeAvatarSideWalkDown2.png");
	
	/** The Blonde avatar G right. */
	private File BlondeAvatarGRight = new File(imageDir +"\\BlondeAvatarGrassRight.png");
	
	/** The Blonde avatar G right 2. */
	private File BlondeAvatarGRight2 = new File(imageDir +"\\BlondeAvatarGrassRight2.png");
	
	/** The Blonde avatar G left. */
	private File BlondeAvatarGLeft = new File(imageDir +"\\BlondeAvatarGrassLeft.png");
	
	/** The Blonde avatar G left 2. */
	private File BlondeAvatarGLeft2 = new File(imageDir +"\\BlondeAvatarGrassLeft2.png");
	
	/** The Blonde avatar G up. */
	private File BlondeAvatarGUp = new File(imageDir +"\\BlondeAvatarGrassUp.png");
	
	/** The Blonde avatar G up 2. */
	private File BlondeAvatarGUp2 = new File(imageDir +"\\BlondeAvatarGrassUp2.png");
	
	/** The Blonde avatar G down. */
	private File BlondeAvatarGDown = new File(imageDir +"\\BlondeAvatarGrassDown.png");
	
	/** The Blonde avatar G down 2. */
	private File BlondeAvatarGDown2 = new File(imageDir +"\\BlondeAvatarGrassDown2.png");
	
	/** The Blonde avatar road down. */
	private File BlondeAvatarRoadDown = new File(imageDir + "\\BlondeAvatarRoadDown.png");
	
	/** The Blonde avatar road down 2. */
	private File BlondeAvatarRoadDown2 = new File(imageDir + "\\BlondeAvatarRoadDown2.png");
	
	/** The Blonde avatar road up. */
	private File BlondeAvatarRoadUp = new File(imageDir + "\\BlondeAvatarRoadUp.png");
	
	/** The Blonde avatar road up 2. */
	private File BlondeAvatarRoadUp2 = new File(imageDir + "\\BlondeAvatarRoadUp2.png");
	
	/** The Blonde avatar road left. */
	private File BlondeAvatarRoadLeft = new File(imageDir + "\\BlondeAvatarRoadLeft.png");
	
	/** The Blonde avatar road left 2. */
	private File BlondeAvatarRoadLeft2 = new File(imageDir + "\\BlondeAvatarRoadLeft2.png");
	
	/** The Blonde avatar road right. */
	private File BlondeAvatarRoadRight = new File(imageDir + "\\BlondeAvatarRoadRight.png");
	
	/** The Blonde avatar road right 2. */
	private File BlondeAvatarRoadRight2 = new File(imageDir + "\\BlondeAvatarRoadRight2.png");
	
	/** The Avatar 6 grass down. */
	private File Avatar6GrassDown = new File(imageDir + "\\Avatar6GrassDown.png");
	
	/** The Avatar 6 grass down 2. */
	private File Avatar6GrassDown2 = new File(imageDir + "\\Avatar6GrassDown2.png");
	
	/** The Avatar 6 grass right. */
	private File Avatar6GrassRight = new File(imageDir + "\\Avatar6GrassRight.png");
	
	/** The Avatar 6 grass right 2. */
	private File Avatar6GrassRight2 = new File(imageDir + "\\Avatar6GrassRight2.png");
	
	/** The Avatar 6 grass left. */
	private File Avatar6GrassLeft = new File(imageDir + "\\Avatar6GrassLeft.png");
	
	/** The Avatar 6 grass left 2. */
	private File Avatar6GrassLeft2 = new File(imageDir + "\\Avatar6GrassLeft2.png");
	
	/** The Avatar 6 grass up. */
	private File Avatar6GrassUp = new File(imageDir + "\\Avatar6GrassUp.png");
	
	/** The Avatar 6 grass up 2. */
	private File Avatar6GrassUp2 = new File(imageDir + "\\Avatar6GrassUp2.png");
	
	/** The Avatar 6 side walk down. */
	private File Avatar6SideWalkDown = new File(imageDir + "\\Avatar6SidewalkDown.png");
	
	/** The Avatar 6 side walk down 2. */
	private File Avatar6SideWalkDown2 = new File(imageDir + "\\Avatar6SidewalkDown2.png");
	
	/** The Avatar 6 side walk left. */
	private File Avatar6SideWalkLeft = new File(imageDir + "\\Avatar6SidewalkLeft.png");
	
	/** The Avatar 6 side walk left 2. */
	private File Avatar6SideWalkLeft2 = new File(imageDir + "\\Avatar6SidewalkLeft2.png");
	
	/** The Avatar 6 side walk right. */
	private File Avatar6SideWalkRight = new File(imageDir + "\\Avatar6SidewalkRight.png");
	
	/** The Avatar 6 side walk right 2. */
	private File Avatar6SideWalkRight2 = new File(imageDir + "\\Avatar6SidewalkRight2.png");
	
	/** The Avatar 6 side walk up. */
	private File Avatar6SideWalkUp = new File(imageDir + "\\Avatar6SidewalkUp.png");
	
	/** The Avatar 6 side walk up 2. */
	private File Avatar6SideWalkUp2 = new File(imageDir + "\\Avatar6SidewalkUp2.png");
	
	/** The Avatar 6 road down. */
	private File Avatar6RoadDown = new File(imageDir + "\\Avatar6RoadDown.png");
	
	/** The Avatar 6 road down 2. */
	private File Avatar6RoadDown2 = new File(imageDir + "\\Avatar6RoadDown2.png");
	
	/** The Avatar 6 road left. */
	private File Avatar6RoadLeft = new File(imageDir + "\\Avatar6RoadLeft.png");
	
	/** The Avatar 6 road left 2. */
	private File Avatar6RoadLeft2 = new File(imageDir + "\\Avatar6RoadLeft2.png");
	
	/** The Avatar 6 road right. */
	private File Avatar6RoadRight = new File(imageDir + "\\Avatar6RoadRight.png");
	
	/** The Avatar 6 road right 2. */
	private File Avatar6RoadRight2 = new File(imageDir + "\\Avatar6RoadRight2.png");
	
	/** The Avatar 6 road up. */
	private File Avatar6RoadUp = new File(imageDir + "\\Avatar6RoadUp.png");
	
	/** The Avatar 6 road up 2. */
	private File Avatar6RoadUp2 = new File(imageDir + "\\Avatar6RoadUp2.png");
	
	/** The guard grass right. */
	private File guardGrassRight = new File(imageDir + "\\guardGrassRight.png");
	
	/** The guard grass left. */
	private File guardGrassLeft = new File(imageDir + "\\guardGrassleft.png");
	
	/** The guard hurt grass. */
	private File guardHurtGrass  = new File(imageDir + "\\guardGrassHurt.png");
	
	/** The bully grass. */
	private File bullyGrass = new File(imageDir + "\\bullyGrass.png");
	
	/** The bully hurt grass. */
	private File bullyHurtGrass =new File(imageDir + "\\bullyHurtGrass.png");
	
	/** The bully side walk. */
	private File bullySideWalk =new File(imageDir + "\\bullySideWalk.png");
	
	/** The Bus stop. */
	private File BusStop = new File(imageDir +"\\busstop.png");
	
	/** The student hurt grass. */
	private File studentHurtGrass = new File(imageDir +"\\studentKO.png");

	/** The background. */
	public JFrame background;
	
	/** The pc. */
	private PC pc;
	
	/** The map. */
	private map map;
	
	/** The quest. */
	private Quests quest;
	
	/** The door. */
	private interactableTile door;
	
	/** The number. */
	private int number;
	
	/** The action. */
	public boolean action = false;
	
	/** The back. */
	static GraphicalStuff back;
	
	/** The last tile. */
	private char lastTile;
	
	/** The tiles. */
	private BufferedImage[] tiles;
	
	/** The pc X. */
	private int pcX;
	
	/** The pc Y. */
	private int pcY;
	
    /**
     * Instantiates a new graphical stuff.
     *
     * @param map the map
     * @param pc the pc
     * @param pcX the pc X
     * @param pcY the pc Y
     * @param timeClass the time class
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public GraphicalStuff(map map,PC pc, int pcX, int pcY,Time timeClass) throws IOException {
        super();

       

        tiles = new BufferedImage[80];

        tiles[0] = ImageIO.read(corn);//createOneTile( new Color( 255, 255, 255 ) );
        tiles[1] = ImageIO.read(building);//createOneTile( new Color( 255,   0, 255 ) );
        tiles[2] = ImageIO.read(grass);//createOneTile( new Color(   0,   0, 255 ) );
        tiles[3] = ImageIO.read(sidewalk);//createOneTile( new Color(   0, 255, 255 ) ); 
        tiles[4] = ImageIO.read(spriteImage);
        tiles[5] = ImageIO.read(BlondeAvatarGRight);
        tiles[6] = ImageIO.read(BlondeAvatarGRight2);
        tiles[7]=ImageIO.read(Bully1);
        tiles[8]=ImageIO.read(Student1);

        tiles[5] = ImageIO.read(BlondeAvatarSRight);
        tiles[6] = ImageIO.read(BlondeAvatarSRight2);
        tiles[7]= ImageIO.read(BlondeAvatarSLeft);
        tiles[8]= ImageIO.read(BlondeAvatarSLeft2);
        tiles[9]= ImageIO.read(BlondeAvatarSUp);
        tiles[10]= ImageIO.read(BlondeAvatarSUp2);
        tiles[11]= ImageIO.read(BlondeAvatarSDown);
        tiles[12]= ImageIO.read(BlondeAvatarSDown2);

        
        tiles[13] = ImageIO.read(BlondeAvatarGRight);
        tiles[14] = ImageIO.read(BlondeAvatarGRight2);
        tiles[15]= ImageIO.read(BlondeAvatarGLeft);
        tiles[16]= ImageIO.read(BlondeAvatarGLeft2);
        tiles[17]= ImageIO.read(BlondeAvatarGUp);
        tiles[18]= ImageIO.read(BlondeAvatarGUp2);
        tiles[19]= ImageIO.read(BlondeAvatarGDown);
        tiles[20]= ImageIO.read(BlondeAvatarGDown2);
 
      
        tiles[21]= ImageIO.read(road);
        tiles[22] = ImageIO.read(BusStop);
        
        tiles[23] = ImageIO.read(BlondeAvatarRoadDown);
        tiles[24] = ImageIO.read(BlondeAvatarRoadDown2);
        tiles[25] = ImageIO.read(BlondeAvatarRoadUp);
        tiles[26] = ImageIO.read(BlondeAvatarRoadUp2);
        tiles[27] = ImageIO.read(BlondeAvatarRoadLeft);
        tiles[28] = ImageIO.read(BlondeAvatarRoadLeft2);
        tiles[29] = ImageIO.read(BlondeAvatarRoadRight);
        tiles[30] = ImageIO.read(BlondeAvatarRoadRight2);
        
        tiles[31]= ImageIO.read(Avatar6GrassDown);
        tiles[32]=ImageIO.read(Avatar6GrassDown2);
        tiles[33]= ImageIO.read(Avatar6GrassRight);
        tiles[34]=ImageIO.read(Avatar6GrassRight2);
        tiles[35]= ImageIO.read(Avatar6GrassLeft);
        tiles[36]=ImageIO.read(Avatar6GrassLeft2);
        tiles[37]= ImageIO.read(Avatar6GrassUp);
        tiles[38]=ImageIO.read(Avatar6GrassUp2);
        
        tiles[39]= ImageIO.read(Avatar6SideWalkDown);
        tiles[40]=ImageIO.read(Avatar6SideWalkDown2);
        tiles[41]= ImageIO.read(Avatar6SideWalkLeft);
        tiles[42]=ImageIO.read(Avatar6SideWalkLeft2);
        tiles[43]= ImageIO.read(Avatar6SideWalkRight);
        tiles[44]=ImageIO.read(Avatar6SideWalkRight2);
        tiles[45]= ImageIO.read(Avatar6SideWalkUp);
        tiles[46]=ImageIO.read(Avatar6SideWalkUp2);
        
        
        tiles[47]= ImageIO.read(Avatar6RoadDown);
        tiles[48]=ImageIO.read(Avatar6RoadDown2);
        tiles[49]= ImageIO.read(Avatar6RoadLeft);
        tiles[50]=ImageIO.read(Avatar6RoadLeft2);
        tiles[51]= ImageIO.read(Avatar6RoadRight);
        tiles[52]=ImageIO.read(Avatar6RoadRight2);
        tiles[53]= ImageIO.read(Avatar6RoadUp);
        tiles[54]=ImageIO.read(Avatar6RoadUp2);
        
        tiles[55] = ImageIO.read(guardGrassRight);
        tiles[56] = ImageIO.read(bullyGrass);
        tiles[57] = ImageIO.read(bullyHurtGrass);
        
        tiles[58] = ImageIO.read(bullyGrass);
        tiles[59] =ImageIO.read(bullySideWalk);
        tiles[60] = ImageIO.read(guardGrassRight);
        tiles[61] = ImageIO.read(guardGrassLeft);
        tiles[62] = ImageIO.read(guardHurtGrass);
        tiles[63] = ImageIO.read(studentHurtGrass);
        background = new JFrame();
        //background.add(this);
        	this.setDoubleBuffered(true);
        	
    		//myBuffer = this.getBufferStrategy();
        door = new interactableTile(pc,timeClass,this);

       // door = new interactableTile();


       // door = new interactableTile();

        this.pcX=pcX;
        this.pcY=pcY;
        this.map = map;
        this.pc = pc;
       //setBackground(Color.LIGHT_GRAY);
	   // setSize(500,500 );
       
        //addKeyListener(listen);
        render(map, pcX, pcY);
        
       
			// back.fun(map, pc.getRow(), pc.getCol());

		
		
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
	public void paint(java.awt.Graphics g) {
            	super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
    
    /**
     * Update image.
     *
     * @param map the map
     * @param pcX the pc X
     * @param pcY the pc Y
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void updateImage(map map , int pcX, int pcY) throws IOException {
    	
    	if(map.isGuardLeft()) {
    		
    		tiles[60] =tiles[55];
    	}
    	else {
    		tiles[60] = tiles[61];
    	}
    	  if(map.isBullyOnSideWalk()) {
    		 map.setBullyOnSideWalk(false);
    		 tiles[56]=tiles[59];
    	 }
    	 else {
    		 tiles[56]= tiles[58];
    	 }
    	  
        if(map.getdirectionName()=="Up") {
			 //System.out.println("2");
		        
				if(map.getOnDoor() || map.isOnGrass() || map.isOnBusStop()|| map.isOnEdgeDoor() || map.isOnRoad()){
						if(map.getOnDoor()) {
					
					spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
					tiles[4] = ImageIO.read(spriteImage);
					map.setOnDoor(false);
					//checkDoor(pcX ,pcY);
					
					checkDoor(pcX,pcY,map);
					
					//System.out.println(pcX + "" +pcY);

					
					//System.out.println(pcX + "" +pcY);

					
				}
						else if(map.isOnRoad()) {
							
							map.setOnRoad(false);
							
							if(map.getnumChange()==1) {
								
								if(pc.getGender()==6) {
									tiles[4] = tiles[53];
								}
								else
									
								tiles[4] =tiles[25];
								//System.out.println("1");
								
								
								
							}
							else if(map.getnumChange()==0) {
								//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUp2.png");
								
								//
								//System.out.println("2");
								if(pc.getGender()==6) {
									tiles[4] = tiles[54];
								}
								else{
									tiles[4] = tiles[26];
								}
								
							}
						
						}
						
						else if(map.isOnEdgeDoor()) {
							
							spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
							tiles[4] = tiles[0];
							map.setOnEdgeDoor(false);
							checkDoor(pcX,pcY,map);
						}
						
						else if(map.isOnBusStop()) {
							
							spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
							tiles[4] = tiles[22];
							map.setOnBusStop(false);
							checkDoor(pcX,pcY,map);
						}
				
						else if(map.isOnGrass()) {
						map.setOnGrass(false);
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUP.png");
						if(map.getnumChange()==1) {
							
							//
							//System.out.println("1");
							if(pc.getGender()==6) {
								tiles[4] = tiles[37];
							}
							else
								tiles[4] =tiles[17];
							
						
							
						}
						else if(map.getnumChange()==0) {
							//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUp2.png");
							
							//
							//System.out.println("2");
							if(pc.getGender()==6) {
								tiles[4] = tiles[38];
							}
							
							else
								tiles[4] = tiles[18];
						}
						
					}
				}
				else {
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUP.png");
					//tiles[4] = ImageIO.read(spriteImage);
				
				
				if(map.getnumChange()==1) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkUp.png");
					//tiles[4] = ImageIO.read(spriteImage);
					//System.out.println("1");
					//
					if(pc.getGender()==6) {
						tiles[4] = tiles[45];
					}
					else
						tiles[4] = tiles[9];
						
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkUp2.png");
					
					//tiles[4] = ImageIO.read(spriteImage);
					//System.out.println("2");
					//
					if(pc.getGender()==6) {
						tiles[4] = tiles[46];
					}
					else
						tiles[4] = tiles[10];
				}
				}
		}
        /*
       if(map.getdirectionName()=="Up" & map.getUpHeld()) {
       	
      
       
		if(map.getOnDoor()){
			spriteImage = new File(imageDir + "\\AvatarSideWalkUP.jpg");
			tiles[4] = ImageIO.read(spriteImage);
			map.setOnDoor(false);
		}
		else {
			spriteImage = new File(imageDir + "\\AvatarGrassUP.jpg");
			tiles[4] = ImageIO.read(spriteImage);
		
		if(map.getOnLeft()) {
			spriteImage = new File(imageDir + "\\AvatarGrassUP.jpg");
			tiles[4] = ImageIO.read(spriteImage);
			System.out.println("Left foot");
			
		}
		else if (!map.getOnLeft()) {
			spriteImage = new File(imageDir + "\\AvatarGrassUP2.jpg");
			tiles[4] = ImageIO.read(spriteImage);
			System.out.println("Right foot");
		}
		
		}
       }
       */
		
		 if(map.getdirectionName()=="Left") {
			 //System.out.println("2");
			 
				if(map.getOnDoor() || map.isOnGrass() || map.isOnBusStop()|| map.isOnEdgeDoor()|| map.isOnRoad()){
						if(map.getOnDoor()) {
					
					spriteImage = new File(imageDir + "\\BlondeAvatarDoorLeft.jpg");
					tiles[4] = ImageIO.read(spriteImage);
					map.setOnDoor(false);
					checkDoor(pcX,pcY , map);
					//render(map, pcX, pcY);
					//System.out.println(pcX + "" +pcY);
					
				}
						else if(map.isOnRoad()) {
							
							map.setOnRoad(false);
							
							if(map.getnumChange()==1) {
								
								//
								//System.out.println("1");
								if(pc.getGender()==6) {
									tiles[4] =tiles[49];
								}
								else
									tiles[4] =tiles[27];
								
							}
							else if(map.getnumChange()==0) {
								//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUp2.png");
								
								//tiles[4] = tiles[28];
								//System.out.println("2");
								if(pc.getGender()==6) {
									tiles[4] =tiles[50];
								}
								else
									tiles[4] = tiles[28];
							}
						
						}
				
						else if(map.isOnEdgeDoor()) {
							
							spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
							tiles[4] = tiles[0];
							map.setOnEdgeDoor(false);
							checkDoor(pcX,pcY,map);
						}
							else if(map.isOnBusStop()) {
							
							spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
							tiles[4] = tiles[22];
							map.setOnBusStop(false);
							checkDoor(pcX,pcY,map);
						}
							else if(map.isOnGrass()) {
						map.setOnGrass(false);
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassLeft.png");
						if(map.getnumChange()==1) {
							
							//tiles[4] = ImageIO.read(spriteImage);
							//System.out.println("1");
							//tiles[4] =tiles[15];
							if(pc.getGender()==6) {
								tiles[4] =tiles[35];
							}
							else
								tiles[4] =tiles[15];
							
						}
						else if(map.getnumChange()==0) {
							//spriteImage = new File(imageDir + "\\BlondeAvatarGrassLeft2.png");
							
							//tiles[4] = ImageIO.read(spriteImage);
							//System.out.println("2");
							//tiles[4] =tiles[16];
							if(pc.getGender()==6) {
								tiles[4] = tiles[36];
							}
							else
								tiles[4] =tiles[16];
						}
						
					}
				}
				
				
				else {
					//spriteImage = new File(imageDir + "\\AvatarGrassleft.jpg");
					//tiles[4] = ImageIO.read(spriteImage);
				
				
				if(map.getnumChange()==1) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkLeft.png");
					//tiles[4] = ImageIO.read(spriteImage);
					//tiles[4] =tiles[7];
					//System.out.println("1");
					if(pc.getGender()==6) {
							tiles[4]=tiles[41];
					}
				
					else
						tiles[4] =tiles[7];
					
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkLeft2.png");
					
					//tiles[4] = ImageIO.read(spriteImage);
					//tiles[4] =tiles[8];
					//System.out.println("2");
					if(pc.getGender()==6) {
						tiles[4]=tiles[42];
					}
					else
						tiles[4] =tiles[8];
				}
				}
		}
		 if(map.getdirectionName()=="Right") {
			 //System.out.println("2");
		        
			 if(map.getOnDoor() || map.isOnGrass() || map.isOnBusStop() || map.isOnEdgeDoor()||map.isOnRoad()){
					if(map.getOnDoor()) {
				
				spriteImage = new File(imageDir + "\\BlondeAvatarDoorRight.jpg");
				tiles[4] = ImageIO.read(spriteImage);
				map.setOnDoor(false);
				checkDoor(pcX,pcY, map);
				//System.out.println(pcX + "" +pcY);
				
			}
					else if(map.isOnRoad()) {
						
						map.setOnRoad(false);
						
						if(map.getnumChange()==1) {
							
							//tiles[4] =tiles[29];
							//System.out.println("1");
							if(pc.getGender()==6) {
								tiles[4] =tiles[51];
							}
							else
								tiles[4] =tiles[29];
							
						}
						else if(map.getnumChange()==0) {
							//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUp2.png");
							
							//tiles[4] = tiles[30];
							//System.out.println("2");
							if(pc.getGender()==6) {
								tiles[4] =tiles[52];
							}
							else
								tiles[4] = tiles[30];
							
						}
					
					}
					else if(map.isOnEdgeDoor()) {
						
						spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
						tiles[4] = tiles[0];
						map.setOnEdgeDoor(false);
						checkDoor(pcX,pcY,map);
					}
					else if(map.isOnBusStop()) {
						
						spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
						tiles[4] = tiles[22];
						map.setOnBusStop(false);
						checkDoor(pcX,pcY,map);
					}
					else if(map.isOnGrass()) {
					map.setOnGrass(false);
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight.png");
					if(map.getnumChange()==1) {
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("1");
						
						//tiles[4] = tiles[13];
						if(pc.getGender()==6) {
							tiles[4] = tiles[33];
						}
						else
							tiles[4] = tiles[13];
					}
					else if(map.getnumChange()==0) {
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight2.png");
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("2");
						//tiles[4] = tiles[14];
						if(pc.getGender()==6) {
							tiles[4] = tiles[34];
						}
						else
							tiles[4] = tiles[14];
						
					}
					
				}
			}
				else {
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight.png");
					//tiles[4] = ImageIO.read(spriteImage);
				
				
				if(map.getnumChange()==1) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight2.png");
					//tiles[4] = tiles[5];
					if(pc.getGender()==6) {
						tiles[4] = tiles[43];
					}
					else
						tiles[4] = tiles[5];
					
					
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight.png");
					
					//tiles[4] = tiles[6];
					if(pc.getGender()==6) {
						tiles[4] = tiles[44];
					}
					else
						tiles[4] = tiles[6];
				}
				}
		}
		 if(map.getdirectionName()=="Down") {
			 //System.out.println("2");
		        
			 if(map.getOnDoor() || map.isOnGrass() || map.isOnBusStop()|| map.isOnEdgeDoor() || map.isOnRoad()){
					if(map.getOnDoor()) {
				
				spriteImage = new File(imageDir + "\\BlondeAvatarDoorDown.jpg");
				tiles[4] =ImageIO.read(spriteImage);
				map.setOnDoor(false);
				checkDoor(pcX,pcY,map);
				//System.out.println(pcX + "" +pcY);
				
			}
						else if(map.isOnRoad()) {
						
						map.setOnRoad(false);
						
						if(map.getnumChange()==1) {
							
							//tiles[4] =tiles[23];
							if(pc.getGender()==6) {
								tiles[4] =tiles[47];
							}
							else
								tiles[4] =tiles[23];
							//System.out.println("1");
							
							
						}
						else if(map.getnumChange()==0) {
							//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUp2.png");
							
							//tiles[4] = tiles[24];
							if(pc.getGender()==6) {
								tiles[4] =tiles[48];
							}
							else
								tiles[4] = tiles[24];
							//System.out.println("2");
						}
					
					}
					else if(map.isOnEdgeDoor()) {
						
						spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
						tiles[4] = tiles[0];
						map.setOnEdgeDoor(false);
						checkDoor(pcX,pcY,map);
					}
					else if(map.isOnBusStop()) {
						
						spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
						tiles[4] = tiles[22];
						map.setOnBusStop(false);
						checkDoor(pcX,pcY,map);
					}
			
					else	if(map.isOnGrass()) {
					map.setOnGrass(false);
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassDown.png");
					if(map.getnumChange()==1) {
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("1");
						//tiles[4] = tiles[19];
						if(pc.getGender()==6) {
								tiles[4] = tiles[31];
						}
						else
							tiles[4] = tiles[19];
						
					}
					else if(map.getnumChange()==0) {
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassDown2.png");
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("2");
						//tiles[4] = tiles[20];
						if(pc.getGender()==6) {
							tiles[4] = tiles[32];
						}
						else
							tiles[4] = tiles[20];
					}
					
				}
			}
				else {
					//spriteImage = new File(imageDir + "\\AvatarGrassDown.jpg");
					//tiles[4] = ImageIO.read(spriteImage);
				
				
				if(map.getnumChange()==1) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkDown.png");
					//tiles[4] = ImageIO.read(spriteImage);
					//tiles[4] =tiles[11];
					if(pc.getGender()==6) {
						tiles[4] = tiles[39];
					}
					else
						tiles[4] =tiles[11];
					
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkDown2.png");
					
					//tiles[4] = ImageIO.read(spriteImage);
					//tiles[4]=tiles[12];
					if(pc.getGender()==6) {
						tiles[4] = tiles[40];
					}
					else
						tiles[4]=tiles[12];
					
				}
				}
		}
		 if(map.getdirectionName()=="UpOnce" & map.getUpHeld()) {
			 if(map.getOnDoor()){
					spriteImage = new File(imageDir + "\\AvatarSideWalkUP.jpg");
					tiles[4] = ImageIO.read(spriteImage);
					map.setOnDoor(false);
				}
				else {
					spriteImage = new File(imageDir + "\\AvatarGrassUp.jpg");
					tiles[4] = ImageIO.read(spriteImage);
				
				
				if(map.getAlternateFeet()==1) {
					spriteImage = new File(imageDir + "\\AvatarGrassUp.jpg");
					tiles[4] = ImageIO.read(spriteImage);
					
					
					
				}
				else if(map.getAlternateFeet()==0) {
					spriteImage = new File(imageDir + "\\AvatarGrassUp2.jpg");
					
					tiles[4] = ImageIO.read(spriteImage);
					
				}
		 }
		 }
		
    }
    
    /**
     * Render.
     *
     * @param map the map
     * @param pcX the pc X
     * @param pcY the pc Y
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void render(map map, int pcX, int pcY ) throws IOException
    {   
    	
    
    	  //background.add(inventory, BorderLayout.CENTER);
    	
        //  add(new JPanel()
        		  
     
         

        	



        
        
    	int height = 6;
        int width = 5;
         img = new BufferedImage(650, 650, IMAGE_TYPE); // here you should create a compatible BufferedImage
       // img = new BufferedImage(1900, 1000, IMAGE_TYPE );   // here you should create a compatible BufferedImage
       // setSize(img.getWidth(), img.getHeight());
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // setSize(img.getWidth(),img.getHeight());
		
		//background.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//background.setFocusableWindowState(true);
	//	background.setAlwaysOnTop(true);
         
        
		//System.out.println(map.getOnDoor());
        for (int i = pcX - width; i <= pcX + width; i++) {
            for (int j = pcY - height; j <= pcY + height; j++) {
            	if(i > -1 && i < map.getRow() && j > -1 && j < map.getCol())
            	{
            		char mapTile = map.getRowCol(i, j);
            		
            		final BufferedImage tile = tiles[retImgArrayIndex(mapTile)];
            		
            		
            		for (int x = 0; x < tile.getWidth(); x++) 
                	{
                        for (int y = 0; y < tile.getHeight(); y++) 
                        {
                        	//int transparent = new Color(img.getRGB( x,  y)).getAlpha();
                        	//int argb = tile.getRGB(x, y);
                        	//if( (argb>>24) == 0x00 )
                        	//img.setRGB( y + ((j - (pcY - 4)) * 50),  x + ((i - (pcX - 4))  * 50),  0 );   
                        		
                        		img.setRGB( y + ((j - (pcY - height)) * 50),  x + ((i - (pcX - width))  * 50),  tile.getRGB(x,y) ); 
                        	
                       
                    }	
            		}
            	}
            }
            
        }
        repaint();
      
        //setVisible( true );
        //setVisible(true);
    }
   
	
	/**
	 * Ret img array index.
	 *
	 * @param c the c
	 * @return the int
	 */
	private int retImgArrayIndex(char c) {
		switch (c) {
		case '*':
			return 3;
		case 'b':
			return 1;
		case '`':
			return 2;
		case 's':
			return 3;
		case 'n':
			return 3;
		case 'r':
			return 21;
		case '@':
			return 4;
		case '!':
			return 56;
		case '|':
			return 8; 
		case'{':
			return 22;
		case'+':
			return 60;
		case'H':
			return 57;
		case'L':
			return 62;
		case'M':
			return 63;
		default:
			return 0;
		}
	}
	
	/**
	 * Check door.
	 *
	 * @param x the x
	 * @param y the y
	 * @param map the map
	 */
	private void checkDoor(int x ,int y , map map){
		
		if(x==36 & y ==111){
			door.SnedecorHall(1500, 2,map);
			
		}
		else if(x==21 & y ==122){
			door.SnedecorHall(1500, 2,map);
			
		}
		else if(x==35 & y == 74) {
			door.CooverHall(900, 1, map);
		}
		else if(x ==35 & y ==27) {
			door.CooverHall(900, 1, map);
			
		}
		else if(x==49 & y ==97) {
			door.AtanasoffHall(1500, 2,map);
			
		}
		else if(x==40 & y ==99) {
			door.AtanasoffHall(1500, 2,map);
			
		}
		else if(x==71 & y ==155) {
			door.ParksLibrary(1500, 4, 2,map);
		}
		else if(x==49 & y ==179) {
			door.ParksLibrary(1500, 4, 2,map);
		}
		else if(x==94 & y ==11) {
			door.DurhamHall(1, 1,map);
		}
		else if(x==65 & y ==119) {
			door.DurhamHall(1, 1,map);
		}
		else if(x== 89 & y == 79) {
			door.SweenyHall(1, 2,map);
		}
		else if(x== 74 & y == 34) {
			door.SweenyHall(1, 2,map);
		}
		else if(x==137 & y ==77) {
			door.HooverHall(1, 2,map);
		}
		else if(x==127 & y ==14) {
			door.HooverHall(1, 2,map);
		}
		else if(x==189 & y ==32) {
			door.BlackEngineering(1, 2,map);
		}
		else if(x==189 & y ==32) {
			door.BlackEngineering(1, 2,map);
		}
		else if(x== 167 & y == 59) {
			door.PearsonHall(1, 2, 3,map);
		}
		else if(x== 159 & y == 111) {
			door.PearsonHall(1, 2, 3,map);
		}
		else if(x== 139 & y ==100) {
			door.MarstonHall(1, 1,map);
		}
		else if(x== 124 & y ==110) {
			door.MarstonHall(1, 1,map);
		}
		else if(x==89 & y ==169) {
			door.CaribouCafe(900,map);
		}
		else if(x==92 & y ==164) {
			door.CaribouCafe(900,map);
		}
		else if(x==160 & y == 182) {
			door.BeardshearHall(1, 2,map);
		}
		else if(x==160 & y == 161) {
			door.BeardshearHall(1, 2,map);
		}
		else if(x==6 & y==101) {
				
			door.BusStop(1, 5, map,"North");
		}
		
		else if(x==107 & y==6) {
			
			door.BusStop(1, 5, map,"West");
		}
		else if(x==207 & y==101) {
			
			door.BusStop(1, 5, map,"South");
		}
		else if(x==107 & y==196) {
			
			door.BusStop(1, 5, map,"East");
		}
		else if(x==1 & y==14) {
			
			door.Armory(1, 1, map);
		}
		else if(x==1 & y==163) { //physics?
			
			
		}
		else if(x==12 & y==1) {
			
			door.DesignHall(1, 1, map);
		}
		else if(x==12 & y==201) {
			
			door.McKayHall(1, 1, map);
		}
		else if(x==111 & y==1) {
			
			door.HoweHall(1, 1, map);
		}
		else if(x==111 & y==201) {
			
			door.CurtissHall(1, 1, 1, map);
		}
	}

}
