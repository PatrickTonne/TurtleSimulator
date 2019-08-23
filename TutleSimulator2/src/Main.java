
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
				Image neuIcon = new Image(getClass().getResourceAsStream("media/New16.gif"));
				ImageView neuView = new ImageView(neuIcon);
				neuItem.setGraphic(neuView);
				
				MenuItem �ffnenItem = new MenuItem("�ffnen");
				Image �ffnenIcon = new Image(getClass().getResourceAsStream("media/Open16.gif"));
				ImageView �ffnenView = new ImageView(�ffnenIcon);
				�ffnenItem.setGraphic(�ffnenView);
				
				MenuItem kompilierenItem = new MenuItem("Kompilieren");
				
				MenuItem druckenItem = new MenuItem("Drucken");
				Image druckenIcon = new Image(getClass().getResourceAsStream("media/Print16.gif"));
				ImageView druckenView = new ImageView(druckenIcon);
				druckenItem.setGraphic(druckenView);
				
				MenuItem beendenItem = new MenuItem("Beenden");
				editorMenu.getItems().addAll(neuItem, �ffnenItem, new SeparatorMenuItem(),kompilierenItem, druckenItem, new SeparatorMenuItem(),beendenItem);
				
				//Territorium Menu
				Menu terriMenu = new Menu("Territorium");
				MenuItem SpeichernItem = new MenuItem("Speichern");
				MenuItem LadenItem = new MenuItem("Laden");
				MenuItem bildSpeichernItem = new MenuItem("Als Bild speichern");
				MenuItem druckenItem2 = new MenuItem("Drucken");
				MenuItem g��e�ndernItem = new MenuItem("Gr��e �ndern...");
				
				ToggleGroup terriToggleGroup = new ToggleGroup();
				
				RadioMenuItem schildkr�tePlazierenMenuItem = new RadioMenuItem("Schildkr�te plazieren");
				schildkr�tePlazierenMenuItem.setToggleGroup(terriToggleGroup);
				RadioMenuItem salatPlazierenMenuItem = new RadioMenuItem("Salat plazieren");
				salatPlazierenMenuItem.setToggleGroup(terriToggleGroup);
				RadioMenuItem MauerPlazierenMenuItem = new RadioMenuItem("Mauer plazieren");
				MauerPlazierenMenuItem.setToggleGroup(terriToggleGroup);
				RadioMenuItem kachelL�schenMenuItem = new RadioMenuItem("Kachel l�schen");
				kachelL�schenMenuItem.setToggleGroup(terriToggleGroup);
				
				terriMenu.getItems().addAll(SpeichernItem, LadenItem, bildSpeichernItem, druckenItem2, g��e�ndernItem, new SeparatorMenuItem(), schildkr�tePlazierenMenuItem,
				salatPlazierenMenuItem, MauerPlazierenMenuItem, kachelL�schenMenuItem);
				
				
				Menu schildkr�teMenu = new Menu("Schuldkr�te");
				
				MenuItem salatImMundItem = new MenuItem("Salat im Mund");
				MenuItem linksUmItem = new MenuItem("linksUm");
				MenuItem vorItem = new MenuItem("vor");
				MenuItem nimmItem= new MenuItem("nimm");
				MenuItem gibItem = new MenuItem("gib");
				
				schildkr�teMenu.getItems().addAll(salatImMundItem, linksUmItem, vorItem, nimmItem, gibItem);
			
				Menu SimulationMenu = new Menu("Simulation");
				
				MenuItem startItem = new MenuItem("Start/Fortsetzen");
				MenuItem pauseItem = new MenuItem("Pause");
				MenuItem stopItem = new  MenuItem("Stopp");
				
				SimulationMenu.getItems().addAll(startItem, pauseItem, stopItem);
				
				
				
				menuBar.getMenus().addAll(editorMenu, terriMenu, schildkr�teMenu,SimulationMenu);
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
