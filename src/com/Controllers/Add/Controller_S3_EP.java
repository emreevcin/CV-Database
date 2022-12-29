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

public class Controller_S3_EP implements Initializable {

    private MainController mainController ;
    private Main main ;
    private Scene scene ;

    @FXML
    private Button backButton,nextButton;

    @FXML
    private TextField departmentTF,gpaTF,institutionTF,titleTF;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private DatePicker fromDateE,fromDateP,toDateE,toDateP;

    @FXML
    private CheckBox isOngoingE,isOngoingP;





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

    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }

    //All things that need to be done when the scene is loaded
    public void allInfo(boolean IncludefromE,boolean IncludetoE,boolean IncludefromP,boolean IncludetoP){
        this.getMainController().getInformation().put("institution", institutionTF.getText());
        this.getMainController().getInformation().put("department", departmentTF.getText());
        this.getMainController().getInformation().put("gpa", gpaTF.getText());
        if(IncludefromE){
            this.getMainController().getInformation().put("fromDateE", fromDateE.getValue().toString());
        }
        else if(IncludetoE){
            this.getMainController().getInformation().put("toDateE", toDateE.getValue().toString());
        }
        else if(IncludefromP){
            this.getMainController().getInformation().put("fromDateP", fromDateP.getValue().toString());
        }
        else if(IncludetoP){
            this.getMainController().getInformation().put("toDateP", toDateP.getValue().toString());
        }
        this.getMainController().getInformation().put("ongoingE", isOngoingE.getText());
        this.getMainController().getInformation().put("titleP", titleTF.getText());
        this.getMainController().getInformation().put("ongoingP", isOngoingP.getText());
        this.getMainController().getInformation().put("descriptionP", descriptionTA.getText());
        Scene scene4 = this.getMainController().getSceneList().get(3);
        this.getMainController().getAddStage().setScene(scene4);
    }


    public void next(){
        if(toDateE.getValue() != null && isOngoingE.isSelected()){
            AlertMethod("You can't fill in 'to' field and 'OnGoing' checkbox at the same time");
        }
        else if(toDateP.getValue() != null && isOngoingP.isSelected()){
            AlertMethod("You can't fill in 'to' field and 'OnGoing' checkbox in project section at the same time");
        }
        else if(fromDateE.getValue() == null && toDateE.getValue() != null){
            AlertMethod("You can't fill in 'to' field without filling in 'from' field");
        }
        else if(fromDateP.getValue() == null && toDateP.getValue() != null){
            AlertMethod("You can't fill in 'to' field without filling in 'from' field in project section");
        }
        else if(fromDateE.getValue() != null && toDateE.getValue() == null && !isOngoingE.isSelected()){
            AlertMethod("You can't fill in 'from' field without filling in 'to' field or 'OnGoing' checkbox");
        }
        else if(fromDateP.getValue() != null && toDateP.getValue() == null && !isOngoingP.isSelected()){
            AlertMethod("You can't fill in 'from' field without filling in 'to' field or 'OnGoing' checkbox");
        }
        else if(fromDateE.getValue() != null && toDateE.getValue() != null && fromDateE.getValue().isAfter(toDateE.getValue())){
            AlertMethod("You can't fill in 'from' field after 'to' field");
        }
        else if(fromDateP.getValue() != null && toDateP.getValue() != null && fromDateP.getValue().isAfter(toDateP.getValue())){
            AlertMethod("You can't fill in 'from' field after 'to' field");
        }
        else if(fromDateE.getValue() != null && toDateE.getValue() != null && fromDateE.getValue().isEqual(toDateE.getValue())){
            AlertMethod("You can't fill in 'from' field equal to 'to' field");
        }
        else if(fromDateP.getValue() != null && toDateP.getValue() != null && fromDateP.getValue().isEqual(toDateP.getValue())){
            AlertMethod("You can't fill in 'from' field equal to 'to' field");
        }
        else if(fromDateE.getValue() != null && toDateE.getValue() != null && fromDateE.getValue().isBefore(toDateE.getValue())){
            allInfo(true,false,false,false);
        }
        else if (fromDateE.getValue()==null && isOngoingE.isSelected() && toDateE.getValue() == null){
            AlertMethod("You can't check the 'OnGoing' checkbox without filling in the from field");
        }
        else if(institutionTF.getText().isEmpty() && !departmentTF.getText().isEmpty() ||((!institutionTF.getText().isEmpty())&& departmentTF.getText().isEmpty()) ){
            AlertMethod("If you want to add an education, fill institution and department fields");
        }
        else if (!institutionTF.getText().isEmpty() && !departmentTF.getText().isEmpty() && gpaTF.getText().isEmpty() ){
            AlertMethod("If you want to add an education, fill gpa field");

        }
        else if (!gpaTF.getText().isEmpty() && !gpaTF.getText().matches("^[0-4]\\.[0-9][0-9]$") && !gpaTF.getText().matches("^[0-4]\\.[0-9]$") && !gpaTF.getText().matches("^[0-4]$"))
        {
            AlertMethod("Write GPA in right format (3.83,2.1)");
        }
        else if (fromDateE.getValue()==null && toDateE.getValue() != null){
            AlertMethod("You can't fill in the 'to' field without filling in the 'from' field");
        }
        else if (toDateE.getValue() != null && fromDateE.getValue() != null && toDateE.getValue().isBefore(fromDateE.getValue())){
            AlertMethod("The 'to' date can't be before the 'from' date");
        }
        else if (toDateP.getValue() != null && isOngoingP.isSelected()){
            AlertMethod("You can't fill in 'to' field and 'OnGoing' checkbox at the same time in project section");
        }
        else if (fromDateP.getValue()==null && isOngoingP.isSelected() && toDateP.getValue() == null){
            AlertMethod("You can't check the 'OnGoing' checkbox without filling in the from field in project section");
        }
        else if (fromDateP.getValue()==null && toDateP.getValue() != null){
            AlertMethod("You can't fill in the 'to' field without filling in the 'from' field in project section");
        }
        else if (toDateP.getValue() != null && fromDateP.getValue() != null && toDateP.getValue().isBefore(fromDateP.getValue())) {
            AlertMethod("The 'to' date can't be before the 'from' date in project section");
        }
        else if (toDateP.getValue() != null && fromDateP.getValue() != null && toDateP.getValue().isEqual(fromDateP.getValue())) {
            AlertMethod("The 'to' date can't be equal to the 'from' date in project section");
        }
        else if (toDateE.getValue() != null && fromDateE.getValue() != null && toDateE.getValue().isEqual(fromDateE.getValue())) {
            AlertMethod("The 'to' date can't be equal to the 'from' date");
        }
        else if (!descriptionTA.getText().isEmpty() && titleTF.getText().isEmpty()){
            AlertMethod("You can't leave the 'Title' field empty");
        }
        else if ((!descriptionTA.getText().isEmpty() && (institutionTF.getText().isEmpty() || departmentTF.getText().isEmpty() || gpaTF.getText().isEmpty()))){
            AlertMethod("You can't leave the 'Institution', 'Department' or 'GPA' field empty if you want an education");
        }
        else if (!titleTF.getText().isEmpty() && (institutionTF.getText().isEmpty() || departmentTF.getText().isEmpty() || gpaTF.getText().isEmpty())){
            AlertMethod("You can't leave the 'Institution', 'Department' or 'GPA' field empty if you want an education");
        }
        else if ((fromDateP.getValue() != null || toDateP.getValue() !=null) && (institutionTF.getText().isEmpty() || departmentTF.getText().isEmpty() || gpaTF.getText().isEmpty())){
            AlertMethod("You can't leave the 'Institution', 'Department' or 'GPA' field empty if you want an education");
        }
        else if ((fromDateP.getValue() != null || toDateP.getValue() !=null) && titleTF.getText().isEmpty()){
            AlertMethod("You can't leave the 'Title' field empty if you want to add a project");
        }
        //date picker E's are filled
        else if(fromDateE.getValue() != null && toDateE.getValue() != null){
            try{
                allInfo(true,true,false,false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //date picker E's are not filled
        else if(fromDateE.getValue() == null && toDateE.getValue() == null){
            try{
                allInfo(false,false,false,false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //date picker P's are filled
        else if(fromDateP.getValue() != null && toDateP.getValue() != null){
            try{
                allInfo(false,false,true,true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //date picker P's are not filled
        else if(fromDateP.getValue() == null && toDateP.getValue() == null){
            try{
                allInfo(false,false,false,false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //date picker E's are filled and P's are not filled
        else if(fromDateE.getValue() != null && toDateE.getValue() != null && fromDateP.getValue() == null && toDateP.getValue() == null){
            try{
                allInfo(true,true,false,false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //date picker E's are not filled and P's are filled
        else if(fromDateE.getValue() == null && toDateE.getValue() == null && fromDateP.getValue() != null && toDateP.getValue() != null){
            try{
                allInfo(false,false,true,true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //date picker E's are filled and P's are filled
        else if(fromDateE.getValue() != null && toDateE.getValue() != null && fromDateP.getValue() != null && toDateP.getValue() != null){
            try{
                allInfo(true,true,true,true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //date picker E's are not filled and P's are not filled
        else if(fromDateE.getValue() == null && toDateE.getValue() == null && fromDateP.getValue() == null && toDateP.getValue() == null){
            try{
                allInfo(false,false,false,false);
            }catch (Exception e){
                e.printStackTrace();
            }
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
