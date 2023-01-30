package drawing.commands;

import drawing.DrawingPane;

import java.util.Stack;

public class CommandHistory {
    private final Stack<ICommand> undoStack;
    private final Stack<ICommand> redoStack;
    private final DrawingPane drawingPane;

    public CommandHistory(DrawingPane drawingPane) {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.drawingPane = drawingPane;
    }

    public void exec(ICommand iCommand){
        try{
            iCommand.execute();
            undoStack.push(iCommand);
        } catch (Exception e) {
            e.printStackTrace();
            drawingPane.setError(e.getMessage());
            drawingPane.updateObservers();
        }
    }

    public void undo(){
        if (!undoStack.isEmpty()) {
            ICommand command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo(){
        if (!redoStack.isEmpty()) {
            ICommand command = redoStack.pop();
            command.redo();
            undoStack.push(command);
        }
    }
}
