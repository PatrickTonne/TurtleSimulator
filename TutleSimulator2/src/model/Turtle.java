package model;


public class Turtle {
	
	int salatCount =0;
	
	private Territorium territorium;
	
	
	public void addTerri(Territorium ter) {
		this.territorium = ter;
		
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
}