package kaidama;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.gui.MainWindow;

/**
 * A GUI for Kaidama using FXML.
 */
public class Main extends Application {

    private Kaidama kaidama = new Kaidama();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Kaidama Chatbot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKaidama(kaidama);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
