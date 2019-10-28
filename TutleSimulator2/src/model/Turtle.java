package model;

import util.Invisible;

public class Turtle {
	
	
	private Territorium territorium;
	
	protected void main() {
		
	}
	
	protected void addTerri(Territorium ter) {
		this.territorium = ter;
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
	
	protected void setTerritorium(Territorium territorium) {
		this.territorium = territorium;
	}
	
	public boolean salatInMouth() {
		return territorium.salatInMouth();
	}
	
	public boolean isSalatInTile() {
		return territorium.isSalatInTile();
	}
	
	public boolean FieldNotOccupied() {
		return territorium.fieldNotOccupied();
	}
}