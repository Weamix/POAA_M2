package drawing.composite;

import drawing.adapter.IShape;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ShapeGroupComposite implements IShape {
    private List<IShape> groupShapes;

    public ShapeGroupComposite() {
        this.groupShapes = new ArrayList<>();
    }

    public void addShape(IShape shape) {
        groupShapes.add(shape);
    }

    public void removeShape(IShape shape) {
        groupShapes.remove(shape);
    }

    public List<IShape> getGroupShapes() {
        return groupShapes;
    }

    @Override
    public boolean isSelected() {
        return groupShapes.stream().anyMatch(IShape::isSelected);
    }

    @Override
    public void setSelected(boolean selected) {
        groupShapes.forEach(shape -> shape.setSelected(selected));
    }

    @Override
    public boolean isOn(double x, double y) {
        return groupShapes.stream().anyMatch(shape -> shape.isOn(x, y));
    }

    @Override
    public void offset(double x, double y) {
        groupShapes.forEach(shape -> shape.offset(x, y));
    }

    @Override
    public void addShapeToPane(Pane pane) {
        groupShapes.forEach(shape -> shape.addShapeToPane(pane));
    }

    @Override
    public void removeShapeFromPane(Pane pane) {
        groupShapes.forEach(shape -> shape.removeShapeFromPane(pane));
    }

    @Override
    public IShape clone() {
        ShapeGroupComposite clone = new ShapeGroupComposite();
        groupShapes.forEach(shape -> clone.addShape(shape.clone()));
        return clone;
    }
}
