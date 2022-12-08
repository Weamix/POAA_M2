package drawing.ui.button;

import drawing.ui.bar.Toolbar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ButtonFactory {
    public static Button createButton(String buttonName, EventHandler<? super ActionEvent> handler, String urlImg, String style) throws IOException {
        Button button = new Button();
        button.setId(buttonName);

        switch(style){
            case "ICON_ONLY":
                Image img = new Image(Toolbar.class.getClassLoader().getResource(urlImg).openStream());
                ImageView view = new ImageView(img);
                button.setGraphic(view);
                break;
            default:
            case "TEXT_ONLY":
                button.setText(buttonName);
                break;
        }

        button.addEventFilter(ActionEvent.ACTION, handler);

        return button;
    }
}
