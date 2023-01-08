package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import drawing.composite.ShapeGroupComposite;

import java.util.List;

public class GroupCommand implements ICommand{

    private DrawingPane drawingPane;
    private ShapeGroupComposite shapeGroupComposite;

    public GroupCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void execute() {
        final List<IShape> selectedShapes = drawingPane.getListSelectedShapes();
        if (selectedShapes != null && selectedShapes.size() > 1) {
            selectedShapes.forEach(drawingPane::removeShape);
            shapeGroupComposite = new ShapeGroupComposite();
            selectedShapes.forEach(shapeGroupComposite::addShape);
            drawingPane.addShape(shapeGroupComposite);
            drawingPane.getListSelectedShapes().clear();
            selectedShapes.forEach(iShape -> System.out.println(iShape.getClass()));
        }
    }

    @Override
    public void undo() {
        new UngroupCommand(drawingPane).execute();
    }

    @Override
    public void redo() {
        shapeGroupComposite.getGroupShapes().forEach(shape -> drawingPane.removeShape(shape));
        drawingPane.addShape(shapeGroupComposite);
        drawingPane.getListSelectedShapes().clear();
    }
}
