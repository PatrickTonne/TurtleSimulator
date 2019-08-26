
public class Turtle {
	
	int salatCount;
	
	int getXPosition() {
		
		return this.posX;
		
	}
	
	int getYPosition() {
		
		return this.posY;
	}
	
	direction getDirection() {
		return this.direction;
	}
	
	int getSalatCount() {
		return this.salatCount;
	}
	
	 void linksUm() {
		if (this.direction == direction.NORTH) { this.direction = direction.WEST;}
		if (this.direction == direction.WEST) { this.direction = direction.SOUTH;}
		if (this.direction == direction.SOUTH) { this.direction = direction.EAST;}
		if (this.direction == direction.EAST) { this.direction = direction.NORTH;}
	}
	 
	void vor() {
		if (this.direction == direction.NORTH) { this.direction = direction.WEST;}
		else if (this.direction == direction.WEST) { this.direction = direction.SOUTH;}
		else if (this.direction == direction.SOUTH) { this.direction = direction.EAST;}
		else if (this.direction == direction.EAST) { this.direction = direction.NORTH;}	
		else {
			throw RuntimeException;
		}
	}
	
	
}
