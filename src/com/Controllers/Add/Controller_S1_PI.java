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

public class Controller_S1_PI implements Initializable {

    private MainController mainController ;
    private Main main ;

    private Scene scene ;

    @FXML
    private Button nextButton ;


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void init (MainController mainController){
        setMainController(mainController);
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

    @FXML
    public void next(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/work-experience-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = this.getMainController().getAddStage();
            Controller_S2_WE controller_s2_we = loader.getController();
            controller_s2_we.init(this.getMainController());
            stage.setScene(scene);


        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
