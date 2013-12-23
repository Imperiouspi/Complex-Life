package types;

public class World {
	public LifeForm[][] Life;
	public Biome[][] Lands;
	public Tile[][] grid;
	
	public World(int landnumber){
		Lands = new Biome[landnumber][landnumber];
		grid = new Tile[landnumber * 20][landnumber * 20];
	}
	
	public void advance(){
		for(int i = 0; i < Life.length; i++){
			for(int j = 0; j < Life[i].length; j++){
				Life[i][j].Move();
			}
		}
	}
	
	public void Apocalypse(){
		for(int i = 0; i < Life.length; i++){
			for(int j = 0; j < Life[i].length; j++){
				Life[i][j].Die();
			}
		}
	}
}
