package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import drawing.decorator.TextDecorator;

public class TextShapeCommand implements  ICommand{

    private DrawingPane drawingPane;
    private IShape backupShape;
    private IShape shapeWithText;
    private String text;

    public TextShapeCommand(DrawingPane drawingPane, String text) {
        this.drawingPane = drawingPane;
        this.text = text;
    }

    @Override
    public void execute() {
        backupShape = drawingPane.getListSelectedShapes().get(0);
        drawingPane.removeShape(backupShape);
        shapeWithText = new TextDecorator(backupShape, text);
        drawingPane.addShape(shapeWithText);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
