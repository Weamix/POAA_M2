package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;

public class ShapeCommand implements  ICommand{
    private DrawingPane drawingPane;
    private IShape shape;

    public ShapeCommand(DrawingPane drawingPane, IShape shape) {
        this.drawingPane = drawingPane;
        this.shape = shape;
    }

    @Override
    public void execute() {
        this.drawingPane.addShape(shape);
    }

    @Override
    public void undo() {
        this.drawingPane.removeShape(shape);
    }

    @Override
    public void redo() {
        execute();
    }
}
