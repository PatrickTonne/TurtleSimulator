package model;
import java.util.Arrays;
import util.Observable;
import java.lang.System;

public class Territorium extends Observable {
	// Größe des Spielfelds
	final int startCols = 20;
	final int startRows = 20;

	public int XSize = startCols;
	public int YSize = startRows;

	// Direction-Möglichkeiten
	final int north = 0;
	final int east = 1;
	final int south = 2;
	final int west = 3;

	final int wall = -1;

	public int[][] playGround = new int[YSize][XSize]; // -1 = WALL, 0 = Leer, >0 Anzahl an Salatköpfen auf dem Feld.

	// Turtle Attribute
	private Turtle turtle = new Turtle();
	private int turtleXPos = 0;
	int turtleYPos = 0;
	private int turtleDirection = 1;
	private int salatCounter = 0;
	
	public Territorium() {
		turtle.addTerri(this);
	}

	// Ausgewähltes Item in der ToolBar/ MenüBar


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
		try {
		switch (this.turtleDirection) {
		case (north):
			if (isInTerri(turtleXPos - 1, turtleYPos) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos - 1, turtleYPos) == true) {
				throw new WallException();
			} else {
				this.turtleXPos--;
			}
			break;
		case (west):
			if (isInTerri(turtleXPos, turtleYPos - 1) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos, turtleYPos - 1) == true) {
				throw new WallException();
			} else {
				this.turtleYPos--;
			}
			break;
		case (south):
			if (isInTerri(turtleXPos + 1, turtleYPos) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos + 1, turtleYPos) == true) {
				throw new WallException();
			} else {
				this.turtleXPos++;
			}
			break;
		case (east):
			if (isInTerri(turtleXPos, turtleYPos + 1) == false) {
				throw new OutOfTerritoryException();
			} else if (isWall(turtleXPos, turtleYPos + 1) == true) {
				throw new WallException();
			} else {
				this.turtleYPos++;
			}
			break;
		}
		} catch (Exception e) {
			
		}
		
		notifyObservers();
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
	public boolean isWall(int x, int y) {
		try {
			if (playGround[y][x] == wall) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			
			return false;
			
		}

	}

	public void setTurtlePos(int editXPos, int editYPos) {
		this.turtleXPos = editXPos;
		this.turtleYPos = editYPos;
		
		notifyObservers();
	}

	public int getTurtleDirection() {
		return turtleDirection;
	}

	public void setTurtleDirection(int turtleDirection) {
		this.turtleDirection = turtleDirection;
		
		notifyObservers();
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
		
		notifyObservers();
	}

	public void setWall(int x, int y) throws OutOfTerritoryException {
		if (isInTerri(x, y) == true && (turtleXPos != x || turtleYPos != y)) {
			this.playGround[y][x] = wall;
		}
		
		notifyObservers();
	}

	public void setSalat(int x, int y) throws OutOfTerritoryException, WallException {
		if (isInTerri(x, y) == false) {
			throw new OutOfTerritoryException();
		} else if (isWall(x, y) == true) {
			throw new WallException();
		} else {
			playGround[y][x]++;
		}
		
		notifyObservers();

	}

	public int[][] getPlayGround() {
		return playGround;
	}

	public void turtleTake() throws noSalatOnFieldException {
		if (this.playGround[turtleYPos][turtleXPos] <= 0) {
			throw new noSalatOnFieldException();
		} else {
			this.turtle.salatCount++;
			this.playGround[turtleYPos][turtleXPos]--;
		}
		
		notifyObservers();
	}

	public void turtleDrop() throws noSalatInMouthException {
		try {
			if (this.turtle.salatCount <= 0) {
				throw new noSalatInMouthException();
			} else {
				this.turtle.salatCount--;
				this.playGround[this.turtleYPos][this.turtleXPos]++;
			}
			
		} catch (OwnException e) {
			// TODO: handle exception
		}
		
		notifyObservers();
	}

	public void deleteTile(int x, int y) {

		playGround[y][x] = 0;
		
		notifyObservers();
	}

	public void reSize(int rows, int columns) {

		int[][] newPlayground = new int[rows][columns];

		for (int x = 0; x < this.playGround.length && x < newPlayground.length; x++) {
			for (int y = 0; y < this.playGround[0].length && y < newPlayground[0].length; y++) {
				newPlayground[x][y] = this.playGround[x][y];
			}
		}

		this.XSize = columns;
		this.YSize = rows;

		playGround = newPlayground;

		if (!isInTerri(turtleXPos, turtleYPos)) {

			deleteTile(0, 0);
			setTurtlePos(0, 0);
		}

		
		notifyObservers();
	}

	public void clearPlayground(int x, int y) throws OutOfTerritoryException {
		if (isInTerri(x, y)) {
			this.playGround[x][y] = 0;
		} else {
			throw new OutOfTerritoryException();
		}
		
		notifyObservers();
	}

	public void setTurtle(Turtle turtle) {
		turtle.setTerritorium(this);
		this.turtle = turtle;
		
	}
	public Turtle getTurtle() {
		
		return this.turtle;
	}
}
