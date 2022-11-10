import drawing.PaintApplication;
import drawing.bar.Observer;
import drawing.bar.StatutBar;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Iterator;

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

    @Ignore
    @Test
    public void should_draw_circle_programmatically() {
        interact(() -> {
                    app.getDrawingPane().addShape(new Ellipse(20, 20, 30, 30));
                });
        Iterator it = app.getDrawingPane().iterator();
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
        Iterator it = app.getDrawingPane().iterator();
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
        Iterator it = app.getDrawingPane().iterator();
        assertTrue(it.next() instanceof Rectangle);
        assertFalse(it.hasNext());
    }

    @Test
    @Ignore
    public void should_draw_triangle_programmatically() {
        interact(() -> {
            app.getDrawingPane().addShape(new Polygon(20, 20, 30, 30, 40, 40));
        });
        var it = app.getDrawingPane().iterator();
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
    public void should_update_statut_bar_test() throws InterruptedException {
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

}