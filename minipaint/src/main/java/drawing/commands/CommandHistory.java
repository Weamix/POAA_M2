package drawing.commands;

import java.util.Stack;

public class CommandHistory {
    private final Stack<ICommand> undoStack;
    private final Stack<ICommand> redoStack;


    public CommandHistory() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void exec(ICommand iCommand){
        undoStack.push(iCommand);
        iCommand.execute();
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
