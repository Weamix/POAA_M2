package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;

import java.util.List;

public class ShapeDuplicateCommand implements ICommand {

    private DrawingPane drawingPane;
    private List<IShape> duplicatedShapes;

    public ShapeDuplicateCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;

    }

    @Override
    public void execute() {
        drawingPane.getListSelectedShapes().forEach(shape -> {
            IShape clone = shape.clone();
            clone.setSelected(false);
            drawingPane.addShape(clone);
            duplicatedShapes.add(clone);
        });
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
