package drawing.handler;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class SelectionHandler implements EventHandler<MouseEvent> {
    private DrawingPane drawingPane;
    private List<IShape> listSelectedShape;

    public SelectionHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        listSelectedShape = new ArrayList<>();
        drawingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
    }

    @Override
    public void handle(MouseEvent event) {

        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            for (IShape shape : drawingPane) {
                if (shape.isOn(event.getX(), event.getY())) {
                    if (event.isShiftDown()) {
                        handleSelectionWhenShiftIsDown(shape);
                    } else {
                        removeAllFromSelection();
                        addSelectedShapeToSelection(shape);
                    }
                    break;
                } else {
                    removeAllFromSelection();
                }
            }
        }
    }

    private void handleSelectionWhenShiftIsDown(IShape shape) {
        if (!listSelectedShape.contains(shape)) {
            addSelectedShapeToSelection(shape);
        } else {
            removeSelectedShapeToSelection(shape);
        }
    }

    private void removeSelectedShapeToSelection(IShape shape) {
        shape.setSelected(false);
        listSelectedShape.remove(shape);
    }

    private void addSelectedShapeToSelection(IShape shape) {
        shape.setSelected(true);
        listSelectedShape.add(shape);
    }

    private void removeAllFromSelection() {
        listSelectedShape.forEach(shape -> shape.setSelected(false));
        listSelectedShape.clear();
    }

    public List<IShape> getListSelectedShape() {
        return listSelectedShape;
    }
}
