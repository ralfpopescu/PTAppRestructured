package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;


public class Controller {

    Stage stage;
    ExcelOrganizer excelOrganizer;

    public Controller(){
        excelOrganizer = new ExcelOrganizer();
    }

    public void handleSelect(){
        boolean success = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        try {
            success = excelOrganizer.handleSheet(file);
        } catch (Exception e){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(":(");
            alert.setHeaderText("Oops!");
            alert.setContentText("Something went wrong.");

            alert.showAndWait();
        }


        if(success){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success!");
            alert.setContentText("Schedule made.");

            alert.showAndWait();
        }


    }


    public void setStage(Stage stage){
        this.stage = stage;
    }
}
