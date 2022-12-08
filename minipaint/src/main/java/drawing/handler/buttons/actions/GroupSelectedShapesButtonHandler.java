package drawing.handler.buttons.actions;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import drawing.composite.ShapeGroupComposite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class GroupSelectedShapesButtonHandler implements EventHandler<ActionEvent> {

    private DrawingPane drawingPane;
    private ShapeGroupComposite shapeGroupComposite;


    public GroupSelectedShapesButtonHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
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
}
