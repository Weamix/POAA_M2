package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import drawing.decorator.TextDecorator;

import java.util.ArrayList;
import java.util.List;


public class TextShapeCommand implements  ICommand{

    private DrawingPane drawingPane;
    private List<IShape> backupShapes = new ArrayList<>();
    private List<IShape> shapesWithText = new ArrayList<>();
    private String text;

    public TextShapeCommand(DrawingPane drawingPane) {
        this(drawingPane,"Nemo");
    }

    public TextShapeCommand(DrawingPane drawingPane, String text) {
        this.drawingPane = drawingPane;
        this.text = text;
    }

    @Override
    public void execute() throws Exception {
        if(drawingPane.getListSelectedShapes().isEmpty()){
            throw new Exception("No shape selected");
        }

        backupShapes.addAll(drawingPane.getListSelectedShapes());

        backupShapes.forEach(shape -> {
            IShape shapeWithText = new TextDecorator(shape, text);
            drawingPane.removeShape(shape);
            shapesWithText.add(shapeWithText);
            drawingPane.addShape(shapeWithText);
        });
    }

    @Override
    public void undo() {
        shapesWithText.forEach(shape -> {
            drawingPane.removeShape(shape);
            drawingPane.addShape(backupShapes.get(shapesWithText.indexOf(shape)));
        });
    }

    @Override
    public void redo() {
        shapesWithText.forEach(shape -> {
            drawingPane.removeShape(backupShapes.get(shapesWithText.indexOf(shape)));
            drawingPane.addShape(shape);
        });
    }
}
