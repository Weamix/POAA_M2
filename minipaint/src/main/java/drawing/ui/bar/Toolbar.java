package drawing.ui.bar;

import drawing.DrawingPane;
import drawing.handler.buttons.ClearButtonHandler;
import drawing.handler.buttons.DeleteButtonHandler;
import drawing.handler.buttons.shapes.EllipseButtonHandler;
import drawing.handler.buttons.shapes.RectangleButtonHandler;
import drawing.handler.buttons.shapes.TriangleButtonHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Toolbar {

    private static List<Button> buttons;

    public Toolbar(List<Button> buttons) {
        this.buttons = buttons;
    }

    public static Toolbar initToolbar(DrawingPane drawingPane){
        buttons = new ArrayList<>();

        buttons.add(addButton("Clear", new ClearButtonHandler(drawingPane)));
        buttons.add(addButton("Delete", new DeleteButtonHandler(drawingPane)));
        buttons.add(addButton("Rectangle", new RectangleButtonHandler(drawingPane)));
        buttons.add(addButton("Circle", new EllipseButtonHandler(drawingPane)));
        buttons.add(addButton("Triangle", new TriangleButtonHandler(drawingPane)));

        return new Toolbar(buttons);
    }

    private static Button addButton(String name, EventHandler<? super ActionEvent> handler) {
        Button button = new Button(name);
        button.addEventFilter(ActionEvent.ACTION, handler);
        return button;
    }

    public static List<Button> getButtons() {
        return buttons;
    }
}
