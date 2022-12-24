package com.Controllers.Add;

import com.Classes.Main;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_S4_CS implements Initializable {
    private MainController mainController ;
    private Main main ;
    private Scene scene ;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker certificateDate;

    @FXML
    private TextField companyTF;

    @FXML
    private TextField educationTF;

    @FXML
    private TextField hardTF;

    @FXML
    private TextField motherTF;

    @FXML
    private Button nextButton;

    @FXML
    private TextField otherTF;

    @FXML
    private TextField softTF;

    @FXML
    private TextField titleTF;

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
    public void next(){
        try{
            this.getMainController().getInformation().put("education", educationTF.getText());
            this.getMainController().getInformation().put("company", companyTF.getText());
            this.getMainController().getInformation().put("dateC", certificateDate.getValue().toString());
            this.getMainController().getInformation().put("mother", motherTF.getText());
            this.getMainController().getInformation().put("otherLanguage", otherTF.getText());
            this.getMainController().getInformation().put("softSkills", softTF.getText());
            this.getMainController().getInformation().put("hardSkills", hardTF.getText());
            this.getMainController().getInformation().put("descriptionHI", titleTF.getText());
            Scene scene5 = this.getMainController().getSceneList().get(4);
            this.getMainController().getAddStage().setScene(scene5);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void back(){
        Scene scene3 = this.getMainController().getSceneList().get(2);
        this.getMainController().getAddStage().setScene(scene3);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
