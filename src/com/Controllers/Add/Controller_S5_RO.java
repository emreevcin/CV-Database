package com.Controllers.Add;

import com.Classes.CV;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller_S5_RO implements Initializable {

    private MainController mainController ;
    private Scene scene ;

    private ArrayList<HashMap<String,String>> recommendationsData = new ArrayList<>();
    private ArrayList<HashMap<String,String>> other_informationData = new ArrayList<>();

    @FXML
    private Button addCVButton;
    @FXML
    private TextArea descriptionOTA,descriptionRTA;
    @FXML
    private TextField emailTF,nameTF,otherTF,phoneTF,roleTF,titleTF;

    @FXML
    private ComboBox<Integer> recommendationCB;

    public void setData(ArrayList<HashMap<String, String>> recommendationsTable, ArrayList<HashMap<String, String>> other_informationTable) {
        this.recommendationsData = recommendationsTable;
        this.other_informationData = other_informationTable;
        loadRecommendation(-1);
        loadOther();
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

    public void back(){
        Scene scene4 = this.getMainController().getController_s4_cs().getScene();
        this.getMainController().getAddStage().setScene(scene4);
    }

    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }

    private String attachedCvFolderPath = "./src/com/resources/attachedCvs";
    private File cvDocFolder = new File(attachedCvFolderPath);
    private File chosenPDF ;

    @FXML
    public void submit()  {
        try {
            if(nameTF.getText().isEmpty() && !descriptionRTA.getText().isEmpty()) {
                AlertMethod("Please enter the name");

            }
            else if (nameTF.getText().isEmpty() && !roleTF.getText().isEmpty()){
                AlertMethod("Please enter the name");
            }
            else if(nameTF.getText().isEmpty() && !emailTF.getText().isEmpty() && !phoneTF.getText().isEmpty()) {
                AlertMethod("Please enter the name");

            }
            else if(nameTF.getText().isEmpty() && !descriptionOTA.getText().isEmpty()){
                AlertMethod("Please enter the name");

            }
            else if (nameTF.getText().isEmpty() && !titleTF.getText().isEmpty()){
                AlertMethod("Please enter the name");
            }
            else if (nameTF.getText().isEmpty() && !otherTF.getText().isEmpty()){
                AlertMethod("Please enter the name");
            }

            else if(!emailTF.getText().isEmpty() && !emailTF.getText().contains("@") && !emailTF.getText().contains(".")) {
                AlertMethod("Please enter the email correctly and try again");
            }
            else if (!phoneTF.getText().isEmpty() && !phoneTF.getText().matches("[0-9]+")){
                AlertMethod("Please enter a valid phone number");
            }
            else if (!phoneTF.getText().isEmpty() && phoneTF.getText().length() != 10){
                AlertMethod("Please enter a valid phone number");
            }


            HashMap<String,String> other = new HashMap<>();

            other.put("header",otherTF.getText());
            other.put("title",titleTF.getText());
            other.put("description",descriptionOTA.getText());


            if(other_informationData.size()==0){
                other_informationData.add(0,other);
            }
            else
                other_informationData.set(0,other);

            Scene scene1= this.getMainController().getController_s1_pi().getScene();
            Scene scene2= this.getMainController().getController_s2_we().getScene();
            Scene scene3= this.getMainController().getController_s3_ep().getScene();
            Scene scene4= this.getMainController().getController_s4_cs().getScene();
            Scene scene5= this.getMainController().getController_s5_ro().getScene();

            CV cv = new CV(scene1,scene2,scene3,scene4,scene5);

            String name = this.getMainController().getController_s1_pi().getData().get(0).get("first_name");
            String surname = this.getMainController().getController_s1_pi().getData().get(0).get("last_name");
            this.getMainController().getD().addCV( name ,surname );
            int cvID = this.getMainController().getD().getCVID();

            for (int i = 0; i < 5; i++) {
                if(i==0){
                    ArrayList<HashMap<String,String>> data = this.getMainController().getController_s1_pi().getData();
                    for (int j = 0; j <data.size() ; j++) {
                        HashMap<String, String> person = data.get(i);
                        this.getMainController().getD().addPerson(cvID,
                                person.get("first_name"),
                                person.get("last_name"),
                                person.get("tag"),
                                person.get("title"),
                                person.get("career_objective"),
                                person.get("email"),
                                person.get("phone"),
                                person.get("city"),
                                person.get("country"));
                    }
                }
                if(i==1){
                    ArrayList<HashMap<String,String>> data = this.getMainController().getController_s2_we().getData();
                    for (HashMap<String, String> work : data) {
                        this.getMainController().getD().addWork(cvID,
                                work.get("occupation"),
                                work.get("employer"),
                                work.get("city"),
                                work.get("country"),
                                work.get("starting_date"),
                                work.get("ending_date"),
                                work.get("ongoing"),
                                work.get("activities_responsibilities"));
                    }
                }
                if(i==2){
                    ArrayList<HashMap<String,String>> educationData = this.getMainController().getController_s3_ep().getEducationData();
                    for (HashMap<String, String> education : educationData) {
                        this.getMainController().getD().addEducation(cvID,
                                education.get("institution"),
                                education.get("department"),
                                education.get("gpa"),
                                education.get("starting_date"),
                                education.get("ending_date"),
                                education.get("ongoing"));

                    }
                    ArrayList<HashMap<String,String>> projectsData = this.getMainController().getController_s3_ep().getProjectsData();
                    for (HashMap<String, String> project : projectsData) {
                        this.getMainController().getD().addProjects(cvID,
                                project.get("title"),
                                project.get("starting_date"),
                                project.get("ending_date"),
                                project.get("ongoing"),
                                project.get("description"));

                    }

                }
                if(i==3){
                    ArrayList<HashMap<String,String>> certificatesData = this.getMainController().getController_s4_cs().getCertificatesData();
                    for (HashMap<String, String> certificate : certificatesData) {
                        this.getMainController().getD().addCertificates(cvID,
                                certificate.get("education_name"),
                                certificate.get("company"),
                                certificate.get("verified_date"));

                    }
                    ArrayList<HashMap<String,String>> skillsData = this.getMainController().getController_s4_cs().getSkillsData();
                    for (HashMap<String, String> skills : skillsData) {
                        this.getMainController().getD().addSkills(cvID,
                                skills.get("mother_tongue"),
                                skills.get("other_languages"),
                                skills.get("soft_skills"),
                                skills.get("hard_skills"),
                                skills.get("hobbies_interests"));
                    }

                }
                if(i==4){
                    ArrayList<HashMap<String,String>> recommendationsData = this.recommendationsData;
                    for (HashMap<String, String> recommendation : recommendationsData) {
                        this.getMainController().getD().addRecommendations(cvID,
                                recommendation.get("name_"),
                                recommendation.get("role_"),
                                recommendation.get("email"),
                                recommendation.get("description"),
                                recommendation.get("phone"));
                    }
                    ArrayList<HashMap<String,String>> other_informationData = this.other_informationData;
                    for (HashMap<String, String> other_information : other_informationData) {
                        this.getMainController().getD().addOthers(cvID,
                                other_information.get("header"),
                                other_information.get("title"),
                                other_information.get("description"));
                    }


                }
            }

            String title = name+"_"+surname ;
            cv.setTitle(title);

            String cvTag = this.getMainController().getController_s1_pi().getData().get(0).get("tag");
            cv.setTag(cvTag);

            this.getMainController().getCvList().getItems().add(cv.getTitle());
            this.getMainController().getCvMap().put(cv.getTitle(), cv);

            this.getMainController().setCvCounter(this.getMainController().getCvList().getItems().size());
            this.getMainController().getCvNumberLabel().setText("CV Number: " + this.getMainController().getCvCounter());

            //when clicked submit button the stage will be closed
            Stage stage = (Stage) mainController.getCvList().getScene().getWindow();
            stage.show();
            //last stage will be close when you click submit button
            Stage stage2 = (Stage) cv.getScene5().getWindow();
            stage2.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @FXML
    void loadCV() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
        FileChooser.ExtensionFilter extensionFilterDOCX = new FileChooser.ExtensionFilter("DOCX files (*.docx)", "*.DOCX");

        fileChooser.getExtensionFilters().addAll(extensionFilterPDF, extensionFilterDOCX);

        chosenPDF = fileChooser.showOpenDialog(null);
        if(chosenPDF != null){
            addCVButton.setDisable(true);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadRecommendation(int index){
        HashMap<String,String> recommendation ;

        if(index ==-1)
            recommendation= recommendationsData.get(0);
        else
            recommendation= recommendationsData.get(index);

        nameTF.setText(recommendation.get("name_"));
        roleTF.setText(recommendation.get("role_"));
        emailTF.setText(recommendation.get("email"));
        phoneTF.setText(recommendation.get("phone"));
        descriptionRTA.setText(recommendation.get("description"));

    }
    public void loadOther() {
        HashMap<String,String> other = other_informationData.get(0);

        otherTF.setText(other.get("header"));
        titleTF.setText(other.get("title"));
        descriptionOTA.setText(other.get("description"));

    }
    public void addRecommendation(){
        HashMap<String,String> recommendation = new HashMap<>();

        recommendation.put("name_",nameTF.getText());
        recommendation.put("role_",roleTF.getText());
        recommendation.put("email", emailTF.getText());
        recommendation.put("phone",phoneTF.getText());
        recommendation.put("description", descriptionRTA.getText());

        recommendationsData.add(recommendation);

        int i = recommendationCB.getItems().size()+1;
        recommendationCB.getItems().add(i);



    }
}
