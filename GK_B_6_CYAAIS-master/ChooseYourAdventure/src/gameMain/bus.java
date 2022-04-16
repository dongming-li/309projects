package gameMain;

/**
 * The Class bus.
 */
public class bus extends NPC{
	
	/**
	 * Instantiates a new bus.
	 *
	 * @param row the row
	 * @param col the col
	 * @param map the map
	 */
	public bus(int row, int col, map map){
		super(row, col, map);
		super.setSymbol(mapNode.BUS);
		super.setSpeed(40);
		super.setAtkValue(100);
		super.setAtkRange(1);
		super.setHealth(100000);
		
	}


	/* (non-Javadoc)
	 * @see gameMain.NPC#update(gameMain.PC, gameMain.map)
	 */
	public boolean update(PC pc, map map) {
		// TODO Auto-generated method stub
		if(super.move(15, 5, map,pc))
			return true;
		return false;
	}
	
}
