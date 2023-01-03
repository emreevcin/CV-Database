package com.Controllers.Add;

import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller_S3_EP implements Initializable {

    private MainController mainController ;
    private Scene scene ;

    private ArrayList<HashMap<String,String>> educationData = new ArrayList<>();
    private ArrayList<HashMap<String,String>>projectsData = new ArrayList<>();


    @FXML
    private TextField departmentTF,gpaTF,institutionTF,titleTF;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private DatePicker fromDateE,fromDateP,toDateE,toDateP;

    @FXML
    private CheckBox isOngoingE,isOngoingP;

    @FXML
    private ComboBox<Integer> educationsCB, projectsCB;


    public void setData(ArrayList<HashMap<String, String>> educationsTable, ArrayList<HashMap<String, String>> projectsTable) {
        this.educationData = educationsTable ;
        this.projectsData = projectsTable ;
        loadEducationData(-1);
        loadProjectData(-1);
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

    public void next(){
        Scene scene4 = this.getMainController().getController_s4_cs().getScene();
        this.getMainController().getAddStage().setScene(scene4);
    }

    public void back(){
        Scene scene2 = this.getMainController().getController_s2_we().getScene();
        this.getMainController().getAddStage().setScene(scene2);
    }

    public ArrayList<HashMap<String, String>> getEducationData() {
        return educationData;
    }

    public ArrayList<HashMap<String, String>> getProjectsData() {
        return projectsData;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void loadEducationData(int index) {
        HashMap<String,String> education ;
        if(index ==-1)
             education= educationData.get(0);
        else
             education= educationData.get(index);

        institutionTF.setText(education.get("institution"));
        departmentTF.setText(education.get("department"));
        gpaTF.setText(education.get("gpa"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDateE_String = education.get("starting_date");
        String toDateE_String = education.get("ending_date");
        if(fromDateE_String != null || toDateE_String != null){
            LocalDate fromE = LocalDate.parse(fromDateE_String, formatter);
            fromDateE.setValue(fromE);
            LocalDate toE = LocalDate.parse(toDateE_String, formatter);
            toDateE.setValue(toE);
        }
        if(education.get("ongoing")==null){
            isOngoingE.setSelected(false);
        }
        else
            isOngoingE.setSelected(education.get("ongoing").equals("Ongoing"));
    }
    private void loadProjectData(int index){
        HashMap<String,String> project ;
        if(index ==-1)
            project = projectsData.get(0);
        else
            project = projectsData.get(index);
        titleTF.setText(project.get("title"));
        String fromDateP_String = project.get("starting_date");
        String toDateP_String = project.get("ending_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(fromDateP_String != null || toDateP_String != null){
            LocalDate fromP = LocalDate.parse(fromDateP_String, formatter);
            fromDateP.setValue(fromP);
            LocalDate toP = LocalDate.parse(toDateP_String, formatter);
            toDateP.setValue(toP);
        }
        descriptionTA.setText(project.get("description"));
        if(project.get("ongoing")==null){
            isOngoingP.setSelected(false);
        }
        else
            isOngoingP.setSelected(project.get("ongoing").equals("Ongoing"));
    }

    @FXML
    void addEducation(){
        HashMap<String,String> education = new HashMap<>();

        education.put("institution",institutionTF.getText());
        education.put("department",departmentTF.getText());
        education.put("gpa",gpaTF.getText());

        if(fromDateE.getValue()==null){
            education.put("starting_date",null);
        }
        else{
            education.put("starting_date",fromDateE.getValue().toString());
        }
        if(toDateE.getValue()==null){
            education.put("ending_date",null);
        }
        else {
            education.put("ending_date", toDateE.getValue().toString());
        }
        education.put("ongoing", String.valueOf(isOngoingE));

        institutionTF.setText("");
        departmentTF.setText("");
        gpaTF.setText("");
        fromDateE.setValue(null);
        toDateE.setValue(null);
        isOngoingE.setSelected(false);

        int i = educationsCB.getItems().size()+1;
        educationsCB.getItems().add(i);

        educationData.add(education);

    }
    @FXML
    void addProject(){
        HashMap<String,String> project = new HashMap<>();


        project.put("description",descriptionTA.getText());
        project.put("title",titleTF.getText());
        if(fromDateP.getValue()==null){
            project.put("starting_date",null);
        }
        else{
            project.put("starting_date",fromDateP.getValue().toString());
        }
        if(toDateP.getValue()==null){
            project.put("ending_date",null);
        }
        else {
            project.put("ending_date", toDateP.getValue().toString());
        }
        project.put("ongoing",String.valueOf(isOngoingP));

        projectsData.add(project);

        int i = projectsCB.getItems().size()+1;
        projectsCB.getItems().add(i);
    }
}
