package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Program;
import view.*;

public class ProgramController extends Application {

	public static final String SAVEFOLDER = "src" + File.separator + "programs";
	public static final String MAINPROGRAM = System.lineSeparator() + "void main() {" + System.lineSeparator()
			+ System.lineSeparator() + "}";
	GUI Simulator;
	Stage stage;
	public static Program program;

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
			quitProgram(program, Simulator.getCode());
		});

		Simulator.start(primaryStage, program);
		CompilerController.Compile(program, Simulator.getTerritorium());

	}

	static public void newProgram(String name) throws IOException {

		if (!exists(name)) {
			GUI newGui = new GUI();
			Stage stage = new Stage();
			program = new Program(name);

			add(program);
			stage.setTitle(name);

			stage.setOnCloseRequest(event -> {
				quitProgram(program, newGui.getCode());
				System.out.println("Stage is closing");
			});
			newGui.start(stage, program);

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Name vergeben");
			alert.setHeaderText("Fehler!");
			alert.setContentText("Der eingegebene Name wird bereits verwendet.");

			alert.showAndWait();
		}

	}

	public static void quitProgram(Program program, String code) {
		program.save(code);
		remove(program);
		if (ProgramController.programList.isEmpty()) {
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
				System.out.println(e);
				e.printStackTrace();
			}

		});
	}

	public static boolean exists(String name) {
		Path filePath = Paths.get(SAVEFOLDER, name + ".java");
		return Files.exists(filePath);
	}

	public static void openProgram(Stage stage) {
		// Quelle:
		// https://panjutorials.de/tutorials/javafx-8-gui/lektionen/file-chooser-in-javafx/
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Programm auswählen");
		File dirFile = new File(SAVEFOLDER);
		fileChooser.setInitialDirectory(dirFile);
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("*.java", "*.java");
		fileChooser.getExtensionFilters().add(filter);
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			if (isOpen(file.getName())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Programm bereits geöffnet!");
				alert.setHeaderText("Fehler!");
				alert.setContentText("Du hast das Programm bereits in einem anderen Fenster geöffnet.");

				alert.showAndWait();

			} else {
				// Quelle:
				// https://stackoverflow.com/questions/924394/how-to-get-the-filename-without-the-extension-in-java
				String name = file.getName().replaceFirst("[.][^.]+$", "");
				GUI newGui = new GUI();
				Stage newStage = new Stage();
				try {
					program = new Program(name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				add(program);
				newStage.setTitle(name);

				newStage.setOnCloseRequest(event -> {
					quitProgram(program, newGui.getCode());
				});
				newGui.start(newStage, program);
				
				CompilerController.Compile(program, newGui.getTerritorium());

			}
		}
	}

	public static boolean isOpen(String name) {
		for (Program program : programList) {
			if (name.equals(program.getFileName())) {
				return true;
			}
		}
		return false;

	}
}
