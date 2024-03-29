package drawing.commands;

import drawing.DrawingPane;
import drawing.adapter.IShape;
import drawing.composite.ShapeGroupComposite;

import java.util.ArrayList;
import java.util.List;

public class UngroupCommand implements ICommand{
    private DrawingPane drawingPane;
    private final List<ShapeGroupComposite> shapesBackup;


    public UngroupCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        this.shapesBackup = new ArrayList<>();
    }

    @Override
    public void execute() throws Exception {
        if(drawingPane.getListSelectedShapes().isEmpty()){
            throw new Exception("No shape selected");
        }
        final List<IShape> selectedShapes = drawingPane.getListSelectedShapes();
        selectedShapes.forEach(shape -> {
            if (shape instanceof ShapeGroupComposite){
                drawingPane.removeShape(shape);
                shapesBackup.add((ShapeGroupComposite) shape);
                final List<IShape> groupShapes = ((ShapeGroupComposite) shape).getGroupShapes();
                groupShapes.forEach(drawingPane::addShape);
            }
            drawingPane.getListSelectedShapes().clear();
        });
    }

    @Override
    public void undo() {
        try {
            new GroupCommand(drawingPane).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void redo() {
        for (ShapeGroupComposite group: shapesBackup) {
            drawingPane.removeShape(group);
            shapesBackup.forEach(s->drawingPane.addShape(s));
        }
    }
}
