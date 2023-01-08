package drawing.handler.buttons;

import drawing.DrawingPane;
import drawing.commands.ICommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private ICommand command;
    private DrawingPane drawingPane;

    public ButtonHandler(ICommand command, DrawingPane drawingPane) {
        this.command = command;
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // TODO : have a new command here , is it the best solution?
        var commandClass = command.getClass();

        try {
            command = commandClass.getConstructor(DrawingPane.class).newInstance(drawingPane);
        } catch (Exception e) {
            e.printStackTrace();
        }

        drawingPane.getCommandHistory().exec(command);
    }
}
