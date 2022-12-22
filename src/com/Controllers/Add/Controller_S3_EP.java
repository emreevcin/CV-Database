package com.Controllers.Add;

import com.Classes.Main;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_S3_EP implements Initializable {

    private MainController mainController ;
    private Main main ;
    private Scene scene ;


    @FXML
    private Button nextButton;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void init (MainController mainController){
        setMainController(mainController);
    }
    public void next(){

        try{
            Scene scene4 = this.getMainController().getSceneList().get(3);
            this.getMainController().getAddStage().setScene(scene4);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void back(){
        Scene scene2 = this.getMainController().getSceneList().get(1);
        this.getMainController().getAddStage().setScene(scene2);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
