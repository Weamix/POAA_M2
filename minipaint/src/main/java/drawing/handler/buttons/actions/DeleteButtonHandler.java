package drawing.handler.buttons.actions;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DeleteButtonHandler implements EventHandler<ActionEvent> {

    private DrawingPane drawingPane;

    public DeleteButtonHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(ActionEvent event) {

        for (IShape shape : drawingPane.getListSelectedShapes()) {
            drawingPane.removeShape(shape);
        }
    }
}