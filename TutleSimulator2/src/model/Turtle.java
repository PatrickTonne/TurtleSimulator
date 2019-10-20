package model;

import util.Invisible;

public class Turtle {
	
	int salatCount =0;
	
	private Territorium territorium;
	
	public void main() {
		
	}
	
	
	public boolean addTerri(Territorium ter) {
		this.territorium = ter;
		return true;
		
	}

	public int getSalatCount() {
		return salatCount;
	}
	public void vor() {
		this.territorium.moveTurtle();
	}
	public void rechtsUm() {
		this.territorium.turtleRight();
	}
	public void pick() {
		this.territorium.turtleTake();
	}
	
	public void drop() {
		this.territorium.turtleDrop();
	}
	public boolean  setTerritorium(Territorium territorium) {
		this.territorium = territorium;
		return true;
	}
	
	
}