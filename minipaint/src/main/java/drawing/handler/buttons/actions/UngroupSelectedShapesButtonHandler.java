package drawing.handler.buttons.actions;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import drawing.composite.ShapeGroupComposite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class UngroupSelectedShapesButtonHandler implements EventHandler<ActionEvent> {

    private DrawingPane drawingPane;

    public UngroupSelectedShapesButtonHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        final List<IShape> selectedShapes = drawingPane.getListSelectedShapes();
        selectedShapes.forEach(shape -> {
            if (shape instanceof ShapeGroupComposite){
                drawingPane.removeShape(shape);
                final List<IShape> groupShapes = ((ShapeGroupComposite) shape).getGroupShapes();
                groupShapes.forEach(drawingPane::addShape);
            }
        });
    }
}
