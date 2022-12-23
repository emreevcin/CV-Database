package com.Controllers.Add;

import com.Classes.CV;
import com.Classes.Main;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_S5_RO implements Initializable {

    private MainController mainController ;
    private Main main ;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void init (MainController mainController){
        setMainController(mainController);
    }

    public void back(){
        Scene scene4 = this.getMainController().getSceneList().get(3);
        this.getMainController().getAddStage().setScene(scene4);
    }

    @FXML
    public void submit(){
        ArrayList<Scene> scenes = new ArrayList<>();
        for (int i = 0; i <this.getMainController().getSceneList().size() ; i++) {
            scenes.add(this.getMainController().getSceneList().get(i));
        }
        CV cv = new CV(scenes.get(0),scenes.get(1),scenes.get(2),scenes.get(3),scenes.get(4));
        cv.setTitle("a");
        this.getMainController().getCvList().getItems().add(cv.getTitle());
        this.getMainController().getCvMap().put(cv.getTitle(), cv);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
