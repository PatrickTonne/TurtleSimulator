package controller;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

public class ProgramController extends Application{
	
	GUI Simulator;
	Stage stage;
	
	static ArrayList<Stage> stageList = new ArrayList<Stage>();

	public static void main(String[] args) {
		
		
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Simulator = new GUI();
		primaryStage.setTitle("Hauptschildkröte");
		
		stageList.add(primaryStage);
		primaryStage.setOnCloseRequest(event -> {
		   System.out.println("PrimaryStage is closing");
		   stageList.remove(primaryStage);
		});
		
		Simulator.start(primaryStage);
		
	}
	
	static public void newProgram() {
		GUI newGui = new GUI();
		Stage stage = new Stage();
		stageList.add(stage);
		stage.setTitle("Schildkröte" + stageList.size());
		newGui.start(stage);
		stage.setOnCloseRequest(event -> {
		    System.out.println("Stage is closing");
		    stageList.remove(stage);
		});
		
	}

}
