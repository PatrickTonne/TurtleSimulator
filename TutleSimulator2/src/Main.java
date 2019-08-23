
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) {
		//Erstes Border Pane Implementierung
				BorderPane borderpane1 = new BorderPane();
				MenuBar menuBar = new MenuBar();
				
				Menu editorMenu = new Menu("Editor");
				MenuItem neuItem = new MenuItem("Neu");
				MenuItem öffnenItem = new MenuItem("Öffnen");
				MenuItem kompilierenItem = new MenuItem("Kompilieren");
				MenuItem druckenItem = new MenuItem("Drucken");
				MenuItem beendenItem = new MenuItem("Beenden");
				editorMenu.getItems().addAll(neuItem, öffnenItem, new SeparatorMenuItem(),kompilierenItem, druckenItem, new SeparatorMenuItem(),beendenItem);
				
				//Territorium Menu
				Menu terriMenu = new Menu("Territorium");
				MenuItem SpeichernItem = new MenuItem("Speichern");
				MenuItem LadenItem = new MenuItem("Laden");
				MenuItem bildSpeichernItem = new MenuItem("Als Bild speichern");
				MenuItem druckenItem2 = new MenuItem("Drucken");
				MenuItem gößeändernItem = new MenuItem("Größe ändern...");
				
				ToggleGroup terriToggleGroup = new ToggleGroup();
				
				RadioMenuItem schildkrötePlazierenMenuItem = new RadioMenuItem("Schildkröte plazieren");
				schildkrötePlazierenMenuItem.setToggleGroup(terriToggleGroup);
				RadioMenuItem salatPlazierenMenuItem = new RadioMenuItem("Salat plazieren");
				salatPlazierenMenuItem.setToggleGroup(terriToggleGroup);
				RadioMenuItem MauerPlazierenMenuItem = new RadioMenuItem("Mauer plazieren");
				MauerPlazierenMenuItem.setToggleGroup(terriToggleGroup);
				RadioMenuItem kachelLöschenMenuItem = new RadioMenuItem("Kachel löschen");
				kachelLöschenMenuItem.setToggleGroup(terriToggleGroup);
				
				terriMenu.getItems().addAll(SpeichernItem, LadenItem, bildSpeichernItem, druckenItem2, gößeändernItem, new SeparatorMenuItem(), schildkrötePlazierenMenuItem,
				salatPlazierenMenuItem, MauerPlazierenMenuItem, kachelLöschenMenuItem);
				
				
				Menu hamsterMenu = new Menu("Hamster");
				
				
				Menu SimulationMenu = new Menu("Simulation");
				menuBar.getMenus().addAll(editorMenu, terriMenu, hamsterMenu,SimulationMenu);
				borderpane1.setTop(menuBar);
				
				//Menu Bar Implementierung
				
				
				//Zweites Border Pane Implementierung
				
				BorderPane borderpane2 = new BorderPane();
				
				VBox toolBox = new VBox();
				Button button1 = new Button();
				button1.setPrefSize(200, 200);
				button1.setMaxWidth(10000);
				toolBox.getChildren().addAll(button1);
				
				borderpane2.setTop(toolBox);
				borderpane1.setBottom(borderpane2);
				
				
				
				
				primaryStage.setScene(new Scene(borderpane1, 600, 600));

				primaryStage.show();
		
	}
	
	
	public void addMenu() {

	}
		

}
