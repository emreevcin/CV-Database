package com.Controllers.Add;

import com.Classes.CV;
import com.Classes.DatabaseConnection;
import com.Classes.Main;
import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_S5_RO implements Initializable {

    private MainController mainController ;
    private Main main ;
    private Scene scene ;



    @FXML
    private Button backButton,addCVButton,saveButton;

    @FXML
    private TextArea descriptionOTA,descriptionRTA;
    @FXML
    private TextField emailTF,nameTF,otherTF,phoneTF,roleTF,titleTF;


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

    public void AllInfo(){
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
                this.getMainController().getInformation().get("tagPI"),
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

        String stringNumberOfCV = Integer.toString(this.getMainController().getD().getNumberOfCV());
        this.getMainController().getCvNumberLabel().setText("CV Number " + stringNumberOfCV);

        ArrayList<Scene> scenes = new ArrayList<>();
        for (int i = 0; i <this.getMainController().getSceneList().size() ; i++) {
            scenes.add(this.getMainController().getSceneList().get(i));
        }
        CV cv = new CV(scenes.get(0),scenes.get(1),scenes.get(2),scenes.get(3),scenes.get(4));
        String cvName = this.getMainController().getD().getCVName();
        cv.setTitle(cvName);
        String cvTag = this.getMainController().getD().getCVTag();
        cv.setTag(cvTag);
        this.getMainController().getCvList().getItems().add(cv.getTitle());
        this.getMainController().getCvMap().put(cv.getTitle(), cv);
        //when clicked submit button the stage will be closed
        Stage stage = (Stage) mainController.getCvList().getScene().getWindow();
        stage.show();
        //last stage will be close when you click submit button
        Stage stage2 = (Stage) cv.getScene5().getWindow();
        stage2.close();
    }

    public void back(){
        Scene scene4 = this.getMainController().getSceneList().get(3);
        this.getMainController().getAddStage().setScene(scene4);
    }

    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }
    private void submitMethod(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submit");
        alert.setHeaderText("Submit");
        alert.setContentText("Your CV is submitted");
        alert.show();
    }


    private String attachedCvFolderPath = "./src/com/resources/attachedCvs";
    private File cvDocFolder = new File(attachedCvFolderPath);
    private File chosenPDF ;


    @FXML
    public void submit() throws IOException {
        if (roleTF.getText().isEmpty() && (!otherTF.getText().isEmpty() || !titleTF.getText().isEmpty() || !descriptionOTA.getText().isEmpty())){
            AlertMethod("You can't submit unless all the important fields are filled");
        }
        else if (!phoneTF.getText().isEmpty() && !phoneTF.getText().matches("[0-9]+") && phoneTF.getText().length() != 10){
            AlertMethod("Please enter a valid phone number");
        }
        else if (!emailTF.getText().isEmpty() &&!emailTF.getText().contains("@")){
            AlertMethod("Please enter a valid email address");
        }
        else if (nameTF.getText().isEmpty()  && (!otherTF.getText().isEmpty() || !titleTF.getText().isEmpty() || !descriptionOTA.getText().isEmpty())){
            AlertMethod("You can't fill these field unless all the important fields are filled");
        }
        else if (descriptionRTA.getText().isEmpty() && (!otherTF.getText().isEmpty() || !titleTF.getText().isEmpty() || !descriptionOTA.getText().isEmpty())){
            AlertMethod("You can't fill these field unless all the important fields are filled");
        }
        else if (emailTF.getText().isEmpty()&& (!otherTF.getText().isEmpty() || !titleTF.getText().isEmpty() || !descriptionOTA.getText().isEmpty())){
            AlertMethod("You can't fill these field unless all the important fields are filled");
        }
        else if (phoneTF.getText().isEmpty()&& (!otherTF.getText().isEmpty() || !titleTF.getText().isEmpty() || !descriptionOTA.getText().isEmpty())){
            AlertMethod("You can't fill these field unless all the important fields are filled");
        }

        else {
            AllInfo();

            if (!cvDocFolder.exists()){
                cvDocFolder.mkdir();
                File destinationCv = new File(attachedCvFolderPath +"/" + this.getMainController().getD().getCVName()+".pdf");
                File sourceCv = new File(chosenPDF.getPath());
                copyFileUsingStream(sourceCv,destinationCv);
            }
            else {

                File destinationCv = new File(attachedCvFolderPath +"/" + this.getMainController().getD().getCVName()+".pdf");
                File sourceCv = new File(chosenPDF.getPath());
                copyFileUsingStream(sourceCv,destinationCv);
            }



        }

    }




    public static void copyFileUsingStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
    @FXML
    void loadCV() throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileInputStream fis;

        FileChooser.ExtensionFilter extensionFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
        FileChooser.ExtensionFilter extensionFilterDOCX = new FileChooser.ExtensionFilter("DOCX files (*.docx)", "*.DOCX");

        fileChooser.getExtensionFilters().addAll(extensionFilterPDF, extensionFilterDOCX);


        chosenPDF = fileChooser.showOpenDialog(null);
        if(chosenPDF != null){
            addCVButton.setDisable(true);
        }




        /**
        fis = new FileInputStream(file);

        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        fis.close();*/
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
