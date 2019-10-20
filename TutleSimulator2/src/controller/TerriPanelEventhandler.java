package controller;

import javafx.event.EventHandler;
import model.*;
import controller.*;
import view.*;
import javafx.scene.input.MouseEvent;

public class TerriPanelEventhandler implements EventHandler<MouseEvent> {

	private Territorium terri1;
	private TerriPanel tp1;

	final int turtleChoosen = 0;
	final int salatChoosen = 1;
	final int wallChoosen = 2;
	final int deleteChoosen = 3;

	public TerriPanelEventhandler(Territorium ter, TerriPanel tp) {

		terri1 = ter;
		tp1 = tp;

	}

	public int getNearTile(double value) {

		int output = ((int) (value / 34));
		return output;

	}

	@Override
	public void handle(MouseEvent event) {
		if (event.isSecondaryButtonDown() == true) {
			int xTile = getNearTile(event.getY());
			int yTile = getNearTile(event.getX());
			if (terri1.getTurtleXPos() == xTile && terri1.getTurtleYPos() == yTile) {
				tp1.showContextMenu();
			}
		}

		else if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			int xTile = getNearTile(event.getY());
			int yTile = getNearTile(event.getX());

			if (xTile < terri1.XSize && yTile < terri1.YSize) {

				switch (tp1.getChoosenItem1().choosenItem) {

				case turtleChoosen:
					if (!(terri1.isWall(xTile, yTile))) {
						terri1.setTurtlePos(xTile, yTile);
						System.out.println("turtle");
					}
					break;

				case salatChoosen:
					terri1.setSalat(xTile, yTile);
					System.out.println("salat");
					break;

				case wallChoosen:
					terri1.setWall(xTile, yTile);
					System.out.println("wall");
					break;

				case deleteChoosen:
					terri1.deleteTile(xTile, yTile);
					System.out.println("delete");
					break;

				}
				System.out.println(xTile + "" + yTile);

			}
		}

		if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {

			if (tp1.getChoosenItem1().choosenItem == turtleChoosen && !event.isSecondaryButtonDown()) {
				int xTile = getNearTile(event.getY());
				int yTile = getNearTile(event.getX());

				if (!(terri1.isWall(xTile, yTile))) {
					terri1.setTurtlePos(xTile, yTile);
				}

			}

		}
	}
}
