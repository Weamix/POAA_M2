package drawing;

import drawing.adapter.IShape;
import drawing.adapter.ShapeAdapter;
import javafx.scene.shape.Polygon;

public class TriangleButtonHandler extends ShapeButtonHandler {

    public TriangleButtonHandler(DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected IShape createShape() {
        double x = originX;
        double y = originY;
        double width = destinationX - originX;
        double height = destinationY - originY;
        Polygon triangle = new Polygon(x, y, width, height);
        triangle.getPoints().addAll(x,y,x+width,y,x+width/2,y+height);
        triangle.getStyleClass().add("triangle");
        return new ShapeAdapter(triangle);
    }
}
