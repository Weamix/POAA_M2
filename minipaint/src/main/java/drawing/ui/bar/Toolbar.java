package drawing.ui.bar;

import drawing.DrawingPane;
import drawing.handler.buttons.ClearButtonHandler;
import drawing.handler.buttons.DeleteButtonHandler;
import drawing.handler.buttons.shapes.EllipseButtonHandler;
import drawing.handler.buttons.shapes.RectangleButtonHandler;
import drawing.handler.buttons.shapes.TriangleButtonHandler;
import drawing.ui.button.ButtonFactory;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Toolbar {

    private static List<Button> buttons;
    private static final String ICON_ONLY  = "ICON_ONLY";

    private Toolbar(List<Button> buttons) {
        this.buttons = buttons;
    }

    public static Toolbar initToolbar(DrawingPane drawingPane) throws IOException {
        buttons = new ArrayList<>();

        buttons.add(ButtonFactory.createButton("Clear", new ClearButtonHandler(drawingPane), "delete.png","TEXT_ONLY"));
        buttons.add(ButtonFactory.createButton("Delete", new DeleteButtonHandler(drawingPane), "clear.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Circle", new EllipseButtonHandler(drawingPane), "circle.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Triangle", new TriangleButtonHandler(drawingPane), "triangle.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Rectangle", new RectangleButtonHandler(drawingPane), "rectangle.png",ICON_ONLY));

        return new Toolbar(buttons);
    }

    public static List<Button> getButtons() {
        return buttons;
    }
}
