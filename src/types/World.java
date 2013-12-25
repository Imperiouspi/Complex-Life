package types;

import biomes.Jungle;

public class World {
	public LifeForm[][] Life;
	public Biome[][] Lands;
	public Tile[][] grid;
	
	public World(int landnumber){
		Lands = new Biome[landnumber][landnumber];
		for(int i = 0; i < landnumber; i++){
			for(int j = 0; j < landnumber; j++){
				Lands[i][j] = worldBiomeGen(i, j);
			}
		}
		grid = new Tile[landnumber * 20][landnumber * 20];
		System.out.println("WAIT!");
	}
	
	private Biome worldBiomeGen(int i, int j) {
		Biome biome = null;
		int random = (int)(Math.random()* 7);
		if(random >= 0){
			biome = new Jungle(i, j);
		}
		return biome;
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
