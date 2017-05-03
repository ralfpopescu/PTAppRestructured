package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("parktavern.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("Park Tavern Schedule Maker");
        primaryStage.setScene(new Scene(root, 250, 310));
        primaryStage.show();

        primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream( "parktavernlogo.png" )));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
