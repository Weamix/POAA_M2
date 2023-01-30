package drawing.ui.bar;

import drawing.adapter.IShape;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;


public class ErrorBar extends HBox implements Observer{

    public static final String ERROR = "Error : ";

    public final Label errorLabel;

    public ErrorBar() {
        errorLabel = new Label();
        getChildren().add(errorLabel);
    }

    @Override
    public void update(List<IShape> shapes, List<IShape> selectedShapes, String error) {
        errorLabel.setText(ERROR + error);
    }
}
