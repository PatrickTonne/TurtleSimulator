package model;

import util.Invisible;

public class Turtle {
	
	int salatCount =0;
	
	private Territorium territorium;
	@util.Invisible
	public void main() {
		
	}
	
	
	public void addTerri(Territorium ter) {
		this.territorium = ter;
	}
	@Invisible
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
	@Invisible
	public void setTerritorium(Territorium territorium) {
		this.territorium = territorium;
	}
	
	
}