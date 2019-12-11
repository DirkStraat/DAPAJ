package nl.hava.dapaj.bankapp.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Pedro
 * This class creates a popup that can be called with a custome title and message values
 */
public class AlertPopUp {

    /**
     * This method creates a pop-up to alert the user for important notifications,
     * and can only be closed once the user clicks ok
     * @param title         The title of the pop-up window
     * @param message       The custome message to show to the user
     */
    public static void createPopUp(String title, String message) {
        Stage popUpWindow = new Stage();

        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle(title);
        popUpWindow.setMinWidth(200);
        popUpWindow.setMinHeight(200);

        Label waarschuwing = new Label();
        waarschuwing.setText(message);
        Button ok = new Button("OK");
        ok.setOnAction(e -> popUpWindow.close());

        VBox layout = new VBox(8);
        layout.getChildren().addAll(waarschuwing, ok);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

}
