
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
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

public class TurleSimulator extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) {
		
		//BorderPane in BorderPane wird erstellt. Menü wird eingefügt.

		BorderPane borderpane1 = addMenu();	
				
		BorderPane borderpane2 = new BorderPane();

		//Toolbar, SplitPane und Label wird eingefügt.
		borderpane2.setTop(addToolBar());
		borderpane2.setCenter(addSplitPane());
		borderpane2.setBottom(addLabel());
		borderpane1.setCenter(borderpane2);
		
		primaryStage.setScene(new Scene(borderpane1, 1080, 720));

		primaryStage.show();
		
	}
	
	//Klasse zur Erstellung des Menüs.
	
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
		
		RadioMenuItem turtlePlazierenMenuItem = new RadioMenuItem("_Schildkröte plazieren");
		turtlePlazierenMenuItem.setToggleGroup(terriToggleGroup);
		RadioMenuItem salatPlazierenMenuItem = new RadioMenuItem("_Salat plazieren");
		salatPlazierenMenuItem.setToggleGroup(terriToggleGroup);
		RadioMenuItem MauerPlazierenMenuItem = new RadioMenuItem("_Mauer plazieren");
		MauerPlazierenMenuItem.setToggleGroup(terriToggleGroup);
		RadioMenuItem kachelLöschenMenuItem = new RadioMenuItem("_Kachel löschen");
		kachelLöschenMenuItem.setToggleGroup(terriToggleGroup);
		
		terriMenu.getItems().addAll(SpeichernItem, LadenItem, bildSpeichernItem, druckenItem2, gößeändernItem, new SeparatorMenuItem(), turtlePlazierenMenuItem,
		salatPlazierenMenuItem, MauerPlazierenMenuItem, kachelLöschenMenuItem);
		
		
		Menu schildkröteMenu = new Menu("_Schildkröte");
		
		MenuItem salatImMundItem = new MenuItem("Salat im Maul");
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
		//Klasse zum Erstellen der Toolbar.

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
		
		Button turtleButton = new Button();
		Image turtleIcon = new Image(getClass().getResourceAsStream("media/Turtle24.png"));
		ImageView turtleView = new ImageView(turtleIcon);
		turtleButton.setGraphic(turtleView);
		turtleButton.setTooltip(new Tooltip("Schildkröte bewegen."));
		
		Button salatButton = new Button();
		Image salatIcon = new Image(getClass().getResourceAsStream("media/Salat24.gif"));
		ImageView salatView = new ImageView(salatIcon);
		salatButton.setGraphic(salatView);
		salatButton.setTooltip(new Tooltip("Salatkopf plazieren."));
		
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
		
		Button turtleSalatButton = new Button();
		Image turtleSalatIcon = new Image(getClass().getResourceAsStream("media/TurtleSalat24.png"));
		ImageView turtleSalatView = new ImageView(turtleSalatIcon);
		turtleSalatButton.setGraphic(turtleSalatView);
		turtleSalatButton.setTooltip(new Tooltip("Schildkröte mit Salat"));
		
		Button turtleLeftButton = new Button();
		Image turtleLeftIcon = new Image(getClass().getResourceAsStream("media/TurtleLeft24.png"));
		ImageView turtleLeftView = new ImageView(turtleLeftIcon);
		turtleLeftButton.setGraphic(turtleLeftView);
		turtleLeftButton.setTooltip(new Tooltip("Schildkröte nach links drehen."));
		
		Button turtleMoveButton = new Button();
		Image turtleMoveIcon = new Image(getClass().getResourceAsStream("media/TurtleMove24.png"));
		ImageView turtleMoveView = new ImageView(turtleMoveIcon);
		turtleMoveButton.setGraphic(turtleMoveView);
		turtleMoveButton.setTooltip(new Tooltip("Schildkröte bewegen"));
		
		Button turtlePickButton = new Button();
		Image turtlePickIcon = new Image(getClass().getResourceAsStream("media/TurtlePick24.png"));
		ImageView turtlePickView = new ImageView(turtlePickIcon);
		turtlePickButton.setGraphic(turtlePickView);
		turtlePickButton.setTooltip(new Tooltip("Salat aufnehmen."));
		
		Button turtlePutButton = new Button();
		Image turtlePutIcon = new Image(getClass().getResourceAsStream("media/TurtlePut24.png"));
		ImageView turtlePutView = new ImageView(turtlePutIcon);
		turtlePutButton.setGraphic(turtlePutView);
		turtlePutButton.setTooltip(new Tooltip("Salat abgeben."));
		
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
		
		
		toolbar = new ToolBar(neuButton, öffnenButton, new Separator(), sichernButton, compileButton, new Separator(),terrainButton, turtleButton, salatButton, mauerButton, deleteButton, new Separator(), turtleSalatButton,
				turtleLeftButton, turtleMoveButton, turtlePickButton, turtlePutButton, new Separator(), playButton, pauseButton, stopButton, new Separator(), tickRateSlider);

		
		
		
		
		return toolbar;
	}
	
	//Klasse zur Erstellung der Splitpane (Editor, Spielfeld)

	public SplitPane addSplitPane() {

		Territorium terri1 = new Territorium();
		
		SplitPane splitPane = new SplitPane();
		TextArea codeEditor = new TextArea();
		ScrollPane sp1 = new ScrollPane();
		TerriPanel tp1 = new TerriPanel(terri1, sp1);
		
		sp1.setContent(tp1.getCanvas());
		splitPane.getItems().addAll(codeEditor,sp1);
		
		return splitPane;
	}

	//Klasse zur Erstellung des Labels.
	public Label addLabel() {
		Label statusLabel = new Label("Statusmeldung");
		
		return statusLabel;
	}
}

