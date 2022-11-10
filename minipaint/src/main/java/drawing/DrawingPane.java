package drawing;

import drawing.adapter.IShape;
import drawing.bar.Observer;
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
    private List<IShape> shapes;
    private List<Observer> observers;

    public DrawingPane() {
        clipChildren();
        shapes = new ArrayList<>();
        observers = new ArrayList<>();
        mouseMoveHandler = new MouseMoveHandler(this);
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
        //this.getChildren().add(shape);
        shape.addShapeToPane(this);
        observers.forEach(observer -> observer.update(shapes));
    }

    public void removeShape(IShape shape) {
        shapes.remove(shape);
        //this.getChildren().remove(shape);
        shape.removeShapeFromPane(this);
        observers.forEach(observer -> observer.update(shapes));
    }

    public void clear() {
        //this.getChildren().removeAll(shapes);
        observers.forEach(observer -> observer.update(shapes));
        shapes.forEach(shape->shape.removeShapeFromPane(this));
        shapes.clear();
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

}
