package view;

import model.*;

import java.lang.reflect.Method;
import java.util.ArrayList;

import controller.*;
import util.Observable;
import util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

public class TerriPanel extends Region implements Observer {

	private Canvas canvas1;
	private Territorium terri1;

	private int canvasWidth;
	private int canvasHeight;

	private int gap = 1;

	private Image wallImage;
	private Image turtleNORTHImage;
	private Image turtleEASTImage;
	private Image turtleSOUTHImage;
	private Image turtleWESTImage;
	private Image salatImage;

	private ContextMenu contextMenu = new ContextMenu();

	private SelectedItem choosenItem1;

	private MethodArray methodArray;

	private GraphicsContext gc;
	ScrollPane sp1;

	public TerriPanel(Territorium ter, ScrollPane sp, SelectedItem choosenItem) {

		loadImages();

		terri1 = ter;
		sp1 = sp;

		this.choosenItem1 = choosenItem;

		this.canvasWidth = ter.YSize * 34 + gap;
		this.canvasHeight = ter.XSize * 34 + gap;

		this.canvas1 = new Canvas(this.canvasWidth + gap, this.canvasHeight + gap);
		this.gc = canvas1.getGraphicsContext2D();

		// Spielfeld wird aufgebaut
		setPlayGround();

		drawCanvas(gc, ter);

		this.center(sp1.getViewportBounds(), this.canvas1);
		sp1.viewportBoundsProperty().addListener((observable, oldvalue, newValue) -> {
			this.center(newValue, this.canvas1);
		});

		TerriPanelEventhandler eventhandler = new TerriPanelEventhandler(terri1, this);
		canvas1.addEventHandler(MouseEvent.ANY, eventhandler);
		terri1.addObserver(this);

	}

	public SelectedItem getChoosenItem1() {
		return choosenItem1;
	}

	public void setChoosenItem1(SelectedItem choosenItem1) {
		this.choosenItem1 = choosenItem1;
	}

	// ScrollPane-Centering by:
	// https://stackoverflow.com/questions/30687994/how-to-center-the-content-of-a-javafx-8-scrollpane/40783645#40783645
	private void center(Bounds viewPortBounds, Canvas centeredCanvas) {
		double width = viewPortBounds.getWidth();
		double height = viewPortBounds.getHeight();
		if (width > this.canvasWidth) {
			centeredCanvas.setTranslateX((width - this.canvasWidth) / 2);
		} else {
			centeredCanvas.setTranslateX(0);
		}
		if (height > this.canvasHeight) {
			centeredCanvas.setTranslateY((height - this.canvasHeight) / 2);
		} else {
			centeredCanvas.setTranslateY(0);
		}

	}

	public Canvas getCanvas() {
		return canvas1;
	}

	// Male Spielfeld
	private void drawCanvas(GraphicsContext gc1, Territorium terri1) {

		for (int x = 0; x < terri1.YSize; x++) {
			for (int y = 0; y < terri1.XSize; y++) {

				gc1.setFill(Color.LIGHTGREY);
				gc1.setStroke(Color.BLACK);

				gc1.strokeRect(gap + (x * 34), gap + (y * 34), 34, 34);
				gc1.fillRect(gap + (x * 34), gap + (y * 34), 34, 34);

				// Hintergrund wird gezeichnet
				if (terri1.playGround[x][y] == -1) {
					gc1.drawImage(wallImage, gap + (x * 34), gap + (y * 34), 34, 34);
				}
				// Salat wird mit entsprechender Anzahl angezeigt.
				if (terri1.playGround[x][y] > 0) {
					gc1.drawImage(salatImage, gap + (x * 34), gap + (y * 34), 34, 34);
					gc1.strokeText(String.valueOf(terri1.playGround[x][y]), gap + (x * 34) + 24, gap + (y * 34) + 30);
				}

				// Turtle guckt in die entsprechende Richtung
				if (terri1.getTurtleXPos() == y && terri1.getTurtleYPos() == x) {
					switch (terri1.getTurtleDirection()) {
					case 0:
						gc1.drawImage(turtleNORTHImage, gap + (x * 34), gap + (y * 34), 34, 34);
						break;
					case 1:
						gc1.drawImage(turtleEASTImage, gap + (x * 34), gap + (y * 34), 34, 34);
						break;
					case 2:
						gc1.drawImage(turtleSOUTHImage, gap + (x * 34), gap + (y * 34), 34, 34);
						break;
					case 3:
						gc1.drawImage(turtleWESTImage, gap + (x * 34), gap + (y * 34), 34, 34);
						break;
					}

				}

			}
		}

	}

	private void setPlayGround() {
		terri1.setTurtlePos(0, 0);

	}

	private void loadImages() {

		this.wallImage = new Image(getClass().getResource("media/Wall32.png").toString());

		this.turtleNORTHImage = new Image(getClass().getResource("media/turtleNORTH.png").toString());
		this.turtleEASTImage = new Image(getClass().getResource("media/turtleEAST.png").toString());
		this.turtleSOUTHImage = new Image(getClass().getResource("media/turtleSOUTH.png").toString());
		this.turtleWESTImage = new Image(getClass().getResource("media/turtleWEST.png").toString());

		this.salatImage = new Image(getClass().getResource("media/Salat24.gif").toString());

	}

	public void showContextMenu() {
		this.methodArray = new MethodArray(terri1.getTurtle());
		ArrayList<Method> methods = methodArray.getMethList();

		contextMenu.hide();
		contextMenu = new ContextMenu();

		// ContextMenu-Handling von:
		// https://o7planning.org/en/11115/javafx-contextmenu-tutorial
		this.canvas1.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {
				Class<?>[] currentPara;
				String paraString = "";
				for (Method method : methods) {
					if (method.getParameterCount() != 0) {
						currentPara = method.getParameterTypes();
						for (int i = 0; i < currentPara.length; i++) {
							paraString = paraString + " " + currentPara[i].getName();
						}
					}

					MenuItem item = new MenuItem(
							method.getReturnType() + " " + method.getName() + "(" + paraString + ")");
					
					if( method.getParameterCount() !=0) { item.setDisable(true);}
					paraString = "";
					item.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							try {
								method.invoke(terri1.getTurtle());
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					});
					contextMenu.getItems().addAll(item);
				}
				contextMenu.show(canvas1, event.getScreenX(), event.getScreenY());
			}

		});

	}

	@Override
	public void update() {
		if (Platform.isFxApplicationThread()) {
			this.canvasWidth = terri1.YSize * 34 + gap;
			this.canvasHeight = terri1.XSize * 34 + gap;

			gc.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
			this.canvas1.setWidth(canvasWidth + gap);
			this.canvas1.setHeight(canvasWidth + gap);

			drawCanvas(this.gc, terri1);
		} else {
			Platform.runLater(() -> drawCanvas(gc, terri1));
		}
	}
}
