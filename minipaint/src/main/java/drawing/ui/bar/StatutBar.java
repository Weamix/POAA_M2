package drawing.ui.bar;

import drawing.adapter.IShape;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;


public class StatutBar extends HBox implements Observer{

    public static final String SELECTED_FORME_S = " selected forme(s)";
    public static final String FORME_S = " forme(s)";
    private Label nbForms;
    private Label nbSelectedForms;

    public StatutBar() {
        nbForms = new Label("0" + FORME_S);
        nbSelectedForms = new Label("0" + FORME_S);
        getChildren().addAll(nbForms, nbSelectedForms);
    }

    public String getNbForms() {
        return nbForms.getText();
    }

    public String getNbSelectedForms() {
        return nbSelectedForms.getText();
    }

    @Override
    public void update(final List<IShape> shapes, final List<IShape> selectedShapes) {
        nbForms.setText(shapes.size() + FORME_S);
        nbSelectedForms.setText(" dont "+ selectedShapes.size() + SELECTED_FORME_S);
    }
}
