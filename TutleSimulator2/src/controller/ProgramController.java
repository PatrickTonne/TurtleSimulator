package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Program;
import view.*;

public class ProgramController extends Application{
	
	public static final String DIRECTORY = "src" + File.separator +"programs";
	public static final String DEFAULTPROGRAMM = "void main() {"+ System.lineSeparator() + System.lineSeparator() + "}";
	GUI Simulator;
	Stage stage;
	private static Program program;
	
	static ArrayList<Program> programList = new ArrayList<Program>();
	
	public static void main(String[] args) {

		launch(args);
	}
	
	public static void add(Program program) {
		ProgramController.programList.add(program);
	}
	public static void remove(Program program) {
		ProgramController.programList.remove(program);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Simulator = new GUI();
		primaryStage.setTitle("defaultTurtle");
		program = new Program();
		add(program);
		primaryStage.setOnCloseRequest(event -> {
		   System.out.println("PrimaryStage is closing");
		   remove(program);
		});
		
		Simulator.start(primaryStage);
		
	}
	
	static public void newProgram(String name) throws IOException {
		GUI newGui = new GUI();
		Stage stage = new Stage();
		
		program = new Program(name);
		add(program);
		stage.setTitle(name);
		
		stage.setOnCloseRequest(event -> {
		    System.out.println("Stage is closing");
		    remove(program);
		});
		newGui.start(stage);
	}
	public static void quitProgram(Program program, String code) {
		program.save(code);
		remove(program);
		if(ProgramController.programList.isEmpty()) {
			Platform.exit();
		}
		
	}

	static public void newInputWindow() {
		
		TextInputDialog dialog = new TextInputDialog("newTurtle");
		 
		dialog.setTitle("Eingabe");
		dialog.setHeaderText("Namen deines Programms eingeben:");
		dialog.setContentText("Name:");
		 
		Optional<String> result = dialog.showAndWait();
		 
		result.ifPresent(name -> {
		    System.out.println("input" + name);
		    try {
				newProgram(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		});
	}
}
