package drawing;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleButtonHandler extends ShapeButtonHandler {

    public TriangleButtonHandler(DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected Shape createShape() {
        double x = originX;
        double y = originY;
        double width = destinationX - originX;
        double height = destinationY - originY;
        Polygon triangle = new Polygon(x, y, width, height);
        triangle.getPoints().addAll(x,y,x+width,y,x+width/2,y+height);
        triangle.getStyleClass().add("triangle");
        return triangle;
    }
}
