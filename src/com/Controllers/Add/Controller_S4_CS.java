package com.Controllers.Add;

import com.Classes.CV;
import com.Classes.Main;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Button backButton,nextButton;

    @FXML
    private DatePicker certificateDate;

    @FXML
    private TextField companyTF,educationTF,hardTF,
                      motherTF,otherTF,softTF,titleTF;


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


    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }


    //All things that need to be done when the scene is loaded
    public void allInfo(boolean IncludeDate){
        this.getMainController().getInformation().put("education", educationTF.getText());
        this.getMainController().getInformation().put("company", companyTF.getText());
        if(IncludeDate){
            this.getMainController().getInformation().put("dateC", certificateDate.getValue().toString());
        }
        this.getMainController().getInformation().put("mother", motherTF.getText());
        this.getMainController().getInformation().put("otherLanguage", otherTF.getText());
        this.getMainController().getInformation().put("softSkills", softTF.getText());
        this.getMainController().getInformation().put("hardSkills", hardTF.getText());
        this.getMainController().getInformation().put("descriptionHI", titleTF.getText());
        Scene scene5 = this.getMainController().getSceneList().get(4);
        this.getMainController().getAddStage().setScene(scene5);
    }



    public void next() {
        if(mainController.isEditFunctionRunned()){
            String title = mainController.getCvList().getSelectionModel().getSelectedItem();
            CV cv = mainController.checkCV(title);
            if(cv==null){
                return;
            }
            Scene scene5 = this.getMainController().getSceneList().get(4);
            this.getMainController().getAddStage().setScene(scene5);
            Stage stage = this.getMainController().getAddStage();
            stage.show();
            // this is the main stage and when you clickled edit screen it will be hidden

        }
        //If the date is not null and the education field is empty
        else if (certificateDate.getValue() != null && educationTF.getText().isEmpty() ){
            AlertMethod ("Please fill in education name field and try again");
        }
        else if (educationTF.getText().isEmpty() && !companyTF.getText().isEmpty() ) {
            AlertMethod("Please fill in education name field and try again");
        }
        else if (educationTF.getText().isEmpty() && !motherTF.getText().isEmpty() ) {
            AlertMethod("Please fill in education name field and try again");
        }
        else if (educationTF.getText().isEmpty() && !otherTF.getText().isEmpty() ) {
            AlertMethod("Please fill in education name field and try again");
        }
        else if (educationTF.getText().isEmpty() && !softTF.getText().isEmpty() ) {
            AlertMethod("Please fill in education name field and try again");
        }
        else if (educationTF.getText().isEmpty() && !hardTF.getText().isEmpty() ) {
            AlertMethod("Please fill in education name field and try again");
        }
        else if (educationTF.getText().isEmpty() && !titleTF.getText().isEmpty() ) {
            AlertMethod("Please fill in education name field and try again");
        }
        //If the date is not null and the education field is not empty
        else if (certificateDate.getValue() != null && !educationTF.getText().isEmpty()){
            allInfo(true);
        }
        //If the date is null and the education field is not empty
        else if (certificateDate.getValue() == null && !educationTF.getText().isEmpty()){
            allInfo(false);
        }
        else if (certificateDate.getValue() != null) {
            try {
                allInfo(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (certificateDate.getValue() == null) {
            try {
                allInfo(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
