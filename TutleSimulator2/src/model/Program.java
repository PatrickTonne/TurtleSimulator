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
		createIfNotExists();
	}

	public Program(String name) throws IOException {
		this.setProgramName(name);
		createIfNotExists();
	}

	private void createIfNotExists() throws IOException {
		Path dirPath = Paths.get(ProgramController.DIRECTORY);
		if (Files.notExists(Paths.get(ProgramController.DIRECTORY))) {
			dirPath = Files.createDirectory(dirPath);

		} else if (!Files.isDirectory(dirPath)) {
			throw new IOException();
		} else if (!Files.isWritable(dirPath)) {
			throw new IOException();
		}
		Path filePath = Paths.get(ProgramController.DIRECTORY, getFileName());
		if (!Files.exists(filePath)) {
			String code = getPrefix() + ProgramController.DEFAULTPROGRAMM+ getSuffix();
			ArrayList<String> lines = new ArrayList<>();
			lines.add(code);
			Files.write(filePath, lines, StandardCharsets.UTF_8);
		}
		if (!Files.isWritable(filePath)) {
			throw new IOException();
		}

	}

	public String getProgramName() {
		return programName;
	}

	public String getFileName() {
		return programName + ".java";
	}

	public String getFullFileName() {
		return ProgramController.DIRECTORY + File.separator + getFileName();
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgram() {
		try {
			StringBuffer code = new StringBuffer();
			Path path = Paths.get(ProgramController.DIRECTORY, getFileName());
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
		if (!checkPath()) {
			return false;
		}
		code = getPrefix() + code + getSuffix();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(code);
		try {
			Path path = Paths.get(ProgramController.DIRECTORY, getFileName());
			Files.write(path, lines, StandardCharsets.UTF_8);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean checkPath() {
		// TODO Auto-generated method stub
		return false;
	}

	private String getPrefix() {
		return "public class " + programName + " extends model.Spaceship { public ";
	}

	private String getSuffix() {
		return "}";
	}
}