package types;

import java.awt.Color;
import java.awt.Graphics;

//Maybe add id for easy access?

public abstract class LifeForm {
	public static String species;
	public static int MaxHealth, MaxHunger;
	public int healthLeft, hungerLeft;
	public static String[] eats, predators;
	public Color color;
	public static int LifeSpan;
	public int localx, localy, viewDistance;

	public LifeForm(int x, int y) {
		localx = x;
		localy = y;
	}

	public LifeForm() {

	}

	public abstract void Eat(LifeForm eaten);

	public void Move(Tile[][] grid){
		Tile[][]seen = new Tile[2* viewDistance + 1][2 * viewDistance + 1];
		int MoveToX = 0, MoveToY = 0;

		seen = getSeenSquares(grid);

		for(int i = 0; i < seen.length; i++){
			for(int j = 0; j < seen[i].length; j++){
				if(seen[i][j] != null && seen[i][j].isOccupied && isPredator(seen[i][j].Occupant)){
					MoveToX = getSideX(seen[i][j].Occupant.localx, this.localx);
				}
				else if(seen[i][j] != null && seen[i][j].isOccupied && isFood(seen[i][j].Occupant)){
					MoveToY = getSideY(seen[i][j].Occupant.localy, this.localy);
				}
			}
		}
		
		this.localx ++;
		localy += MoveToY;
	}

	public void Age() {
		LifeSpan--;
	}

	public boolean isPredator(LifeForm life){
		for(int i = 0; i < predators.length; i++){
			if (predators[i] != null && predators[i].equals(life.species)){
				return true;
			}
		}
		return true;
	}

	public boolean isFood(LifeForm life){
		for(int i = 0; i < eats.length; i++){
			if (eats[i] != null && eats[i].equals(life.species)){
				return true;
			}
		}
		return true;
	}

	public int getSideX(int xAnimal, int xSeer){
		if(xAnimal == xSeer){
			return 0;
		}
		else if (xAnimal < xSeer){
			return 1;
		}
		else if (xAnimal > xSeer){
			return -1;
		}
		return 1;
	}

	public int getSideY(int yAnimal, int ySeer){
		if(yAnimal == ySeer){
			return 0;
		}
		else if (yAnimal < ySeer){
			return 1;
		}
		else if (yAnimal > ySeer){
			return -1;
		}
		return 0;
	}

	public Tile[][] getSeenSquares(Tile[][]grid){
		Tile[][] seen = new Tile[viewDistance * 2 + 1][viewDistance * 2 + 1];
		int a = 0;
		int b = 0;

		for(int i  = this.localx-viewDistance; i < this.localx + viewDistance && this.localx + viewDistance < grid.length && a < seen.length && i< seen.length; i++, a++){
			if(i < 0){
				i = 0;
				if(a != 0)
					a--;
			}
			for(int j = this.localy-viewDistance; this.localy + viewDistance < grid[i].length && j < this.localy + viewDistance &&  b < seen[i].length && j < seen[i].length; j++, b++){
				if(i < 0){
					i = 0;
					if(a != 0)
						a--;
				}
				if(j < 0){
					j = 0;
					if(b != 0)
						b--;
				}
				seen[a][b] = grid[i][j];
			}
		}

		return seen;
	}
	public abstract void Die();

	public abstract void Breed();

	public abstract void findNutrients();

	public abstract void onEaten(LifeForm eating);

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(this.localx * 5, this.localy * 5, 6, 6);
	}
}
