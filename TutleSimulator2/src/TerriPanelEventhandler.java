import javafx.scene.input.MouseEvent;

public class TerriPanelEventhandler {
	
	private Territorium terri1;
	
	TerriPanelEventhandler(Territorium ter, TerriPanel tp){
		
		terri1 = ter;
	
		
	}

	public void setOnMousePressed(MouseEvent pressEvent) {
		int xTile = getNearTile(pressEvent.getX());
		int yTile = getNearTile(pressEvent.getY());
		
		System.out.println(xTile + "" + yTile);
		
	}
	
	public int getNearTile( double value) {
		
		int output = ((int) (value/34));
		return output;
		
	}
}

