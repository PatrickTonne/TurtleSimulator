import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Territorium {
	// Größe des Spielfelds
	final int startCols = 10;
	final int startRows = 30;

	int XSize = startRows;
	int YSize = startCols;

	// Direction-Möglichkeiten
	final int north = 0;
	final int east = 1;
	final int south = 2;
	final int west = 3;

	final int wall = -1;

	int[][] playGround = new int[YSize][XSize]; // -1 = WALL, 0 = Leer, >0 Anzahl an Salatköpfen auf dem Feld.

	// Turtle Attribute
	private Turtle turtle = new Turtle();
	private int turtleXPos = 0;
	int turtleYPos = 0;
	private int turtleDirection = west;
	private int salatCounter = 0;

	// Konstruktor

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

	public void moveTurtle() throws OutOfTerritoryException, WallException {
		switch (this.turtleDirection) {
		case (north):
			if (isInTerri(turtleXPos, turtleYPos - 1) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos, turtleYPos - 1) == true) {
				throw new WallException();
			} else {
				this.turtleYPos--;
			}
			break;
		case (west):
			if (isInTerri(turtleXPos - 1, turtleYPos) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos - 1, turtleYPos) == true) {
				throw new WallException();
			} else {
				this.turtleXPos--;
			}
			break;
		case (south):
			if (isInTerri(turtleXPos, turtleYPos + 1) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos, turtleYPos + 1) == true) {
				throw new WallException();
			} else {
				this.turtleYPos++;
			}
			break;
		case (east):
			if (isInTerri(turtleXPos + 1, turtleYPos) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos + 1, turtleYPos) == true) {
				throw new WallException();
			} else {
				this.turtleXPos++;
			}
			break;
		}
	}

	// Hilfmethode: Ist das Feld im Territorium?

	private boolean isInTerri(int x, int y) {
		if (x < 0 || x > this.XSize || y < 0 || y > this.YSize) {
			return false;
		} else {
			return true;
		}

	}

// Hilfsmethode: Ist das Feld eine Wand?
	private boolean isWall(int x, int y) {
		if (playGround[y][x] == wall) {
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

	public void turtleRight() {
		if (turtleDirection == west) {
			this.turtleDirection = north;
		} else {
			this.turtleDirection++;
		}
	}

	public void setWall(int x, int y) throws OutOfTerritoryException {
		if (isInTerri(x, y) == true) {
			this.playGround[y][x] = wall;
		} else {
			throw new OutOfTerritoryException();

		}

	}

	public void setSalat(int x, int y) throws OutOfTerritoryException, WallException {
		if (isInTerri(x, y) == false) {
			throw new OutOfTerritoryException();
		} else if (isWall(x, y) == true) {
			throw new WallException();
		} else {
			playGround[y][x]++;
		}

	}

	public int[][] getPlayGround() {
		return playGround;
	}

	public void setTurtleXPos() {
		this.turtleYPos = this.turtleYPos - 1;
	}

	public void turtleTake() throws noSalatOnFieldException {
		if (this.playGround[turtleYPos][turtleXPos] <= 0) {
			throw new noSalatOnFieldException();
		} else {
			this.turtle.salatCount++;
			this.playGround[turtleYPos][turtleXPos]--;
		}
	}

	public void turtleDrop() throws noSalatInMouthException {
		if (this.turtle.salatCount<= 0) {
			throw new noSalatInMouthException();
		} else {
			this.turtle.salatCount--;
			this.playGround[this.turtleYPos][this.turtleXPos]++;
		}
	}
}
