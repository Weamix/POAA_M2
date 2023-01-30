package drawing.commands;

public interface ICommand  {
    void execute() throws Exception;
    void undo();
    void redo();
}
