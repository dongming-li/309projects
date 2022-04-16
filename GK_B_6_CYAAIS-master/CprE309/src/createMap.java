
public class createMap {
	
	private map map;
	
	public createMap(int x, int y){
		map = new map(x, y);
		for(int i=0;i<map.getX();i++){
			for(int j=0;j<map.getY();j++){
				if(i==0||j==0){
					map.setXY(i, j, '*');
				}
			}
		}
	}
	

}
