/**
 * 
 * @author Grant Duncan
 *
 */
import java.io.IOException;
import java.util.Scanner;

public class movePC {
	Scanner in = new Scanner(System.in);
	// would rather use something like curses

		public movePC(map map, PC pc, quests quest, int number) throws IOException{
			//map.renderMap(map);
			map.renderPCView(map, pc);
			quest.printQuest(number);
			String next = in.next();
			if(next.equals("map")){
				map.renderMap(map);
				next=in.next();
				
			}
			if(next.equals("save")){
				save save = new save(map, pc, quest);
			}
			if(next.equals("quit")){
				System.exit(0);
			}
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (next.contains("w")) {
				if (map.isWalkable(pc.getRow() - 1, pc.getCol()))
					pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol()));
				if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) {
				} else
					map.setRowCol(pc.getRow(), pc.getCol(), '@');
			} else if (next.contains("d")) {
				if (map.isWalkable(pc.getRow(), pc.getCol() + 1))
					pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() + 1));
				if (map.placePC(pc.getRow(), pc.getCol() + 1, pc)) {
				} else
					map.setRowCol(pc.getRow(), pc.getCol(), '@');
			} else if (next.contains("a")) {
				if (map.isWalkable(pc.getRow(), pc.getCol() - 1))
					pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() - 1));
				if (map.placePC(pc.getRow(), pc.getCol() - 1, pc)) {
				} else
					map.setRowCol(pc.getRow(), pc.getCol(), '@');
			} else if (next.contains("s")) {
				if (map.isWalkable(pc.getRow() + 1, pc.getCol()))
					pc.setUnderPC(map.getRowCol(pc.getRow() + 1, pc.getCol()));
				if (map.placePC(pc.getRow() + 1, pc.getCol(), pc)) {
				} else
					map.setRowCol(pc.getRow(), pc.getCol(), '@');
			}
				
//				if (map.getRowCol(pc.getRow() - 1, pc.getCol()) != '0' && map.getRowCol(pc.getRow() - 1, pc.getCol()) != '*')
//					pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol()));
//				if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) {
//				} else
//					map.setRowCol(pc.getRow(), pc.getCol(), '@');
//			} else if (next.contains("d")) {
//				if (map.getRowCol(pc.getRow(), pc.getCol() + 1) != '0' && map.getRowCol(pc.getRow(), pc.getCol() + 1) != '*')
//					pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() + 1));
//				if (map.placePC(pc.getRow(), pc.getCol() + 1, pc)) {
//				} else
//					map.setRowCol(pc.getRow(), pc.getCol(), '@');
//			} else if (next.contains("a")) {
//				if (map.getRowCol(pc.getRow(), pc.getCol() - 1) != '0' && map.getRowCol(pc.getRow(), pc.getCol() - 1) != '*')
//					pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() - 1));
//				if (map.placePC(pc.getRow(), pc.getCol() - 1, pc)) {
//				} else
//					map.setRowCol(pc.getRow(), pc.getCol(), '@');
//			} else if (next.contains("s")) {
//				if (map.getRowCol(pc.getRow() + 1, pc.getCol()) != '0' && map.getRowCol(pc.getRow() + 1, pc.getCol()) != '*')
//					pc.setUnderPC(map.getRowCol(pc.getRow() + 1, pc.getCol()));
//				if (map.placePC(pc.getRow() + 1, pc.getCol(), pc)) {
//				} else
//					map.setRowCol(pc.getRow(), pc.getCol(), '@');
//			}

		}
}
