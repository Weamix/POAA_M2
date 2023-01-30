package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements ICommand{
    private DrawingPane drawingPane;
    private final List<IShape> shapesBackup;

    public DeleteCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        this.shapesBackup = new ArrayList<>();
    }

    @Override
    public void execute() throws Exception {
        if(drawingPane.getListSelectedShapes().isEmpty()){
            throw new Exception("No shape selected");
        }
        for (IShape shape : drawingPane.getListSelectedShapes()) {
            drawingPane.removeShape(shape);
            shapesBackup.add(shape);
        }
    }

    @Override
    public void undo() {
        shapesBackup.forEach(shape -> drawingPane.addShape(shape));
    }

    @Override
    public void redo() {
        shapesBackup.forEach(shape -> drawingPane.removeShape(shape));
        drawingPane.getListSelectedShapes().clear();
    }
}
