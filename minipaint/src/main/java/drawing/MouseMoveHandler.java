package drawing;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

import java.util.Iterator;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class MouseMoveHandler implements EventHandler<MouseEvent> {

    private DrawingPane drawingPane;

    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;

    private Shape selectedShape;

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

            for (Iterator<Shape> it = drawingPane.iterator(); it.hasNext(); ) {
                Shape shape = it.next();
                if (shape.getBoundsInParent().contains(event.getX(), event.getY())) {
                    selectedShape = shape;
                    break;
                }
            }

            orgTranslateX = selectedShape == null ? 0 : selectedShape.getTranslateX();
            orgTranslateY = selectedShape == null ? 0 : selectedShape.getTranslateY();

        }

        if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            if (selectedShape == null)
                return;

            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            selectedShape.setTranslateX(newTranslateX);
            selectedShape.setTranslateY(newTranslateY);
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            selectedShape = null;
        }
    }
}
