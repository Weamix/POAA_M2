package drawing.bar;

import drawing.adapter.IShape;

import java.util.List;

public interface Observer {

    void update(List<IShape> shapes, List<IShape> selectedShapes);
}
