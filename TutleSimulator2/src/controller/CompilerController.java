package controller;

import java.io.ByteArrayOutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Program;

public class CompilerController {
	
	public static void Compile(Program program) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		ByteArrayOutputStream fail = new ByteArrayOutputStream();
		int result = compiler.run(null, null, fail, program.getFullFileName());
		System.out.println(result);
		if (result == 0) {
			System.out.println("Kein Fehler");
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Compiler");
	        alert.setHeaderText("Erfolgreich!");
	        alert.setContentText("Dein Programm wurde erfolgreich kompiliert.");
	        alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Kompilierfehler");
	        alert.setHeaderText("Da meckert der Compiler!");
	        alert.setContentText("Überprüfe dein Programm nach Fehlern");
	 
	        alert.showAndWait();
		}
	}
	
	//Class-Loader Teil fehlt.

}
