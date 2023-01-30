package drawing.decorator;

import drawing.adapter.IShape;
import drawing.composite.ShapeGroupComposite;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TextDecorator implements IShape {

    private IShape wrappee;
    private Label label;

    public TextDecorator(IShape wrappee, String text) throws IllegalArgumentException {
        this.wrappee = wrappee;
        label = new Label(text);

        if(wrappee instanceof ShapeGroupComposite){
            throw new IllegalArgumentException("Cannot add text to a group of shapes");
        }
    }

    @Override
    public boolean isSelected() {
       return wrappee.isSelected();
    }

    @Override
    public void setSelected(boolean selected) {
        wrappee.setSelected(selected);
    }

    @Override
    public boolean isOn(double x, double y) {
        return wrappee.isOn(x, y);
    }

    @Override
    public void offset(double x, double y) {
        wrappee.offset(x, y);
        label.setTranslateX(label.getTranslateX() + x);
        label.setTranslateY(label.getTranslateY() + y);
    }

    @Override
    public void addShapeToPane(Pane pane) {
        wrappee.addShapeToPane(pane);
        pane.getChildren().add(label);
    }

    @Override
    public void removeShapeFromPane(Pane pane) {
        wrappee.removeShapeFromPane(pane);
        pane.getChildren().remove(label);
    }

    @Override
    public IShape clone() {
        return wrappee.clone();
    }
}
