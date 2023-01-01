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
    private Button backButton,nextButton;

    @FXML
    private TextField cityTF,countryTF,employerTF,occupationTF;

    @FXML
    private TextArea explanationTA;

    @FXML
    private DatePicker fromDate,toDate;

    @FXML
    private CheckBox isOngoing;



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

    //Alert method
    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }

    @FXML
    public void next(){
        if(mainController.isEditFunctionRunned()){
            Stage stage = new Stage();
            stage.setScene(mainController.getSceneList().get(2));
            stage.show();
            // this is the main stage and when you clickled edit screen it will be hidden
            Stage weView = (Stage) nextButton.getScene().getWindow();
            weView.hide();
        }

        else if(toDate.getValue() !=null && isOngoing.isSelected()){
            AlertMethod("You can't fill in 'to' field and 'OnGoing' checkbox at the same time");}
        else if(fromDate.getValue() ==null && isOngoing.isSelected() && toDate.getValue() == null){
            AlertMethod("You can't check the 'OnGoing' checkbox without filling in the from field");
        }
        else if(toDate.getValue() != null &&toDate.getValue().isBefore(fromDate.getValue())){//if I implement this, it gives errors
            AlertMethod("The 'to' date can't be before the 'from' date");
        }
        else if(fromDate.getValue() ==null && toDate.getValue() != null){
            AlertMethod("You can't fill in 'to Date' field without filling in the from field");
        }
        else if(fromDate.getValue() !=null && toDate.getValue() ==null && !isOngoing.isSelected()){
            AlertMethod("You can't leave 'to' field empty without checking the 'OnGoing' checkbox");
        }
        else if(fromDate.getValue() !=null && toDate.getValue() !=null && fromDate.getValue().isAfter(toDate.getValue())){
            AlertMethod("The 'from' date can't be after the 'to' date");
        }
        else if(occupationTF.getText().isEmpty() &&!employerTF.getText().isEmpty()){
            AlertMethod("You can't leave the occupation field empty");
        }
        else if (occupationTF.getText().isEmpty() && !cityTF.getText().isEmpty()){
            AlertMethod("You can't leave the occupation field empty");
        }
        else if (occupationTF.getText().isEmpty() && !countryTF.getText().isEmpty()){
            AlertMethod("You can't leave the occupation field empty");
        }
        else if((!countryTF.getText().isEmpty() && cityTF.getText().isEmpty() && occupationTF.getText().isEmpty())){
            AlertMethod("You can't leave the occupation and city fields empty if you fill in the country field");
        }
        else if (!cityTF.getText().isEmpty() && countryTF.getText().isEmpty()&& occupationTF.getText().isEmpty()){
            AlertMethod("You can't leave the occupation and country fields empty if you fill in the city field");
        }
        else if (!explanationTA.getText().isEmpty() && occupationTF.getText().isEmpty()){
            AlertMethod("You can't leave the occupation field empty if you fill in the explanation field");
        }

        //date picker fields filled
        else if (fromDate.getValue() != null && toDate.getValue() != null){
            try{
                this.getMainController().getInformation().put("fromWE", fromDate.getValue().toString());
                this.getMainController().getInformation().put("toWE", toDate.getValue().toString());
                this.getMainController().getInformation().put("occupation", occupationTF.getText());
                this.getMainController().getInformation().put("employer", employerTF.getText());
                this.getMainController().getInformation().put("cityWE", cityTF.getText());
                this.getMainController().getInformation().put("countryWE", countryTF.getText());
                this.getMainController().getInformation().put("ongoingWE", String.valueOf(isOngoing.isSelected()));
                this.getMainController().getInformation().put("explanationWE", explanationTA.getText());
                Scene scene3 = this.getMainController().getSceneList().get(2);
                this.getMainController().getAddStage().setScene(scene3);

            }   catch (Exception e){e.printStackTrace();}}

        //date picker fields is not filled in
        else if(fromDate.getValue() == null || toDate.getValue() == null) {
            try{
                this.getMainController().getInformation().put("occupation", occupationTF.getText());
                this.getMainController().getInformation().put("employer", employerTF.getText());
                this.getMainController().getInformation().put("cityWE", cityTF.getText());
                this.getMainController().getInformation().put("countryWE", countryTF.getText());
                this.getMainController().getInformation().put("ongoingWE", String.valueOf(isOngoing.isSelected()));
                this.getMainController().getInformation().put("explanationWE", explanationTA.getText());
                Scene scene3 = this.getMainController().getSceneList().get(2);
                this.getMainController().getAddStage().setScene(scene3);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            //Work is going on and fromDate is filled in
        }else if (fromDate.getValue() != null && toDate.getValue() ==null && isOngoing.isSelected()){
            try{
                this.getMainController().getInformation().put("fromWE", fromDate.getValue().toString());
                this.getMainController().getInformation().put("occupation", occupationTF.getText());
                this.getMainController().getInformation().put("employer", employerTF.getText());
                this.getMainController().getInformation().put("cityWE", cityTF.getText());
                this.getMainController().getInformation().put("countryWE", countryTF.getText());
                this.getMainController().getInformation().put("ongoingWE", String.valueOf(isOngoing.isSelected()));
                this.getMainController().getInformation().put("explanationWE", explanationTA.getText());
                Scene scene3 = this.getMainController().getSceneList().get(2);
                this.getMainController().getAddStage().setScene(scene3);

            }   catch (Exception e){e.printStackTrace();}

        }

        else {
            AlertMethod("Something went wrong");
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

