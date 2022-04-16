/**
 * 
 * @author Grant Duncan
 *
 */
package main;

import java.util.ArrayList;

public class moveNPC  {
	
	private map map;

	
	public moveNPC(map map, PC pc, ArrayList<NPC> npcs, int time) {
		this.map = map;
		
		for(int i=0;i<npcs.size();i++){
			//for(int j=0; j<(npcs.get(i).getSpeed()*time)/100;j++){
			
			//add one to each npc's time value. Therefore, if they have the same speed as PC, they should get one
			//move per turn. if they have twice pc speed, they get two. And if they have half, they only get a turn
			//every other
			npcs.get(i).addTime(time);
			int moves = (npcs.get(i).getSpeed()*npcs.get(i).getTime())/pc.getSpeed();
			for(int j=0; j<moves;j++){
				npcs.get(i).update(pc, map);
			}
		}
	}
	
}