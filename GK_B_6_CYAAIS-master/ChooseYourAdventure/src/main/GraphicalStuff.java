package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GraphicalStuff extends JPanel    {

	private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;

	private BufferedImage img;
	private final String imageDir = System.getProperty("user.dir") + "\\Images";
	private File corn = new File(imageDir + "\\corn.jpg");
	private File spriteImage = new File(imageDir + "\\AvatarGrassUP2.jpg");
	private File building = new File(imageDir + "\\building.jpg");
	private File grass = new File(imageDir + "\\grass.png");
	private File sidewalk = new File(imageDir + "\\sidewalk.png");

	
	private File Bully1 = new File(imageDir + "\\Bully1.png");
	private File Student1 = new File(imageDir + "\\Student1.png");


	private File BlondeAvatarSRight = new File(imageDir +"\\BlondeAvatarSideWalkRight.png");
	private File BlondeAvatarSRight2 = new File(imageDir +"\\BlondeAvatarSideWalkRight2.png");
	private File BlondeAvatarSLeft = new File(imageDir +"\\BlondeAvatarSideWalkLeft.png");
	private File BlondeAvatarSLeft2 = new File(imageDir +"\\BlondeAvatarSideWalkLeft2.png");
	private File BlondeAvatarSUp = new File(imageDir +"\\BlondeAvatarSideWalkUp.png");
	private File BlondeAvatarSUp2 = new File(imageDir +"\\BlondeAvatarSideWalkUp2.png");
	private File BlondeAvatarSDown = new File(imageDir +"\\BlondeAvatarSideWalkDown.png");
	private File BlondeAvatarSDown2 = new File(imageDir +"\\BlondeAvatarSideWalkDown2.png");
	
	private File BlondeAvatarGRight = new File(imageDir +"\\BlondeAvatarGrassRight.png");
	private File BlondeAvatarGRight2 = new File(imageDir +"\\BlondeAvatarGrassRight2.png");
	private File BlondeAvatarGLeft = new File(imageDir +"\\BlondeAvatarGrassLeft.png");
	private File BlondeAvatarGLeft2 = new File(imageDir +"\\BlondeAvatarGrassLeft2.png");
	private File BlondeAvatarGUp = new File(imageDir +"\\BlondeAvatarGrassUp.png");
	private File BlondeAvatarGUp2 = new File(imageDir +"\\BlondeAvatarGrassUp2.png");
	private File BlondeAvatarGDown = new File(imageDir +"\\BlondeAvatarGrassDown.png");
	private File BlondeAvatarGDown2 = new File(imageDir +"\\BlondeAvatarGrassDown2.png");
	

	public JFrame background;
	private PC pc;
	private map map;
	private Quests quest;
	private interactableTile door;
	private int number;
	public boolean action = false;
	static GraphicalStuff back;
	private char lastTile;
	private BufferedImage[] tiles;
	private int pcX;
	private int pcY;
	
    public GraphicalStuff(map map,PC pc, int pcX, int pcY) throws IOException {
        super();

       

        tiles = new BufferedImage[21];

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
        background = new JFrame();
        //background.add(this);
        	this.setDoubleBuffered(true);
        	
    		//myBuffer = this.getBufferStrategy();
        door = new interactableTile(pc);

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
    
    @Override
	public void paint(java.awt.Graphics g) {
            	super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
    public void updateImage(map map , int pcX, int pcY) throws IOException {
    	 
        if(map.getdirectionName()=="Up") {
			 //System.out.println("2");
		        
				if(map.getOnDoor() || map.isOnGrass()){
						if(map.getOnDoor()) {
					
					spriteImage = new File(imageDir + "\\BlondeAvatarDoorUP.jpg");
					tiles[4] = ImageIO.read(spriteImage);
					map.setOnDoor(false);
					//checkDoor(pcX ,pcY);
					
					checkDoor(pcX,pcY,map);
					
					//System.out.println(pcX + "" +pcY);

					
					//System.out.println(pcX + "" +pcY);

					
				}
				
					if(map.isOnGrass()) {
						map.setOnGrass(false);
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUP.png");
						if(map.getnumChange()==1) {
							
							tiles[4] =tiles[17];
							//System.out.println("1");
							
							
						}
						else if(map.getnumChange()==0) {
							//spriteImage = new File(imageDir + "\\BlondeAvatarGrassUp2.png");
							
							tiles[4] = tiles[18];
							//System.out.println("2");
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
					tiles[4] = tiles[9];
					
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkUp2.png");
					
					//tiles[4] = ImageIO.read(spriteImage);
					//System.out.println("2");
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
			 
				if(map.getOnDoor() || map.isOnGrass()){
						if(map.getOnDoor()) {
					
					spriteImage = new File(imageDir + "\\BlondeAvatarDoorLeft.jpg");
					tiles[4] = ImageIO.read(spriteImage);
					map.setOnDoor(false);
					checkDoor(pcX,pcY , map);
					//render(map, pcX, pcY);
					//System.out.println(pcX + "" +pcY);
					
				}
				
					if(map.isOnGrass()) {
						map.setOnGrass(false);
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassLeft.png");
						if(map.getnumChange()==1) {
							
							//tiles[4] = ImageIO.read(spriteImage);
							//System.out.println("1");
							tiles[4] =tiles[15];
							
							
						}
						else if(map.getnumChange()==0) {
							//spriteImage = new File(imageDir + "\\BlondeAvatarGrassLeft2.png");
							
							//tiles[4] = ImageIO.read(spriteImage);
							//System.out.println("2");
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
					tiles[4] =tiles[7];
					//System.out.println("1");
					
					
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkLeft2.png");
					
					//tiles[4] = ImageIO.read(spriteImage);
					tiles[4] =tiles[8];
					//System.out.println("2");
				}
				}
		}
		 if(map.getdirectionName()=="Right") {
			 //System.out.println("2");
		        
			 if(map.getOnDoor() || map.isOnGrass()){
					if(map.getOnDoor()) {
				
				spriteImage = new File(imageDir + "\\BlondeAvatarDoorRight.jpg");
				tiles[4] = ImageIO.read(spriteImage);
				map.setOnDoor(false);
				checkDoor(pcX,pcY, map);
				//System.out.println(pcX + "" +pcY);
				
			}
			
				if(map.isOnGrass()) {
					map.setOnGrass(false);
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight.png");
					if(map.getnumChange()==1) {
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("1");
						
						tiles[4] = tiles[13];
					}
					else if(map.getnumChange()==0) {
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight2.png");
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("2");
						tiles[4] = tiles[14];
					}
					
				}
			}
				else {
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight.png");
					//tiles[4] = ImageIO.read(spriteImage);
				
				
				if(map.getnumChange()==1) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight2.png");
					tiles[4] = tiles[5];
					
					
					
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassRight.png");
					
					tiles[4] = tiles[6];
				
				}
				}
		}
		 if(map.getdirectionName()=="Down") {
			 //System.out.println("2");
		        
			 if(map.getOnDoor() || map.isOnGrass()){
					if(map.getOnDoor()) {
				
				spriteImage = new File(imageDir + "\\BlondeAvatarDoorDown.jpg");
				tiles[4] = ImageIO.read(spriteImage);
				map.setOnDoor(false);
				checkDoor(pcX,pcY,map);
				//System.out.println(pcX + "" +pcY);
				
			}
			
				if(map.isOnGrass()) {
					map.setOnGrass(false);
					//spriteImage = new File(imageDir + "\\BlondeAvatarGrassDown.png");
					if(map.getnumChange()==1) {
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("1");
						tiles[4] = tiles[19];
						
					}
					else if(map.getnumChange()==0) {
						//spriteImage = new File(imageDir + "\\BlondeAvatarGrassDown2.png");
						
						//tiles[4] = ImageIO.read(spriteImage);
						//System.out.println("2");
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
					tiles[4] =tiles[11];
					
					
				}
				else if(map.getnumChange()==0) {
					//spriteImage = new File(imageDir + "\\BlondeAvatarSideWalkDown2.png");
					
					//tiles[4] = ImageIO.read(spriteImage);
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
    public void render(map map, int pcX, int pcY ) throws IOException
    {   
    	
    
    	  //background.add(inventory, BorderLayout.CENTER);
    	
        //  add(new JPanel()
        		  
     
         

        	



        
        
    	
        
         img = new BufferedImage(480, 500, IMAGE_TYPE); // here you should create a compatible BufferedImage
       // img = new BufferedImage(1900, 1000, IMAGE_TYPE );   // here you should create a compatible BufferedImage
       // setSize(img.getWidth(), img.getHeight());
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // setSize(img.getWidth(),img.getHeight());
		
		//background.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//background.setFocusableWindowState(true);
	//	background.setAlwaysOnTop(true);
         
        
		//System.out.println(map.getOnDoor());
        for (int i = pcX - 4; i <= pcX + 4; i++) {
            for (int j = pcY - 4; j <= pcY + 4; j++) {
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
                        		
                        		img.setRGB( y + ((j - (pcY - 4)) * 50),  x + ((i - (pcX - 4))  * 50),  tile.getRGB(x,y) ); 
                        	
                       
                    }	
            		}
            	}
            }
            
        }
        repaint();
      
        //setVisible( true );
        //setVisible(true);
    }
   
	
	private int retImgArrayIndex(char c) {
		switch (c) {
		case '*':
			return 0;
		case '0':
			return 1;
		case '`':
			return 2;
		case 's':
			return 3;
		case '@':
			return 4;
		case '!':
			return 7;
		case '|':
			return 8; 
		default:
			return 0;
		}
	}
	private void checkDoor(int x ,int y , map map){
		
		if(x==36 & y ==111){
			door.SnedecorHall(1500, 2);
			
		}
		else if(x==35 & y == 74) {
			door.CooverHall(900, 1, map);
		}
		else if(x ==35 & y ==27) {
			door.CooverHall(900, 1, map);
			
		}
		else if(x==49 & y ==97) {
			door.AtanasoffHall(1500, 2);
			
		}
		else if(x==71 & y ==155) {
			door.ParksLibrary(1500, 4, 2);
		}
		else if(x==94 & y ==11) {
			door.DurhamHall(1, 1);
		}
		else if(x== 89 & y == 79) {
			door.SweenyHall(1, 2);
		}
		else if(x==137 & y ==77) {
			door.HooverHall(1, 2);
		}
		else if(x==189 & y ==32) {
			door.BlackEngineering(1, 2);
		}
		else if(x== 171 & y == 96) {
			door.PearsonHall(1, 2, 3);
		}
		else if(x== 139 & y ==100) {
			door.MarstonHall(1, 1);
		}
		else if(x==89 & y ==169) {
			door.CaribouCafe(900);
		}
		else if(x==160 & y == 182) {
			door.BeardshearHall(1, 2);
		}
		
		
	}

}
