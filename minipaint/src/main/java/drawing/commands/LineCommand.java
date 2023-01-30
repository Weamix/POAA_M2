package drawing.commands;

import drawing.DrawingPane;
import drawing.Edge;
import drawing.adapter.IShape;

public class LineCommand implements ICommand {

    private DrawingPane drawingPane;

    public LineCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void execute() throws Exception {
        if(drawingPane.getListSelectedShapes().isEmpty()){
            throw new Exception("No shape selected");
        }
        else if(drawingPane.getListSelectedShapes().size() == 1){
            throw new Exception("You need to select 2 shapes to create an edge");
        }
        else if(drawingPane.getListSelectedShapes().size() > 2){
            throw new Exception("Too many shapes selected");
        }

        drawingPane.getListSelectedShapes();
        IShape from = drawingPane.getListSelectedShapes().get(0);
        IShape to = drawingPane.getListSelectedShapes().get(1);
        Edge edge = new Edge(from, to);
        drawingPane.addShape(edge);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
