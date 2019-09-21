package view;

import java.util.Optional;
import controller.ChoosenItem;
import controller.ProgramController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Program;
import model.Territorium;
public class GUI{

	
	ChoosenItem choosenItem1 = new ChoosenItem();
	
	private Button terrainButton;
	private ToggleButton turtleButton;
	private ToggleButton salatButton;
	private ToggleButton mauerButton;
	private ToggleButton deleteButton;
	
	private RadioMenuItem turtlePlazierenMenuItem;
	private RadioMenuItem salatPlazierenMenuItem;
	private RadioMenuItem MauerPlazierenMenuItem;
	private RadioMenuItem kachelLöschenMenuItem;
	
	private Editor codeEditor;
	
	Territorium terri1;
	Stage stage;
	Program program;
	
	
	public void start(Stage primaryStage, Program prog) {
		
		//BorderPane in BorderPane wird erstellt. Menü wird eingefügt.

		BorderPane borderpane1 = addMenu();	
		program = prog;
				
		BorderPane borderpane2 = new BorderPane();
		stage = primaryStage;

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
		neuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ProgramController.newInputWindow();
				
			}
		});
		
		MenuItem öffnenItem = new MenuItem("_Öffnen");
		öffnenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+O"));
		Image öffnenIcon = new Image(getClass().getResourceAsStream("media/Open16.gif"));
		ImageView öffnenView = new ImageView(öffnenIcon);
		öffnenItem.setGraphic(öffnenView);
		öffnenItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ProgramController.openProgram(stage);
				
			}
		});
		
		MenuItem kompilierenItem = new MenuItem("_Kompilieren");
		kompilierenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+K"));
		
		MenuItem druckenItem = new MenuItem("_Drucken");
		druckenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+P"));
		Image druckenIcon = new Image(getClass().getResourceAsStream("media/Print16.gif"));
		ImageView druckenView = new ImageView(druckenIcon);
		druckenItem.setGraphic(druckenView);
		
		MenuItem beendenItem = new MenuItem("_Beenden");
		beendenItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+Q"));
		
		beendenItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ProgramController.quitProgram(program, codeEditor.getText());
				stage.close();
				
			}
		});
		editorMenu.getItems().addAll(neuItem, öffnenItem, new SeparatorMenuItem(),kompilierenItem, druckenItem, new SeparatorMenuItem(),beendenItem);
		
		//Territorium Menu
		Menu terriMenu = new Menu("_Territorium");
		MenuItem SpeichernItem = new MenuItem("_Speichern");
		MenuItem LadenItem = new MenuItem("_Laden");
		MenuItem bildSpeichernItem = new MenuItem("_Als Bild speichern");
		MenuItem druckenItem2 = new MenuItem("_Drucken");
		MenuItem größeändernItem = new MenuItem("_Größe ändern...");
		
		größeändernItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				changeSize();
				
			}
		});
		
		ToggleGroup terriToggleGroup = new ToggleGroup();
		
		turtlePlazierenMenuItem = new RadioMenuItem("_Schildkröte plazieren");
		turtlePlazierenMenuItem.setToggleGroup(terriToggleGroup);
		turtlePlazierenMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				choosenItem1.choosenItem = choosenItem1.turtleChoosen;
				turtleButton.setSelected(true);
				
			}
		});
		
		salatPlazierenMenuItem = new RadioMenuItem("_Salat plazieren");
		salatPlazierenMenuItem.setToggleGroup(terriToggleGroup);
		salatPlazierenMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				choosenItem1.choosenItem = choosenItem1.salatChoosen;
				salatButton.setSelected(true);
				
			}
		});
		MauerPlazierenMenuItem = new RadioMenuItem("_Mauer plazieren");
		MauerPlazierenMenuItem.setToggleGroup(terriToggleGroup);
		MauerPlazierenMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				choosenItem1.choosenItem = choosenItem1.wallChoosen;
				mauerButton.setSelected(true);
				
			}
		});
		kachelLöschenMenuItem = new RadioMenuItem("_Kachel löschen");
		kachelLöschenMenuItem.setToggleGroup(terriToggleGroup);
		kachelLöschenMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				choosenItem1.choosenItem = choosenItem1.deleteChoosen ;
				deleteButton.setSelected(true);
				
			}
		});
		
		terriMenu.getItems().addAll(SpeichernItem, LadenItem, bildSpeichernItem, druckenItem2, größeändernItem, new SeparatorMenuItem(), turtlePlazierenMenuItem,
		salatPlazierenMenuItem, MauerPlazierenMenuItem, kachelLöschenMenuItem);
		
		
		Menu schildkröteMenu = new Menu("_Schildkröte");
		
		MenuItem salatImMundItem = new MenuItem("Salat im Maul");
		MenuItem rechtsUmItem = new MenuItem("rechtsUm");
		rechtsUmItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				terri1.turtleRight();
				
			}
		});
		rechtsUmItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+L"));
		MenuItem vorItem = new MenuItem("vor");
		
		vorItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				terri1.moveTurtle();
				
			}
		});
		vorItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+V"));
		MenuItem nimmItem= new MenuItem("nimm");
		
		nimmItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				terri1.turtleTake();
				
			}
		});
		nimmItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+N"));
		MenuItem gibItem = new MenuItem("gib");
		
		gibItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				terri1.turtleDrop();
				
			}
		});
		gibItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+G"));
		
		schildkröteMenu.getItems().addAll(salatImMundItem, rechtsUmItem, vorItem, nimmItem, gibItem);
	
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
		neuButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ProgramController.newInputWindow();
				
			}
		});
		
		Button öffnenButton = new Button();
		Image öffnenIcon = new Image(getClass().getResourceAsStream("media/Open24.gif"));
		ImageView öffnenView = new ImageView(öffnenIcon);
		öffnenButton.setGraphic(öffnenView);
		öffnenButton.setTooltip(new Tooltip("Projekt öffnen."));
		öffnenButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ProgramController.openProgram(stage);
				
			}
		});
		
		Button sichernButton = new Button();
		Image sichernIcon = new Image(getClass().getResourceAsStream("media/Save24.gif"));
		ImageView sichernView = new ImageView(sichernIcon);
		sichernButton.setGraphic(sichernView);
		sichernButton.setTooltip(new Tooltip("Sichern."));
		sichernButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				program.save("test");

			}
		});
		
		Button compileButton = new Button();
		Image compileIcon = new Image(getClass().getResourceAsStream("media/Compile24.gif"));
		ImageView compileView = new ImageView(compileIcon);
		compileButton.setGraphic(compileView);
		compileButton.setTooltip(new Tooltip("Kompilieren."));
		//___________
		ToggleGroup terriToggleGroup = new ToggleGroup();
		
		terrainButton = new Button();
		Image terrainIcon = new Image(getClass().getResourceAsStream("media/Terrain24.gif"));
		ImageView terrainView = new ImageView(terrainIcon);
		terrainButton.setGraphic(terrainView);
		terrainButton.setTooltip(new Tooltip("Terrain ändern."));
		
		terrainButton.setOnAction(new EventHandler<ActionEvent>() {

			//Grundlage von für neues Fenster: https://stackoverflow.com/questions/31556373/javafx-dialog-with-2-input-fields
			@Override
			public void handle(ActionEvent event) {

				changeSize();
			}
		});
		
		
		turtleButton = new ToggleButton();
		Image turtleIcon = new Image(getClass().getResourceAsStream("media/Turtle24.png"));
		ImageView turtleView = new ImageView(turtleIcon);
		turtleButton.setGraphic(turtleView);
		turtleButton.setTooltip(new Tooltip("Schildkröte bewegen."));
		turtleButton.setToggleGroup(terriToggleGroup);
		
		turtleButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				choosenItem1.choosenItem = choosenItem1.turtleChoosen;
				turtlePlazierenMenuItem.setSelected(true);
			}
		});
		
		salatButton = new ToggleButton();
		Image salatIcon = new Image(getClass().getResourceAsStream("media/Salat24.gif"));
		ImageView salatView = new ImageView(salatIcon);
		salatButton.setGraphic(salatView);
		salatButton.setTooltip(new Tooltip("Salatkopf plazieren."));
		salatButton.setToggleGroup(terriToggleGroup);
		
		salatButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				choosenItem1.choosenItem = choosenItem1.salatChoosen;
				salatPlazierenMenuItem.setSelected(true);
			}
	
		});
		
		mauerButton = new ToggleButton();
		Image mauerIcon = new Image(getClass().getResourceAsStream("media/Wall24.gif"));
		ImageView mauerView = new ImageView(mauerIcon);
		mauerButton.setGraphic(mauerView);
		mauerButton.setTooltip(new Tooltip("Mauer plazieren."));
		mauerButton.setToggleGroup(terriToggleGroup);
		
		mauerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				choosenItem1.choosenItem = choosenItem1.wallChoosen;
				MauerPlazierenMenuItem.setSelected(true);
			}
	
		});
		
		deleteButton = new ToggleButton();
		Image deleteIcon = new Image(getClass().getResourceAsStream("media/Delete24.gif"));
		ImageView deleteView = new ImageView(deleteIcon);
		deleteButton.setGraphic(deleteView);
		deleteButton.setTooltip(new Tooltip("Löschen."));
		deleteButton.setToggleGroup(terriToggleGroup);
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				choosenItem1.choosenItem = choosenItem1.deleteChoosen ;
				kachelLöschenMenuItem.setSelected(true);
				
			}
		});
		//_____
		
		Button turtleSalatButton = new Button();
		Image turtleSalatIcon = new Image(getClass().getResourceAsStream("media/TurtleSalat24.png"));
		ImageView turtleSalatView = new ImageView(turtleSalatIcon);
		turtleSalatButton.setGraphic(turtleSalatView);
		turtleSalatButton.setTooltip(new Tooltip("Schildkröte mit Salat"));
		
		Button turtleLeftButton = new Button();
		Image turtleLeftIcon = new Image(getClass().getResourceAsStream("media/TurtleLeft24.png"));
		ImageView turtleLeftView = new ImageView(turtleLeftIcon);
		turtleLeftButton.setGraphic(turtleLeftView);
		turtleLeftButton.setTooltip(new Tooltip("Schildkröte nach rechts drehen."));
		
		turtleLeftButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				terri1.turtleRight();
				
			}
		});
		
		Button turtleMoveButton = new Button();
		Image turtleMoveIcon = new Image(getClass().getResourceAsStream("media/TurtleMove24.png"));
		ImageView turtleMoveView = new ImageView(turtleMoveIcon);
		turtleMoveButton.setGraphic(turtleMoveView);
		turtleMoveButton.setTooltip(new Tooltip("Schildkröte bewegen"));
		turtleMoveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				terri1.moveTurtle();
				
			}
		});
		
		Button turtlePickButton = new Button();
		Image turtlePickIcon = new Image(getClass().getResourceAsStream("media/TurtlePick24.png"));
		ImageView turtlePickView = new ImageView(turtlePickIcon);
		turtlePickButton.setGraphic(turtlePickView);
		turtlePickButton.setTooltip(new Tooltip("Salat aufnehmen."));
		turtlePickButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				terri1.turtleTake();
				
			}
		});
		
		Button turtlePutButton = new Button();
		Image turtlePutIcon = new Image(getClass().getResourceAsStream("media/TurtlePut24.png"));
		ImageView turtlePutView = new ImageView(turtlePutIcon);
		turtlePutButton.setGraphic(turtlePutView);
		turtlePutButton.setTooltip(new Tooltip("Salat abgeben."));
		
		turtlePutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				terri1.turtleDrop();
				
			}
		});
		
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

		terri1 = new Territorium();
		
		SplitPane splitPane = new SplitPane();
		codeEditor = new Editor(program);
		ScrollPane sp1 = new ScrollPane();
		TerriPanel tp1 = new TerriPanel(terri1, sp1, choosenItem1);
		
		sp1.setContent(tp1.getCanvas());
		splitPane.getItems().addAll(codeEditor,sp1);
		
		return splitPane;
	}

	//Klasse zur Erstellung des Labels.
	public Label addLabel() {
		Label statusLabel = new Label("Statusmeldung");
		
		return statusLabel;
	}
	
	public void changeSize() {
		
		// Create the custom dialog.
	    Dialog<Pair<String, String>> dialog = new Dialog<>();
	    dialog.setTitle("Spielfeldgröße ändern");

	    // Set the button types.
	    ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

	            GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(20, 150, 10, 10));

	    TextField height = new TextField();
	    height.setPromptText("5-50");
	    TextField width = new TextField();
	    width.setPromptText("5-50");

	    gridPane.add(new Label("Höhe"), 0, 0);
	    gridPane.add(height, 1, 0);
	    gridPane.add(new Label("Breite:"), 2, 0);
	    gridPane.add(width, 3, 0);

	    dialog.getDialogPane().setContent(gridPane);


	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == loginButtonType) {
	        	int heightInt = Integer.parseInt(height.getText());
	        	int widthInt = Integer.parseInt(width.getText());
	        	
	        	if(heightInt > 4 && heightInt <51 && widthInt >4 && widthInt <51) {
	            System.out.println("Höhe:" +  heightInt);
	            System.out.println("Breite:" + widthInt);
	            
	            System.out.println("Ergebnis:" + widthInt);
	            //Resize muss noch überabreitet werden.
	            
	            terri1.reSize(widthInt, heightInt);
	        	}
	        	else {
	        		Alert alert = new Alert(AlertType.ERROR);
	        		alert.setTitle("Fehler");
	        		alert.setHeaderText("Es sind nur Größen von 5-50 möglich!");

	        		alert.showAndWait();
	        	}
	        }
	        return null;
	    });

	    Optional<Pair<String, String>> result = dialog.showAndWait();


	}
	public String getCode() {
		return codeEditor.getText();
	}
}
	

