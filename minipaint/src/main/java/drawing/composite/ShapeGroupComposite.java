package drawing.composite;

import drawing.adapter.IShape;
import javafx.scene.layout.Pane;

import java.util.List;

public class ShapeGroupComposite implements IShape {
    private final List<IShape> groupShapes;

    public ShapeGroupComposite(List<IShape> groupShapes) {
        this.groupShapes = groupShapes;
    }

    public static IShape create(final List<IShape> group) {
        return new ShapeGroupComposite(group);
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
}
