package com.Controllers.Add;

import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller_S1_PI implements Initializable {

    private MainController mainController ;

    private ArrayList<HashMap<String,String>> data = new ArrayList<>() ;

    private HashMap<String,String> personalInfo =new HashMap<>();

    private Scene scene ;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea careerObjectiveTA;

    @FXML
    private TextField cityTF,countryTF,emailTF,firstNameTF,lastNameTF
            ,phoneTF,titleTF,tagTF;


    public Controller_S1_PI() {
        this.data.add(personalInfo) ;
    }

    public void setData(ArrayList<HashMap<String, String>> data) {
        this.data = data;
        loadData();
    }

    public ArrayList<HashMap<String, String>> getData() {
        return data;
    }

    @FXML
    public void next() {
        if(firstNameTF.getText().isEmpty() && lastNameTF.getText().isEmpty()) {
            AlertMethod("Please enter the first name and last name and try again");
        }
        else if(!emailTF.getText().isEmpty() && !emailTF.getText().contains("@") && !emailTF.getText().contains(".")) {
            AlertMethod("Please enter the email correctly and try again");
        }
        else if(!phoneTF.getText().isEmpty() && phoneTF.getText().length() != 10) {
            AlertMethod("Please enter the phone number correctly and try again");
        }
        else if (!firstNameTF.getText().isEmpty() && lastNameTF.getText().isEmpty()){
            AlertMethod("Also you need to enter last name");
        }
        else{
            try{
                allInfo();
                Scene scene2 = this.getMainController().getController_s2_we().getScene();
                this.getMainController().getAddStage().setScene(scene2);
            } catch (Exception e){e.printStackTrace();}
        }


    }

    @FXML
    public void cancel() {

        // primary stage should come back to the screen
        Stage stage1 = (Stage) mainController.getCvList().getScene().getWindow();
        stage1.show();
        Stage stage2 = (Stage) cancelButton.getScene().getWindow();
        stage2.close();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }

    private void allInfo(){
        personalInfo.put("first_name", firstNameTF.getText());
        personalInfo.put("last_name", lastNameTF.getText());
        personalInfo.put("tag", tagTF.getText());
        personalInfo.put("title", titleTF.getText());
        personalInfo.put("career_objective", careerObjectiveTA.getText());
        personalInfo.put("email", emailTF.getText());
        personalInfo.put("phone", phoneTF.getText());
        personalInfo.put("city", cityTF.getText());
        personalInfo.put("country", countryTF.getText());

    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void init (MainController mainController){
        setMainController(mainController);
    }


    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    private void loadData() {
        if(data== null || data.size()==0){
            return;
        }
        HashMap<String,String> personalInfo = data.get(0);

        if(personalInfo== null)
            return;
        firstNameTF.setText(personalInfo.get("first_name"));
        lastNameTF.setText(personalInfo.get("last_name"));
        tagTF.setText(personalInfo.get("tag"));
        titleTF.setText(personalInfo.get("title"));
        careerObjectiveTA.setText(personalInfo.get("career_objective"));
        emailTF.setText(personalInfo.get("email"));
        phoneTF.setText(personalInfo.get("phone"));
        cityTF.setText(personalInfo.get("city"));
        countryTF.setText(personalInfo.get("country"));
    }


}