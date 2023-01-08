package drawing.ui.bar;

import drawing.DrawingPane;
import drawing.commands.*;
import drawing.handler.buttons.ButtonHandler;
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

        buttons.add(ButtonFactory.createButton("Clear", new ButtonHandler(new ClearCommand(drawingPane), drawingPane), "delete.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Delete", new ButtonHandler(new DeleteCommand(drawingPane),drawingPane), "clear.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Circle", new EllipseButtonHandler(drawingPane), "circle.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Triangle", new TriangleButtonHandler(drawingPane), "triangle.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Rectangle", new RectangleButtonHandler(drawingPane), "rectangle.png",ICON_ONLY));
        buttons.add(ButtonFactory.createButton("Group", new ButtonHandler(new GroupCommand(drawingPane),drawingPane), "group.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Ungroup", new ButtonHandler(new UngroupCommand(drawingPane),drawingPane), "ungroup.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Undo", event -> drawingPane.getCommandHistory().undo(), "undo.png",TEXT_ONLY));
        buttons.add(ButtonFactory.createButton("Redo", event -> drawingPane.getCommandHistory().redo(), "redo.png",TEXT_ONLY));

        return new Toolbar(buttons);
    }

    public static List<Button> getButtons() {
        return buttons;
    }
}
