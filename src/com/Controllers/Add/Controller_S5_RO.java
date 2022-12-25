package com.Controllers.Add;

import com.Classes.CV;
import com.Classes.Main;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_S5_RO implements Initializable {

    private MainController mainController ;
    private Main main ;
    private Scene scene;

    @FXML
    private Button backButton;

    @FXML
    private Button addCVButton;

    @FXML
    private TextArea descriptionOTA;

    @FXML
    private TextArea descriptionRTA;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField otherTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField roleTF;

    @FXML
    private Button saveButton;

    @FXML
    private TextField titleTF;

    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

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
        this.getMainController().getInformation().put("nameR", nameTF.getText());
        this.getMainController().getInformation().put("roleR", roleTF.getText());
        this.getMainController().getInformation().put("emailR", emailTF.getText());
        this.getMainController().getInformation().put("phoneR", phoneTF.getText());
        this.getMainController().getInformation().put("descriptionR", descriptionRTA.getText());
        this.getMainController().getInformation().put("otherAddition", otherTF.getText());
        this.getMainController().getInformation().put("titleAddition", titleTF.getText());
        this.getMainController().getInformation().put("descriptionAddition", descriptionOTA.getText());
        this.getMainController().getInformation().put("originalFile", bos.toString(StandardCharsets.UTF_8));


        this.getMainController().getD().addCV(this.getMainController().getInformation().get("firstName"), this.getMainController().getInformation().get("lastName"));
        int cvID = this.getMainController().getD().getCVID();

        this.getMainController().getD().addPerson(cvID,
                this.getMainController().getInformation().get("photo"),
                this.getMainController().getInformation().get("firstName"),
                this.getMainController().getInformation().get("lastName"),
                this.getMainController().getInformation().get("titlePI"),
                this.getMainController().getInformation().get("careerObjective"),
                this.getMainController().getInformation().get("emailPI"),
                this.getMainController().getInformation().get("phonePI"),
                this.getMainController().getInformation().get("cityPI"),
                this.getMainController().getInformation().get("countryPI"));

        this.getMainController().getD().addWork(cvID,
                this.getMainController().getInformation().get("occupation"),
                this.getMainController().getInformation().get("employer"),
                this.getMainController().getInformation().get("cityWE"),
                this.getMainController().getInformation().get("countryWE"),
                this.getMainController().getInformation().get("fromWE"),
                this.getMainController().getInformation().get("toWE"),
                this.getMainController().getInformation().get("ongoingWE"),
                this.getMainController().getInformation().get("explanationWE"));

        this.getMainController().getD().addEducation(cvID,
                this.getMainController().getInformation().get("institution"),
                this.getMainController().getInformation().get("department"),
                this.getMainController().getInformation().get("gpa"),
                this.getMainController().getInformation().get("fromE"),
                this.getMainController().getInformation().get("toE"),
                this.getMainController().getInformation().get("ongoingE"));

        this.getMainController().getD().addProjects(cvID,
                this.getMainController().getInformation().get("titleP"),
                this.getMainController().getInformation().get("fromP"),
                this.getMainController().getInformation().get("toD"),
                this.getMainController().getInformation().get("ongoingP"),
                this.getMainController().getInformation().get("descriptionP"));

        this.getMainController().getD().addCertificates(cvID,
                this.getMainController().getInformation().get("education"),
                this.getMainController().getInformation().get("company"),
                this.getMainController().getInformation().get("dateC"));

        this.getMainController().getD().addSkills(cvID,
                this.getMainController().getInformation().get("mother"),
                this.getMainController().getInformation().get("otherLanguage"),
                this.getMainController().getInformation().get("softSkills"),
                this.getMainController().getInformation().get("hardSkills"),
                this.getMainController().getInformation().get("descriptionHI"));

        this.getMainController().getD().addRecommendations(cvID,
                this.getMainController().getInformation().get("nameR"),
                this.getMainController().getInformation().get("roleR"),
                this.getMainController().getInformation().get("emailR"),
                this.getMainController().getInformation().get("descriptionR"),
                this.getMainController().getInformation().get("phoneR"));

        this.getMainController().getD().addOthers(cvID,
                this.getMainController().getInformation().get("otherAddition"),
                this.getMainController().getInformation().get("titleAddition"),
                this.getMainController().getInformation().get("descriptionAddition"),
                this.getMainController().getInformation().get("originalFile"));


        ArrayList<Scene> scenes = new ArrayList<>();
        for (int i = 0; i <this.getMainController().getSceneList().size() ; i++) {
            scenes.add(this.getMainController().getSceneList().get(i));
        }
        CV cv = new CV(scenes.get(0),scenes.get(1),scenes.get(2),scenes.get(3),scenes.get(4));
        String cvName = this.getMainController().getD().getCVName();
        cv.setTitle(cvName);
        this.getMainController().getCvList().getItems().add(cv.getTitle());
        this.getMainController().getCvMap().put(cv.getTitle(), cv);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void loadCV() throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileInputStream fis;

        FileChooser.ExtensionFilter extensionFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
        FileChooser.ExtensionFilter extensionFilterDOCX = new FileChooser.ExtensionFilter("DOCX files (*.docx)", "*.DOCX");

        fileChooser.getExtensionFilters().addAll(extensionFilterPDF, extensionFilterDOCX);

        File file = fileChooser.showOpenDialog(null);

        fis = new FileInputStream(file);

        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        fis.close();
    }
}
