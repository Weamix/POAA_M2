package drawing;

import drawing.adapter.IShape;
import drawing.commands.CommandHistory;
import drawing.ui.bar.Observer;
import drawing.handler.MouseMoveHandler;
import drawing.handler.SelectionHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class DrawingPane extends Pane implements Iterable<IShape> {

    private MouseMoveHandler mouseMoveHandler;
    private SelectionHandler selectionHandler;
    private List<IShape> shapes;
    private List<Observer> observers;

    private CommandHistory commandHistory;
    private String error;

    public DrawingPane() {
        clipChildren();
        shapes = new ArrayList<>();
        observers = new ArrayList<>();
        mouseMoveHandler = new MouseMoveHandler(this);
        selectionHandler = new SelectionHandler(this);
        commandHistory = new CommandHistory(this);
    }


    /**
     * Clips the children of this {@link Region} to its current size.
     * This requires attaching a change listener to the region’s layout bounds,
     * as JavaFX does not currently provide any built-in way to clip children.
     */
    void clipChildren() {
        final Rectangle outputClip = new Rectangle();
        this.setClip(outputClip);

        this.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }

    public void addShape(IShape shape) {
        shapes.add(shape);
        shape.addShapeToPane(this);
        updateObservers();
    }

    public void removeShape(IShape shape) {
        shapes.remove(shape);
        shape.removeShapeFromPane(this);
        updateObservers();
    }

    public void clear() {
        shapes.forEach(shape->shape.removeShapeFromPane(this));
        shapes.clear();
        updateObservers();
    }

    public void updateObservers(){
        observers.forEach(observer -> observer.update(shapes, getListSelectedShapes(), error));
    }

    public List<IShape> getListSelectedShapes(){
        return new ArrayList<>(selectionHandler.getListSelectedShape());
    }

    public List<IShape> getShapes() {
        return shapes;
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public Iterator<Observer> getObservers() {
        return observers.iterator();
    }

    @Override
    public Iterator<IShape> iterator() {
        return shapes.iterator();
    }

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    public void setError(String error) {
        this.error = error;
    }
}
