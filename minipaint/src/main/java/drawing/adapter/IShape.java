package drawing.adapter;

import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public interface IShape {
    boolean isSelected();
    void setSelected(boolean selected);
    boolean isOn(double x, double y);
    void offset(double x, double y);
    void addShapeToPane(Pane pane);
    void removeShapeFromPane(Pane pane);
    IShape clone();

    ObservableValue translateXProperty();
    ObservableValue translateYProperty();
}