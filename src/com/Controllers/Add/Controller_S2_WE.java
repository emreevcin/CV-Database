package com.Controllers.Add;

import com.Classes.Main;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_S2_WE implements Initializable {

    private Main main ;
    private MainController mainController ;

    private Scene scene ;


    @FXML
    private Button backButton;

    @FXML
    private TextField cityTF;

    @FXML
    private TextField countryTF;

    @FXML
    private TextField employerTF;

    @FXML
    private TextArea explanationTA;

    @FXML
    private DatePicker fromDate;

    @FXML
    private CheckBox isOngoing;

    @FXML
    private Button nextButton;

    @FXML
    private TextField occupationTF;

    @FXML
    private DatePicker toDate;



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
    @FXML
    public void next(){
        try{
            this.getMainController().getInformation().put("occupation", occupationTF.getText());
            this.getMainController().getInformation().put("employer", employerTF.getText());
            this.getMainController().getInformation().put("cityWE", cityTF.getText());
            this.getMainController().getInformation().put("countryWE", countryTF.getText());
            this.getMainController().getInformation().put("fromWE", fromDate.getValue().toString());
            this.getMainController().getInformation().put("toWE", toDate.getValue().toString());
            this.getMainController().getInformation().put("ongoingWE", isOngoing.getText());
            this.getMainController().getInformation().put("explanationWE", explanationTA.getText());
            Scene scene3 = this.getMainController().getSceneList().get(2);
            this.getMainController().getAddStage().setScene(scene3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void back(){
      try{
          Scene scene1 = this.getMainController().getSceneList().get(0);
          this.getMainController().getAddStage().setScene(scene1);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
