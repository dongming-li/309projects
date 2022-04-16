/**
 * 
 * @author Grant Duncan
 *
 */
import java.util.ArrayList;

public class quests implements java.io.Serializable{

	private ArrayList<int[]> quest;
	
	public quests(){
		quest = new ArrayList();
	}
	
	public void addQuest(ArrayList<int[]> quest){
		this.quest=quest;
	}
	
	public int[] currentQuest(int number){
		return quest.get(number);
	}
	

	
	public void printQuest(int number){
		System.out.println("Go to "+quest.get(number)[0]+","+quest.get(number)[1]);
	}
	public ArrayList<int[]> questLog(){
		return quest;
	}
}
