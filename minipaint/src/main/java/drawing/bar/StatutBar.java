package drawing.bar;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Shape;

import java.util.List;


public class StatutBar extends HBox implements Observer{

    public static final String FORME_S = " forme(s)";
    private Label nbForms;

    public StatutBar() {
        nbForms = new Label("0" + FORME_S);
        getChildren().add(nbForms);
    }

    public String getNbForms() {
        return nbForms.getText();
    }

    @Override
    public void update(final List<Shape> shapes) {
        nbForms.setText(shapes.size() + FORME_S);
    }
}
