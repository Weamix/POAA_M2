package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeDuplicateCommand implements ICommand {

    private DrawingPane drawingPane;
    private List<IShape> duplicatedShapes = new ArrayList<>();

    public ShapeDuplicateCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;

    }

    @Override
    public void execute() throws Exception {
        if(drawingPane.getListSelectedShapes().isEmpty()){
            throw new Exception("No shape selected");
        }
        drawingPane.getListSelectedShapes().forEach(shape -> {
            IShape clone = shape.clone();
            drawingPane.addShape(clone);
            duplicatedShapes.add(clone);
        });
        drawingPane.getListSelectedShapes().clear();
    }

    @Override
    public void undo() {
        duplicatedShapes.forEach(shape -> drawingPane.removeShape(shape));
    }

    @Override
    public void redo() {
        duplicatedShapes.forEach(shape -> drawingPane.addShape(shape));
    }
}
