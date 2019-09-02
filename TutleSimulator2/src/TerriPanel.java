import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TerriPanel extends Region {

	Canvas canvas1;
	Territorium terri1;
	Image wallImage = new Image(getClass().getResource("media/Wall32.png").toString());
	Image turtleImage = new Image(getClass().getResource("media/Turtle24.png").toString());

	public TerriPanel(Territorium ter) {
		terri1 = ter;
		int xSize = ter.XSize * 34 +2;
		int ySize = ter.YSize * 34 +2;

		canvas1 = new Canvas(xSize, ySize);
		GraphicsContext gc = canvas1.getGraphicsContext2D();
		terri1.setWall(2, 2);
		terri1.setTurtlePos(6, 6);
		drawCanvas(gc, ter);

	}

	public Canvas getCanvas() {
		return canvas1;
	}

	private void drawCanvas(GraphicsContext gc1, Territorium terri1) {

		for (int x = 0; x < terri1.YSize; x++) {
			for (int y = 0; y < terri1.XSize; y++) {
				gc1.setFill(Color.LIGHTGREY);
				gc1.setStroke(Color.BLACK);
				gc1.strokeRect(2 + (x * 34), 2 + (y * 34), 34, 34);
				gc1.fillRect(2 + (x * 34), 2 + (y * 34), 34, 34);
				
				if (terri1.playGround[x][y] == -1) {
					gc1.drawImage(wallImage, 2 + (x * 34), 2 + (y * 34), 34, 34);
				}
				if (terri1.getTurtleXPos() == y && terri1.getTurtleYPos() == x) {
					gc1.drawImage(turtleImage, 2 + (x * 34), 2 + (y * 34), 34, 34);
				}
				

			}
		}

	}

	private void loadImages() {

	}
}
