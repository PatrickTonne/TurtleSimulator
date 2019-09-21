package view;

import javafx.scene.control.TextArea;
import model.Program;

public class Editor extends TextArea {
    private Program program;

    public Editor(Program program) {
        this.program = program;
       // setText(this.program.getProgram()); <--- Hilfe benötigt
        setText(this.program.getProgram());
    }
}