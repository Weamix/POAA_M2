package drawing.bar;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class StatutBar extends HBox implements Observer{

    private Label nbForms;

    public StatutBar() {
        nbForms = new Label("forme(s)");
        getChildren().add(nbForms);
    }

    @Override
    public void update() {
        nbForms.
    }
}
