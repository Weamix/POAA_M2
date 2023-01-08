package drawing.commands;

public interface ICommand {
    void execute();
    void undo();
    void redo();
}
