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
        this.listSelectedShape = new ArrayList<>();
        drawingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
    }

    @Override
    public void handle(MouseEvent event) {

        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            for (IShape shape : drawingPane) {
                if (shape.isOn(event.getX(), event.getY())) {
                    System.out.println("Shape is on");
                    if (event.isShiftDown()) {
                        System.out.println("Shift is down");
                        handleSelectionWhenShiftIsDown(shape);
                    } else {
                        System.out.println("Shift NOT down");
                        removeAllFromSelection();
                        addSelectedShapeToSelection(shape);
                    }
                    break;
                }
            }
        }
        drawingPane.updateObservers();
    }

    private void handleSelectionWhenShiftIsDown(IShape shape) {
        if (!listSelectedShape.contains(shape)) {
            System.out.println("handleSelectionWhenShiftIsDown - addSelectedShapeToSelection");
            addSelectedShapeToSelection(shape);
        } else {
            System.out.println("handleSelectionWhenShiftIsDown - removeSelectedShapeToSelection");
            removeSelectedShapeToSelection(shape);
        }
    }

    public void removeSelectedShapeToSelection(IShape shape) {
        System.out.println("Remove selected shape to selection:" + shape);
        shape.setSelected(false);
        listSelectedShape.remove(shape);
    }

    private void addSelectedShapeToSelection(IShape shape) {
        if(shape.isSelected()){
            removeSelectedShapeToSelection(shape);
        } else{
            System.out.println("Add selected shape to selection:" + shape);
            shape.setSelected(true);
            listSelectedShape.add(shape);
        }
    }

    private void removeAllFromSelection() {
        System.out.println("Remove all from selection:");
        listSelectedShape.forEach(shape -> shape.setSelected(false));
        listSelectedShape.clear();
    }

    public List<IShape> getListSelectedShape() {
        return listSelectedShape;
    }
}
