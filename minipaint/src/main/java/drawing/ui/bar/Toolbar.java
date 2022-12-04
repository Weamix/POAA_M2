package drawing.ui.bar;

import drawing.DrawingPane;
import drawing.handler.buttons.actions.ClearButtonHandler;
import drawing.handler.buttons.actions.DeleteButtonHandler;
import drawing.handler.buttons.actions.GroupSelectedShapesButtonHandler;
import drawing.handler.buttons.actions.UngroupSelectedShapesButtonHandler;
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
    private static final String TEXT_ONLY  = "TEXT_ONLY";


    private Toolbar(List<Button> buttons) {
        this.buttons = buttons;
    }

    public static Toolbar initToolbar(DrawingPane drawingPane) throws IOException {
        buttons = new ArrayList<>();

        buttons.add(ButtonFactory.createButton("Clear", new ClearButtonHandler(drawingPane), "delete.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Delete", new DeleteButtonHandler(drawingPane), "clear.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Circle", new EllipseButtonHandler(drawingPane), "circle.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Triangle", new TriangleButtonHandler(drawingPane), "triangle.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Rectangle", new RectangleButtonHandler(drawingPane), "rectangle.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Group", new GroupSelectedShapesButtonHandler(drawingPane), "group.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Ungroup", new UngroupSelectedShapesButtonHandler(drawingPane), "ungroup.png",TEXT_ONLY));

        return new Toolbar(buttons);
    }

    public static List<Button> getButtons() {
        return buttons;
    }
}
