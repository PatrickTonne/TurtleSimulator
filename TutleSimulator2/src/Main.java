import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) {
		//Erstes Border Pane Implementierung
				BorderPane borderpane1 = new BorderPane();
				
				
				//Menu Bar Implementierung
				MenuBar menuBar = new MenuBar();
				Menu editorMenu = new Menu("Editor");
				Menu terriMenu = new Menu("Territorium");
				Menu hamsterMenu = new Menu("Hamster");
				Menu SimulationMenu = new Menu("Simulation");
				menuBar.getMenus().addAll(editorMenu, terriMenu, hamsterMenu,SimulationMenu);
				borderpane1.setTop(menuBar);
				
				//Zweites Border Pane Implementierung
				
				BorderPane borderpane2 = new BorderPane();
				
				VBox toolBox = new VBox();
				Button button1 = new Button();
				button1.setPrefSize(200, 200);
				button1.setMaxWidth(1000);
				toolBox.getChildren().addAll(button1);
				
				borderpane2.setTop(toolBox);
				borderpane1.setBottom(borderpane2);
				
				
				
				
				
				primaryStage.setScene(new Scene(borderpane1, 600, 600));

				primaryStage.show();
		
	}
		

}
