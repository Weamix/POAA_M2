import drawing.PaintApplication;
import drawing.adapter.IShape;
import drawing.adapter.ShapeAdapter;
import drawing.composite.ShapeGroupComposite;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import java.util.List;

import static org.junit.Assert.*;

public class PaintTest extends ApplicationTest {

    PaintApplication app;

    @Override
    public void start(Stage stage) {
        try {
            app = new PaintApplication();
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_draw_circle_programmatically() {
        interact(() -> {
                    app.getDrawingPane().addShape(new ShapeAdapter(new Ellipse(20, 20, 30, 30)));
                });
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Ellipse);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_circle() {
        // given:
        clickOn("Circle");
        moveBy(60,60);

        // when:
        drag().dropBy(30,30);
        //press(MouseButton.PRIMARY); moveBy(30,30); release(MouseButton.PRIMARY);

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Ellipse);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_rectangle() {
        // given:
        clickOn("Rectangle");
        moveBy(0,60);

        // when:
        drag().dropBy(70,40);

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Rectangle);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_triangle_programmatically() {
        interact(() -> {
            app.getDrawingPane().addShape(new ShapeAdapter(new Polygon(20, 20, 30, 30, 40, 40)));
        });
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Polygon);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_triangle() {
        // given:
        clickOn("Triangle");
        moveBy(-30,100);

        // when:
        drag().dropBy(70, 40);

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Polygon);
        assertFalse(it.hasNext());
    }


    @Test
    public void should_clear() {
        // given:
        clickOn("Rectangle");
        moveBy(30,60).drag().dropBy(70,40);
        clickOn("Circle");
        moveBy(-30,160).drag().dropBy(70,40);

        // when:
        clickOn("Clear");

        // then:
        assertFalse(app.getDrawingPane().iterator().hasNext());
    }

    @Test
    public void should_update_statut_bar_number_forms() {
        clickOn("Triangle");
        moveBy(-30,100).drag().dropBy(70, 40);
        assertEquals("1 forme(s)", app.getStatubar().getNbForms());

        clickOn("Circle");
        moveBy(60,60).drag().dropBy(30,30);
        assertEquals("2 forme(s)", app.getStatubar().getNbForms());

        clickOn("Rectangle");
        moveBy(0,60).drag().dropBy(70,40);
        assertEquals("3 forme(s)", app.getStatubar().getNbForms());

        clickOn("Clear");
        assertFalse(app.getDrawingPane().iterator().hasNext());
        assertEquals("0 forme(s)", app.getStatubar().getNbForms());
    }

    @Test
    public void should_update_statut_bar_selected_shapes(){
        should_draw_triangle();
        clickOn(".triangle");
        assertEquals(" dont 1 selected forme(s)", app.getStatubar().getNbSelectedForms());
    }

    @Test
    public void should_delete_selected_shapes(){
        should_draw_triangle();
        clickOn(".triangle");
        clickOn("Delete");
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(!it.hasNext());
    }

    @Test
    public void should_group_shapes() {
        //draw rec
        clickOn("Rectangle");
        moveBy(0,60);
        drag().dropBy(70,40);

        //draw tri
        clickOn("Circle");
        moveBy(60,60);
        drag().dropBy(30,30);

        clickOn(".rectangle");

        press(KeyCode.SHIFT)
                .clickOn(".ellipse")
                .release(KeyCode.SHIFT);

        clickOn("Group");

        final List<IShape> shapes = app.getDrawingPane().getListSelectedShapes();
        Assert.assertEquals(ShapeGroupComposite.class, shapes.get(0).getClass());
    }

}