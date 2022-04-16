/**
 * 
 * @author Grant Duncan
 *
 */
package gameMain;

import java.util.ArrayList;

/**
 * The Class moveNPC.
 */
public class moveNPC  {
	
	/** The map. */
	private map map;

	
	/**
	 * Instantiates a new move NPC.
	 *
	 * @param map the map
	 * @param pc the pc
	 * @param npcs the npcs
	 * @param time the time
	 */
	public moveNPC(map map, PC pc, ArrayList<NPC> npcs, int time) {
		this.map = map;
		
		
		for(int i=0;i<npcs.size();i++){
			//for(int j=0; j<(npcs.get(i).getSpeed()*time)/100;j++){
			
			//add one to each npc's time value. Therefore, if they have the same speed as PC, they should get one
			//move per turn. if they have twice pc speed, they get two. And if they have half, they only get a turn
			//every other
			npcs.get(i).addTime(time);
			int moves= (npcs.get(i).getSpeed()*npcs.get(i).getTime())/pc.getSpeed();
			for(int j=0; j<moves;j++){
//				System.out.println(npcs.get(i).getSymbol());
				if(!npcs.get(i).update(pc, map)){
					if(npcs.get(i).isKO()){
						
						MainFrame.npcs.add(new security(MainFrame.pc.getRow()+5, MainFrame.pc.getCol()+5, map));
					}
					npcs.remove(i);
					i--; //I think I need this... needs testing
					break;
				}
			}
		}
	}
	
}