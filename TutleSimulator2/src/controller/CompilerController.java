package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Program;
import model.Territorium;
import model.Turtle;

public class CompilerController {
	
	public static void StartCompile(Program program, Territorium ter) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		ByteArrayOutputStream fail = new ByteArrayOutputStream();
		int result = compiler.run(null, null, fail, program.getFullFileName());
		if ( result == 0) {
			loadTurtle(program, ter);
		}
		
	}
	// Hilfe Compiler benutzen: https://stackoverflow.com/questions/7989135/is-it-possible-to-programmatically-compile-java-source-code-in-memory-only
	public static void Compile(Program program, Territorium ter) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		ByteArrayOutputStream fail = new ByteArrayOutputStream();
		int result = compiler.run(null, null, fail, program.getFullFileName());
		if (result == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Compiler");
	        alert.setHeaderText("Erfolgreich!");
	        alert.setContentText("Dein Programm wurde erfolgreich kompiliert.");
	        alert.showAndWait();
	        loadTurtle(program, ter);
	       
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Kompilierfehler");
	        alert.setHeaderText("Da meckert der Compiler!");
	        alert.setContentText(fail.toString());
	 
	        alert.showAndWait();
		}
	}
	
	public static void loadTurtle(Program prog, Territorium ter) {
		URLClassLoader classLoader = null;
		try {
			File save = new File(ProgramController.SAVEFOLDER);
			classLoader = new URLClassLoader(new URL[] {save.toURI().toURL()});
			Turtle turtle = (Turtle) classLoader.loadClass(prog.getProgramName()).newInstance();
			ter.setTurtle(turtle);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(classLoader != null) {
				try {
					classLoader.close();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
	}
	
	

}
