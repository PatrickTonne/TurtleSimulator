import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class TerriPanel extends Region {

	Canvas canvas1;
	Territorium terri1;
	private Image wallImage;
	private Image turtleNORTHImage;
	private Image turtleEASTImage;
	private Image turtleSOUTHImage;
	private Image turtleWESTImage;
	
	private Image salatImage;

	public TerriPanel(Territorium ter) {
		terri1 = ter;
		loadImages();
		int xSize = ter.XSize * 34 +2;
		int ySize = ter.YSize * 34 +2;

		canvas1 = new Canvas(xSize, ySize);
		GraphicsContext gc = canvas1.getGraphicsContext2D();
		terri1.setWall(2, 2);
		terri1.setTurtlePos(6, 6);
		terri1.setSalat(7, 7);
		terri1.setSalat(7, 7);
		terri1.setSalat(7, 7);
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
					gc1.drawImage(turtleWESTImage, 2 + (x * 34), 2 + (y * 34), 34, 34);
					
				}
				if(terri1.playGround[x][y] >0) {
					gc1.drawImage(salatImage, 2+ (x*34), 2 + (y*34), 34, 34);
				}
				

			}
		}

	}

	private void loadImages() {
		
		this.wallImage = new Image(getClass().getResource("media/Wall32.png").toString());
		
		this.turtleNORTHImage = new Image(getClass().getResource("media/turtleNORTH.png").toString());
		this.turtleEASTImage = new Image(getClass().getResource("media/turtleEAST.png").toString());
		this.turtleSOUTHImage = new Image(getClass().getResource("media/turtleWEST.png").toString());
		this.turtleWESTImage = new Image(getClass().getResource("media/turtleWEST.png").toString());
		this.salatImage = new Image(getClass().getResource("media/Salat24.gif").toString());

	}
}
