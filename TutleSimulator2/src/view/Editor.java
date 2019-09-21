package view;

import model.Program;
import javafx.scene.control.TextArea;

public class Editor extends TextArea {
    private Program program;

    public Editor(Program program) {
        this.program = program;
        setText(this.program.getProgram());
    }
}