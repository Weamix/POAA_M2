package drawing.adapter;

import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ShapeAdapter implements IShape {

    Shape shape;
    private boolean selected;
    private double x;
    private double y;

    public ShapeAdapter(Shape shape) {
        this.shape = shape;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        if(selected) {
            shape.getStyleClass().add("selected");
        }else{
            shape.getStyleClass().removeAll("selected");
        }
    }

    @Override
    public boolean isOn(double x, double y) {
        return shape.getBoundsInParent().contains(x,y);
    }

    @Override
    public void offset(double x, double y) {
        shape.setTranslateX(x + shape.getTranslateX());
        shape.setTranslateY(y + shape.getTranslateY());
    }

    @Override
    public void addShapeToPane(Pane pane) {
        pane.getChildren().add(shape);
        x = shape.getBoundsInParent().getCenterX();
        y = shape.getBoundsInParent().getCenterY();
    }

    @Override
    public void removeShapeFromPane(Pane pane) {
        pane.getChildren().remove(shape);
    }

    @Override
    public IShape clone() {
        final var union = Shape.union(shape, shape);
        shape.getStyleClass()
                .forEach(css -> union.getStyleClass().add(css));

        ShapeAdapter clone = new ShapeAdapter(union);
        clone.setSelected(selected);
        return clone;
    }

    @Override
    public ObservableValue translateXProperty() {
        return shape.translateXProperty().add(x);
    }

    @Override
    public ObservableValue translateYProperty() {
        return shape.translateYProperty().add(y);
    }
}
