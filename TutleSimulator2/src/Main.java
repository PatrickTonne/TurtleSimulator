
import com.sun.media.jfxmedia.events.NewFrameEvent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) {
		//Erstes Border Pane Implementierung

		BorderPane borderpane1 = addMenu();	
				
				
		BorderPane borderpane2 = new BorderPane();

		
		borderpane2.setTop(addToolBar());
		borderpane2.setCenter(addSplitPane());
		borderpane2.setBottom(addLabel());
		borderpane1.setCenter(borderpane2);
		
		primaryStage.setScene(new Scene(borderpane1, 1080, 720));

		primaryStage.show();
		
	}
	
	
	public BorderPane addMenu() {

		BorderPane borderpane = new BorderPane();
		MenuBar menuBar = new MenuBar();
		
		Menu editorMenu = new Menu("_Editor");
		MenuItem neuItem = new MenuItem("_Neu");
		neuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+N"));
		Image neuIcon = new Image(getClass().getResourceAsStream("media/New16.gif"));
		ImageView neuView = new ImageView(neuIcon);
		neuItem.setGraphic(neuView);
		
		MenuItem öffnenItem = new MenuItem("_Öffnen");
		öffnenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+O"));
		Image öffnenIcon = new Image(getClass().getResourceAsStream("media/Open16.gif"));
		ImageView öffnenView = new ImageView(öffnenIcon);
		öffnenItem.setGraphic(öffnenView);
		
		MenuItem kompilierenItem = new MenuItem("_Kompilieren");
		kompilierenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+K"));
		
		MenuItem druckenItem = new MenuItem("_Drucken");
		druckenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+P"));
		Image druckenIcon = new Image(getClass().getResourceAsStream("media/Print16.gif"));
		ImageView druckenView = new ImageView(druckenIcon);
		druckenItem.setGraphic(druckenView);
		
		MenuItem beendenItem = new MenuItem("_Beenden");
		beendenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+Q"));
		editorMenu.getItems().addAll(neuItem, öffnenItem, new SeparatorMenuItem(),kompilierenItem, druckenItem, new SeparatorMenuItem(),beendenItem);
		
		//Territorium Menu
		Menu terriMenu = new Menu("_Territorium");
		MenuItem SpeichernItem = new MenuItem("_Speichern");
		MenuItem LadenItem = new MenuItem("_Laden");
		MenuItem bildSpeichernItem = new MenuItem("_Als Bild speichern");
		MenuItem druckenItem2 = new MenuItem("_Drucken");
		MenuItem gößeändernItem = new MenuItem("_Größe ändern...");
		
		ToggleGroup terriToggleGroup = new ToggleGroup();
		
		RadioMenuItem hamsterPlazierenMenuItem = new RadioMenuItem("_Schildkröte plazieren");
		hamsterPlazierenMenuItem.setToggleGroup(terriToggleGroup);
		RadioMenuItem kornPlazierenMenuItem = new RadioMenuItem("_Salat plazieren");
		kornPlazierenMenuItem.setToggleGroup(terriToggleGroup);
		RadioMenuItem MauerPlazierenMenuItem = new RadioMenuItem("_Mauer plazieren");
		MauerPlazierenMenuItem.setToggleGroup(terriToggleGroup);
		RadioMenuItem kachelLöschenMenuItem = new RadioMenuItem("_Kachel löschen");
		kachelLöschenMenuItem.setToggleGroup(terriToggleGroup);
		
		terriMenu.getItems().addAll(SpeichernItem, LadenItem, bildSpeichernItem, druckenItem2, gößeändernItem, new SeparatorMenuItem(), hamsterPlazierenMenuItem,
		kornPlazierenMenuItem, MauerPlazierenMenuItem, kachelLöschenMenuItem);
		
		
		Menu schildkröteMenu = new Menu("_Hamster");
		
		MenuItem salatImMundItem = new MenuItem("Körner im Maul");
		MenuItem linksUmItem = new MenuItem("linksUm");
		linksUmItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+L"));
		MenuItem vorItem = new MenuItem("vor");
		vorItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+V"));
		MenuItem nimmItem= new MenuItem("nimm");
		nimmItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+N"));
		MenuItem gibItem = new MenuItem("gib");
		gibItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+G"));
		
		schildkröteMenu.getItems().addAll(salatImMundItem, linksUmItem, vorItem, nimmItem, gibItem);
	
		Menu SimulationMenu = new Menu("_Simulation");
		
		MenuItem startItem = new MenuItem("_Start/Fortsetzen");
		startItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+F11"));
		Image startIcon = new Image(getClass().getResourceAsStream("media/Play16.gif"));
		ImageView startView = new ImageView(startIcon);
		startItem.setGraphic(startView);
	
		MenuItem pauseItem = new MenuItem("_Pause");
		Image pauseIcon = new Image(getClass().getResourceAsStream("media/Pause16.gif"));
		ImageView pauseView = new ImageView(pauseIcon);
		pauseItem.setGraphic(pauseView);
		
		MenuItem stopItem = new  MenuItem("_Stopp");
		stopItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+F12"));
		Image stopIcon = new Image(getClass().getResourceAsStream("media/Stop16.gif"));
		ImageView stopView = new ImageView(stopIcon);
		stopItem.setGraphic(stopView);
		
		SimulationMenu.getItems().addAll(startItem, pauseItem, stopItem);
		
		
		
		menuBar.getMenus().addAll(editorMenu, terriMenu, schildkröteMenu,SimulationMenu);
		borderpane.setTop(menuBar);
		return borderpane;
		

	}
		

	public ToolBar addToolBar() {
		ToolBar toolbar = new ToolBar();
		Button neuButton = new Button();
		Image neuIcon = new Image(getClass().getResourceAsStream("media/New24.gif"));
		ImageView neuView = new ImageView(neuIcon);
		neuButton.setGraphic(neuView);
		neuButton.setTooltip(new Tooltip("Neue Simulation starten."));
		
		Button öffnenButton = new Button();
		Image öffnenIcon = new Image(getClass().getResourceAsStream("media/Open24.gif"));
		ImageView öffnenView = new ImageView(öffnenIcon);
		öffnenButton.setGraphic(öffnenView);
		öffnenButton.setTooltip(new Tooltip("Projekt öffnen."));
		
		Button sichernButton = new Button();
		Image sichernIcon = new Image(getClass().getResourceAsStream("media/Save24.gif"));
		ImageView sichernView = new ImageView(sichernIcon);
		sichernButton.setGraphic(sichernView);
		sichernButton.setTooltip(new Tooltip("Sichern."));
		
		Button compileButton = new Button();
		Image compileIcon = new Image(getClass().getResourceAsStream("media/Compile24.gif"));
		ImageView compileView = new ImageView(compileIcon);
		compileButton.setGraphic(compileView);
		compileButton.setTooltip(new Tooltip("Kompilieren."));
		
		Button terrainButton = new Button();
		Image terrainIcon = new Image(getClass().getResourceAsStream("media/Terrain24.gif"));
		ImageView terrainView = new ImageView(terrainIcon);
		terrainButton.setGraphic(terrainView);
		terrainButton.setTooltip(new Tooltip("Terrain ändern."));
		
		Button hamsterButton = new Button();
		Image hamsterIcon = new Image(getClass().getResourceAsStream("media/Hamster24.png"));
		ImageView hamsterView = new ImageView(hamsterIcon);
		hamsterButton.setGraphic(hamsterView);
		hamsterButton.setTooltip(new Tooltip("Hamster bewegen."));
		
		Button kornButton = new Button();
		Image kornIcon = new Image(getClass().getResourceAsStream("media/Corn24.gif"));
		ImageView kornView = new ImageView(kornIcon);
		kornButton.setGraphic(kornView);
		kornButton.setTooltip(new Tooltip("Korn plazieren."));
		
		Button mauerButton = new Button();
		Image mauerIcon = new Image(getClass().getResourceAsStream("media/Wall24.gif"));
		ImageView mauerView = new ImageView(mauerIcon);
		mauerButton.setGraphic(mauerView);
		mauerButton.setTooltip(new Tooltip("Mauer plazieren."));
		
		Button deleteButton = new Button();
		Image deleteIcon = new Image(getClass().getResourceAsStream("media/Delete24.gif"));
		ImageView deleteView = new ImageView(deleteIcon);
		deleteButton.setGraphic(deleteView);
		deleteButton.setTooltip(new Tooltip("Löschen."));
		
		Button hamsterCornButton = new Button();
		Image hamsterCornIcon = new Image(getClass().getResourceAsStream("media/HamsterCorn24.png"));
		ImageView hamsterCornView = new ImageView(hamsterCornIcon);
		hamsterCornButton.setGraphic(hamsterCornView);
		hamsterCornButton.setTooltip(new Tooltip("Hamster mit Korn"));
		
		Button hamsterLeftButton = new Button();
		Image hamsterLeftIcon = new Image(getClass().getResourceAsStream("media/HamsterLeft24.png"));
		ImageView hamsterLeftView = new ImageView(hamsterLeftIcon);
		hamsterLeftButton.setGraphic(hamsterLeftView);
		hamsterLeftButton.setTooltip(new Tooltip("Hamster naczh links drehen."));
		
		Button hamsterMoveButton = new Button();
		Image hamsterMoveIcon = new Image(getClass().getResourceAsStream("media/HamsterMove24.png"));
		ImageView hamsterMoveView = new ImageView(hamsterMoveIcon);
		hamsterMoveButton.setGraphic(hamsterMoveView);
		hamsterMoveButton.setTooltip(new Tooltip("Hamster bewegen"));
		
		Button hamsterPickButton = new Button();
		Image hamsterPickIcon = new Image(getClass().getResourceAsStream("media/HamsterPick24.png"));
		ImageView hamsterPickView = new ImageView(hamsterPickIcon);
		hamsterPickButton.setGraphic(hamsterPickView);
		hamsterPickButton.setTooltip(new Tooltip("Korn aufnehmen."));
		
		Button hamsterPutButton = new Button();
		Image hamsterPutIcon = new Image(getClass().getResourceAsStream("media/HamsterPut24.png"));
		ImageView hamsterPutView = new ImageView(hamsterPutIcon);
		hamsterPutButton.setGraphic(hamsterPutView);
		hamsterPutButton.setTooltip(new Tooltip("Korn abgeben."));
		
		Button playButton = new Button();
		Image playIcon = new Image(getClass().getResourceAsStream("media/Play24.gif"));
		ImageView playView = new ImageView(playIcon);
		playButton.setGraphic(playView);
		playButton.setTooltip(new Tooltip("Starten."));
		
		Button pauseButton = new Button();
		Image pauseIcon = new Image(getClass().getResourceAsStream("media/Pause24.gif"));
		ImageView pauseView = new ImageView(pauseIcon);
		pauseButton.setGraphic(pauseView);
		pauseButton.setTooltip(new Tooltip("Pause."));
		
		Button stopButton = new Button();
		Image stopIcon = new Image(getClass().getResourceAsStream("media/Stop24.gif"));
		ImageView stopView = new ImageView(stopIcon);
		stopButton.setGraphic(stopView);
		stopButton.setTooltip(new Tooltip("Stopp."));
		
		Slider tickRateSlider = new Slider(0,50,100);
		tickRateSlider.setValue(0);
		tickRateSlider.setTooltip(new Tooltip("Geschwindigkeit einstellen."));
		
		
		toolbar = new ToolBar(neuButton, öffnenButton, new Separator(), sichernButton, compileButton, new Separator(),terrainButton, hamsterButton, kornButton, mauerButton, deleteButton, new Separator(), hamsterCornButton,
				hamsterLeftButton, hamsterMoveButton, hamsterPickButton, hamsterPutButton, new Separator(), playButton, pauseButton, stopButton, new Separator(), tickRateSlider);

		
		
		
		
		return toolbar;
	}

	public SplitPane addSplitPane() {

		
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().add(new TextArea());
		splitPane.getItems().add(new StackPane(new Label("Test")));
		
		return splitPane;
	}

	public Label addLabel() {
		Label statusLabel = new Label("Statusmeldung");
		
		return statusLabel;
	}
}

