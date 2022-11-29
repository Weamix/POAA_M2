package drawing;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class MouseMoveHandler implements EventHandler<MouseEvent> {

    private DrawingPane drawingPane;
    private double orgSceneX;
    private double orgSceneY;

    public MouseMoveHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        drawingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        drawingPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
        drawingPane.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
    }

    @Override
    public void handle(MouseEvent event) {

        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && drawingPane.getListSelectedShapes() != null) {
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            drawingPane.getListSelectedShapes().forEach(shape -> shape.offset(offsetX, offsetY));

            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
        }
    }
}
