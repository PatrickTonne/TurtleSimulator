
public class Turtle {
	
	int salatCount =0;
	
	private Territorium territorium;
	
	
	public Turtle(Territorium ter) {
		this.territorium = ter;
	}

	protected int getSalatCount() {
		return salatCount;
	}
	void vor() {
		
		this.territorium.moveTurtle();
	}
	void rechtsUm() {
		this.territorium.turtleRight();
	}

	void pick() {
		this.territorium.turtleTake();
	}
	
	void drop() {
		this.territorium.turtleDrop();
	}
}