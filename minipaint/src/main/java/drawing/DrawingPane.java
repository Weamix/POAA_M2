package drawing;

import drawing.bar.Observer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class DrawingPane extends Pane implements Iterable<Shape> {

    private MouseMoveHandler mouseMoveHandler;

    private ArrayList<Shape> shapes;
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

    public void addShape(Shape shape) {
        shapes.add(shape);
        this.getChildren().add(shape);
        observers.forEach(observer -> observer.update(shapes));
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
        this.getChildren().remove(shape);
        observers.forEach(observer -> observer.update(shapes));
    }

    public void clear() {
        this.getChildren().removeAll(shapes);
        shapes.clear();
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }
}
