package drawing;

import drawing.ui.bar.ErrorBar;
import drawing.ui.bar.StatutBar;
import drawing.ui.bar.Toolbar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by lewandowski on 20/12/2020.
 * Updated by mvitse on 2022
 */
public class PaintApplication extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Scene scene;
    private BorderPane root;
    private DrawingPane drawingPane;
    private StatutBar statutBar;
    private ErrorBar errorBar;

    @Override
    public void start(Stage primaryStage) throws IOException {
        root = new BorderPane();
        scene = new Scene(root, WIDTH, HEIGHT);
        HBox hBox = new HBox();
        statutBar = new StatutBar();
        errorBar = new ErrorBar();

        root.getStylesheets().add(
                PaintApplication.class.getClassLoader().getResource("style/Paint.css").toExternalForm());

        drawingPane = new DrawingPane();
        drawingPane.getStyleClass().add("drawingPane");
        drawingPane.addObserver(statutBar);
        drawingPane.addObserver(errorBar);
        root.setCenter(drawingPane);

        Toolbar.initToolbar(drawingPane);
        hBox.getChildren().addAll(Toolbar.getButtons());
        hBox.setPadding(new Insets(5));
        hBox.setSpacing(5.0);
        hBox.getStyleClass().add("toolbar");

        statutBar.setPadding(new Insets(5));
        statutBar.setSpacing(5.0);
        statutBar.getStyleClass().add("toolbar");

        errorBar.setPadding(new Insets(5));
        errorBar.setSpacing(5.0);
        errorBar.getStyleClass().add("toolbar");

        root.setTop(hBox);
        root.setBottom(statutBar);
        root.setBottom(errorBar);

        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public DrawingPane getDrawingPane() {
        return drawingPane;
    }

    public StatutBar getStatubar() { return statutBar;}

    public static void main(String[] args) {
        launch(args);
    }
}
