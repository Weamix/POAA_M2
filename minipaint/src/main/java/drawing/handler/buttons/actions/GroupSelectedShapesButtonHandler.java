package drawing.handler.buttons.actions;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import drawing.composite.ShapeGroupComposite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class GroupSelectedShapesButtonHandler implements EventHandler<ActionEvent> {

    private DrawingPane drawingPane;

    public GroupSelectedShapesButtonHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        final List<IShape> selectedShapes = drawingPane.getListSelectedShapes();
        selectedShapes.forEach(drawingPane::removeShape);
        drawingPane.addShape(ShapeGroupComposite.create(selectedShapes));
    }
}
