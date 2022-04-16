/**
 * 
 * @author Grant Duncan
 *
 */
package main;

import java.awt.Event;

import java.awt.event.KeyEvent;


import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class movePC extends JPanel implements KeyListener {
	// Scanner in = new Scanner(System.in);
	// would rather use something like curses
	
    
     
	private PC pc;
	private map map;
	private Quests quest;
	private int number;
	private boolean action = false;
	static GraphicalStuff back;
	private char lastTile;
	
	

	public movePC(map map, PC pc, Quests quest, int number) throws IOException {
		
		
		this.pc = pc;
		this.map = map;
		this.quest = quest;
		this.number = number;
		//this.setName("Move input");
		// set window dimension
		//this.setSize(200, 200);
		//this.setVisible(true);
		// Ignore OS paint calls
		//this.setIgnoreRepaint(true);
		
		
		//this.requestFocus();
		//frame.setAlwaysOnTop(true);
		//addKeyListener(this);
		//pc.update();
		// pc.printStats();
		back = new GraphicalStuff(map, pc, pc.getRow(), pc.getCol());

		// map.renderMap(map);
		map.renderPCView(map, pc);
		/*quest.printQuest(number); */
		// String next = in.next();
		/*
		 * if(next.equals("map")){ map.renderMap(map); next=in.next();
		 * 
		 * } if(next.equals("save")){ save save = new save(map, pc, quest); }
		 * if(next.equals("quit")){ System.exit(0); }
		 */
		map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
		/*
		while (true) {

			while (!action) {

			}
			action = false;
			// back.fun(map, pc.getRow(), pc.getCol());

		}
		*/

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
			
		case KeyEvent.VK_UP: {
			
			map.setdirectionName("Up");
			if(map.getUpHeld()) {
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() - 1, pc.getCol())) {
				char mapTile = map.getRowCol(pc.getRow()-1, pc.getCol());
				 lastTile = mapTile;
				if (mapTile=='/') {
					map.setOnDoor(true);
				}
				if(map.getnumChange()<10 &map.getStayLeft()) {
					map.setOnLeft(true);
					map.setnumChange(map.getnumChange()+1);
					if(map.getnumChange()==9) {
						map.setnumChange(0);
						map.setStayLeft(false);
						
					}
				}
				else  if (map.getnumChange()!=5 & !map.getStayLeft()){
					map.setOnLeft(false);
					if(map.getnumChange()==4) {
		
						map.setStayLeft(true);
						
					}
					
					map.setnumChange(map.getnumChange()+1);
				}
				
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol()));
			}
			else {
				if(lastTile=='/') {
					map.setOnDoor(true);
				}
			}
			
			if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');
			
			
			action = true;

			
			
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			

		}
		}
			break;
		case KeyEvent.VK_RIGHT: {
			map.setdirectionName("Right");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow(), pc.getCol() + 1)){
				if(map.getnumChange()==0) {
					map.setnumChange(1);
					
				}
				else {
					map.setnumChange(0);
				}
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
			pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() + 1));
			
			
			}
			
				
			if (map.placePC(pc.getRow(), pc.getCol() + 1, pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			
			action = true;

			
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
			break;
		case KeyEvent.VK_LEFT: {
			map.setdirectionName("Left");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow(), pc.getCol() - 1)) {
				
				
				char mapTile = map.getRowCol(pc.getRow(), pc.getCol()-1);
				/*
				lastTile = mapTile;
				if (mapTile=='/') {
					map.setOnDoor(true);
				}
				*/
				if(map.getnumChange()==0) {
					map.setnumChange(1);
					
				}
				else {
					map.setnumChange(0);
				}
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() - 1));
				
				
			}
			/*
			else {
				if(lastTile=='/') {
					map.setOnDoor(true);
				}
			}
			*/
				
			if (map.placePC(pc.getRow(), pc.getCol() - 1, pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			action = true;
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
			break;
		case KeyEvent.VK_DOWN: {
			map.setdirectionName("Down");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() + 1, pc.getCol())) {
				
				if(map.getnumChange()==0) {
					map.setnumChange(1);
					
				}
				else {
					map.setnumChange(0);
				}
				
				map.renderPCView(map, pc);
				pc.update();
			pc.printStats();
			pc.setUnderPC(map.getRowCol(pc.getRow() + 1, pc.getCol()));
			
			}
				
			if (map.placePC(pc.getRow() + 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			
			action = true;

		
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
			break;
		
		}

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		

		if(ke.getKeyCode()==KeyEvent.VK_UP){
			map.setUpHeld(false);
			map.setdirectionName("UpOnce");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() - 1, pc.getCol())) {
				
				if(map.getAlternateFeet()==0) {
					map.setAlternateFeet(1);
					
				}
				else {
					map.setAlternateFeet(0);
				}
				
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol()));
			}
			else {
				if(lastTile=='/') {
					map.setOnDoor(true);
				}
			}
			
			if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');
			
			
			action = true;

			
			
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	
	 
             
         }

    
 
}
