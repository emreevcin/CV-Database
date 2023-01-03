package com.Controllers.Add;

import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller_S4_CS implements Initializable {
    private MainController mainController ;
    private Scene scene ;

    private ArrayList<HashMap<String,String>> certificatesData = new ArrayList<>();
    private ArrayList<HashMap<String,String>> skillsData = new ArrayList<>();

    @FXML
    private DatePicker certificateDate;

    @FXML
    private TextField companyTF,educationTF,hardTF,
                      motherTF,otherTF,softTF,titleTF;
    @FXML
    private ComboBox<Integer> certificatesCB ;

    public void setData(ArrayList<HashMap<String, String>> certificatesTable, ArrayList<HashMap<String, String>> skillsTable) {
        this.certificatesData = certificatesTable ;
        this.skillsData = skillsTable ;
        loadCertificates(-1);
        loadSkills();
    }
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

    public void init (MainController mainController){
        setMainController(mainController);
    }

    public ArrayList<HashMap<String, String>> getCertificatesData() {
        return certificatesData;
    }

    public ArrayList<HashMap<String, String>> getSkillsData() {
        return skillsData;
    }

    private void AlertMethod(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText("Please fill in education name field and try again");
        alert.show();
    }

    public void next() {
        //If the date is not null and the education field is empty

        HashMap<String,String> skills = new HashMap<>();

        skills.put("mother_tongue",motherTF.getText());
        skills.put("other_language", otherTF.getText());
        skills.put("soft_skills",softTF.getText());
        skills.put("hard_skills",hardTF.getText());
        skills.put("hobbies_interests",titleTF.getText());

        if(skillsData.size()==0){
           skillsData.add(0,skills);
        }
        else
            skillsData.set(0,skills);

        Scene scene5 = this.getMainController().getController_s5_ro().getScene();
        this.getMainController().getAddStage().setScene(scene5);

    }
    public void back(){
        Scene scene3 = this.getMainController().getController_s3_ep().getScene();
        this.getMainController().getAddStage().setScene(scene3);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void loadCertificates(int index) {
        HashMap<String,String> certificate ;
        if(index ==-1)
            certificate= certificatesData.get(0);
        else
            certificate= certificatesData.get(index);

        educationTF.setText(certificate.get("education_name"));
        companyTF.setText(certificate.get("company"));
        String verifiedDate = certificate.get("verified_date");
        if(verifiedDate!= null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate verified = LocalDate.parse(verifiedDate, formatter);
            certificateDate.setValue(verified);
        }
    }
    private void loadSkills(){
        HashMap<String,String> skills= skillsData.get(0) ;
        motherTF.setText(skills.get("mother_tongue"));
        otherTF.setText(skills.get("other_language"));
        softTF.setText(skills.get("soft_skills"));
        hardTF.setText(skills.get("hard_skills"));
        titleTF.setText(skills.get("hobbies_interests"));
    }

    public void addCertificate(){
        HashMap<String,String> certificate = new HashMap<>();

        certificate.put("education_name",educationTF.getText());
        certificate.put("company",companyTF.getText());
        if(certificateDate.getValue()==null){
            certificate.put("verified_date",null);
        }
        else {
            certificate.put("verified_date",certificateDate.getValue().toString());
        }
        certificatesData.add(certificate);

        int i = certificatesCB.getItems().size()+1;
        certificatesCB.getItems().add(i);

    }
}
