package drawing.bar;

import javafx.scene.shape.Shape;

import java.util.List;

public interface Observer {

    void update(List<Shape> shapes);
}
