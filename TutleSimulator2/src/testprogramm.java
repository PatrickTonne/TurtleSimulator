import java.io.Console;

import com.sun.javafx.iio.ios.IosDescriptor;

public class testprogramm {

	public static void main(String[] args) {
		int move = 0;

		Territorium terri1 = new Territorium();
		terri1.setWall(0, 1);
		terri1.setWall(0, 1);
		terri1.setSalat(0, 2);
		terri1.setSalat(6, 5);
		terri1.setSalat(6, 5);
		terri1.setSalat(6, 5);

		terri1.setTurtlePos(6, 6);
		terri1.setTurtleDirection(0);

		do {
			for (int x = 0; x < terri1.YSize; x++) {
				for (int y = 0; y < terri1.XSize; y++) {

					if (terri1.getTurtleXPos() == y && terri1.getTurtleYPos() == x) {
						System.out.print(getTurtle(terri1));
					} else {
						System.out.print(terri1.playGround[x][y]);
					}
				}
				System.out.println();
			}
			System.out.println();
			move = IO.readInt("rechtsUm = 0,vor = 1, Salat nehemen = 2, Salat hinlegen =3");

			if (move == 0) {
				terri1.turtleRight();
			} else if (move == 1) {
				terri1.moveTurtle();
			} else if (move == 2) {
				terri1.turtleTake();
			} else {
				terri1.turtleDrop();
			}
		} while (true);

	}

	static public String getTurtle(Territorium t1) {
		if (t1.getTurtleDirection() == 0) {
			return "^";
		} else if (t1.getTurtleDirection() == 1) {
			return ">";
		} else if (t1.getTurtleDirection() == 2) {
			return "v";
		} else {
			return "<";
		}

	}

}
