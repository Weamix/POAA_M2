package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements ICommand{
    private DrawingPane drawingPane;
    private List<IShape> shapesBackup;

    public ClearCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        this.shapesBackup = new ArrayList<>();
    }

    @Override
    public void execute() {
        this.shapesBackup = List.copyOf(drawingPane.getShapes());
        this.drawingPane.clear();
    }

    @Override
    public void undo() {
        shapesBackup.forEach(shape -> drawingPane.addShape(shape));
    }

    @Override
    public void redo() {
        execute();
    }
}
