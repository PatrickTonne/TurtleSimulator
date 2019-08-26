import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Territorium {
	// Größe des Spielfelds
	final int startCols = 10;
	final int startRows = 12;

	int XSize = startCols;
	int YSize = startRows;

	// Direction-Möglichkeiten
	final int north = 0;
	final int east = 1;
	final int south = 2;
	final int west = 3;

	final int wall = -1;

	int[][] playGround; // -1 = WALL, 0 = Leer, >0 Anzahl an Salatköpfen auf dem Feld.

	// Turtle Attribute
	private Turtle turtle;
	private int turtleXPos = 0;
	private int turtleYPos = 0;
	private int turtleDirection = west;
	private int salatCounter = 0;

	// Konstruktor
	Territorium() {
		for (int i = 0; i < XSize; i++) {
			for (int x = 0; x < YSize; x++) {
				playGround[i][x] = 0;
			}

		}
	}

	public int getXSize() {
		return XSize;
	}

	public void setXSize(int xSize) {
		XSize = xSize;
	}

	public int getYSize() {
		return YSize;
	}

	public void setYSize(int ySize) {
		YSize = ySize;
	}

	public int getTurtleXPos() {
		return turtleXPos;
	}

	public int getTurtleYPos() {
		return turtleYPos;
	}

	public void moveTurtle() throws OutOfTerritoryException, WandException {
		switch (this.turtleDirection) {
		case (north):
			if (isInTerri(turtleXPos, turtleYPos - 1) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos, turtleYPos - 1) == true) {
				throw new WandException();
			} else {
				this.turtleYPos--;
			}
			break;
		case (west):
			if (isInTerri(turtleXPos - 1, turtleYPos) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos - 1, turtleYPos) == true) {
				throw new WandException();
			} else {
				this.turtleXPos--;
			}
			break;
		case (south):
			if (isInTerri(turtleXPos, turtleYPos + 1) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos, turtleYPos + 1) == true) {
				throw new WandException();
			} else {
				this.turtleYPos++;
			}
			break;
		case (east):
			if (isInTerri(turtleXPos + 1, turtleYPos) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos + 1, turtleYPos) == true) {
				throw new WandException();
			} else {
				this.turtleXPos++;
			}
			break;
		}
	}

	// Hilfmethoden

	private boolean isInTerri(int x, int y) {
		if (x < 0 || x > this.XSize || y < 0 || y > this.YSize) {
			return false;
		} else {
			return true;
		}

	}

	private boolean isWall(int x, int y) {
		if (playGround[x][y] == wall) {
			return true;
		} else {
			return false;
		}
	}

	public void setTurtlePos(int editXPos, int editYPos) {
		this.turtleXPos = editXPos;
		this.turtleYPos = editYPos;
	}

	public int getTurtleDirection() {
		return turtleDirection;
	}

	public void setTurtleDirection(int turtleDirection) {
		this.turtleDirection = turtleDirection;
	}

	public int getSalatCounter() {
		return salatCounter;
	}

	public void addSalatCounter() {
		this.salatCounter = this.salatCounter + 1;
	}

	public void takeSalatCounter() {
		this.salatCounter = this.salatCounter - 1;
	}

	final private void setPlayGround(int[][] playGround) {
		this.playGround = playGround;
	}

	public void setWall(int x, int y) throws OutOfTerritoryException {
		if (isInTerri(x, y) == true) {
			this.playGround[x][y] = wall;
		} else {
			throw new OutOfTerritoryException();

		}

	}
	public void setSalat(int x, int y) throws OutOfTerritoryException,WandException{
		if (isInTerri(x, y) == false) {
			throw new OutOfTerritoryException();
		} else if (isWall(x, y) == true) {
			throw new WandException();
		}else {
			playGround[x][y]++; 
		}
		
	}
}
