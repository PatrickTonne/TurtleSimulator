package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import controller.ProgramController;

public class Program {

	private String programName;

	public Program() throws IOException {
		this.setProgramName("DefaultTurtle");
		create();
	}

	public Program(String name) throws IOException {
		this.setProgramName(name);
		create();
	}
	
	//https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	private void create() throws IOException {
		Path path = Paths.get(ProgramController.SAVEFOLDER);
		if (Files.notExists(Paths.get(ProgramController.SAVEFOLDER))) {
			path = Files.createDirectory(path);
		}
		Path filePath = Paths.get(ProgramController.SAVEFOLDER, getFileName());
		if (Files.notExists(filePath)) {
			String code = getPrefix() + ProgramController.MAINPROGRAM + getSuffix();
			ArrayList<String> codeLines = new ArrayList<>();
			codeLines.add(code);
			Files.write(filePath, codeLines, StandardCharsets.UTF_8);
		}

	}

	public String getProgramName() {
		return programName;
	}

	public String getFileName() {
		return programName + ".java";
	}

	public String getFullFileName() {
		return ProgramController.SAVEFOLDER + File.separator + getFileName();
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	//https://examples.javacodegeeks.com/core-java/lang/stringbuffer/java-stringbuffer-example/
	public String getProgram() {
		try {
			StringBuffer code = new StringBuffer();
			Path path = Paths.get(ProgramController.SAVEFOLDER, getFileName());
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			for (int i = 0; i < lines.size(); i++) {
				code.append(lines.get(i));
				if (i < lines.size() - 1) {
					code.append(System.lineSeparator());
				}
			}
			code.delete(0, getPrefix().length());
			code.delete(code.length() - getSuffix().length(), code.length());
			return code.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}

	public boolean save(String code) {
		code = getPrefix() + code + getSuffix();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(code);
		try {
			Path path = Paths.get(ProgramController.SAVEFOLDER, getFileName());
			Files.write(path, lines, StandardCharsets.UTF_8);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private String getPrefix() {
		return " import util.Invisible; public class " + programName + " extends model.Turtle { public ";
	}

	private String getSuffix() {
		return "}";
	}
	
}