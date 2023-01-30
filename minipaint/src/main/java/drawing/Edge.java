package drawing;

import drawing.adapter.IShape;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Edge implements IShape {

    private IShape from;
    private IShape to;
    private Line shape;

    private boolean selected;

    public Edge(IShape from, IShape to) {
        this.from = from;
        this.to = to;

        shape  = new Line();
        shape.startXProperty().bind(from.translateXProperty());
        shape.startYProperty().bind(from.translateYProperty());
        shape.endXProperty().bind(to.translateXProperty());
        shape.endYProperty().bind(to.translateYProperty());
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        from.setSelected(selected);
        to.setSelected(selected);

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
    }

    @Override
    public void addShapeToPane(Pane pane) {
        pane.getChildren().add(shape);
    }

    @Override
    public void removeShapeFromPane(Pane pane) {
        pane.getChildren().remove(shape);
    }

    @Override
    public IShape clone() {
        return null;
    }

    @Override
    public ObservableValue translateXProperty() {
        return null;
    }

    @Override
    public ObservableValue translateYProperty() {
        return null;
    }
}
