package com.Controllers;

import com.Classes.CV;
import com.Classes.DatabaseConnection;
import com.Classes.Main;
import com.Controllers.Add.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    DatabaseConnection d = new DatabaseConnection();

    public DatabaseConnection getD() {
        return d;
    }

    private Main main ;

    private Controller_S1_PI controller_s1_pi ;
    private Controller_S2_WE controller_s2_we ;
    private Controller_S3_EP controller_s3_ep ;
    private Controller_S4_CS controller_s4_cs ;
    private Controller_S5_RO controller_s5_ro ;

    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private Scene scene5;

    protected HashMap<String, String> information = new HashMap<>();

    private HashMap<String,CV> cvMap;

    @FXML
    private ListView<String> cvList;
    @FXML
    private ListView<String> pdfList;

    private ArrayList<Scene> sceneList;

    private Scene addScene ;
    private Stage addStage;

    public MainController() {
        this.controller_s1_pi = new Controller_S1_PI();
        this.controller_s2_we = new Controller_S2_WE();
        this.controller_s3_ep = new Controller_S3_EP();
        this.controller_s4_cs = new Controller_S4_CS();
        this.controller_s5_ro = new Controller_S5_RO();
        addStage = new Stage();
        cvMap = new HashMap<>();
        sceneList= new ArrayList<>();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setController_s1_pi(Controller_S1_PI controller_s1_pi) {
        this.controller_s1_pi = controller_s1_pi;
    }

    public void setController_s2_we(Controller_S2_WE controller_s2_we) {
        this.controller_s2_we = controller_s2_we;
    }

    public void setController_s3_ep(Controller_S3_EP controller_s3_ep) {
        this.controller_s3_ep = controller_s3_ep;
    }

    public void setController_s4_cs(Controller_S4_CS controller_s4_cs) {
        this.controller_s4_cs = controller_s4_cs;
    }

    public void setController_s5_ro(Controller_S5_RO controller_s5_ro) {
        this.controller_s5_ro = controller_s5_ro;
    }


    public Stage getAddStage() {
        return addStage;
    }


    public ArrayList<Scene> getSceneList() {
        return sceneList;
    }
    public HashMap<String, CV> getCvMap() {
        return cvMap;
    }

    public ListView<String> getCvList() {
        return cvList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller_s1_pi.init(this);
        controller_s2_we.init(this);
        controller_s3_ep.init(this);
        controller_s4_cs.init(this);
        controller_s5_ro.init(this);

        pullFiles();
    }

    @FXML
    public void openCreateScreen(){
        try{
            FXMLLoader addLoader1 = new FXMLLoader(getClass().getResource("../resources/personal-information-view.fxml"));
            FXMLLoader addLoader2 = new FXMLLoader(getClass().getResource("../resources/work-experience-view.fxml"));
            FXMLLoader addLoader3 = new FXMLLoader(getClass().getResource("../resources/education-projects-view.fxml"));
            FXMLLoader addLoader4 = new FXMLLoader(getClass().getResource("../resources/certificates-skills-view.fxml"));
            FXMLLoader addLoader5 = new FXMLLoader(getClass().getResource("../resources/recommendation-others-view.fxml"));

            Parent root1 = addLoader1.load();
            Parent root2 = addLoader2.load();
            Parent root3 = addLoader3.load();
            Parent root4 = addLoader4.load();
            Parent root5 = addLoader5.load();

            Controller_S1_PI controller_s1_pi1 = addLoader1.getController();
            controller_s1_pi1.init(this);
            Controller_S2_WE controller_s2_we1 = addLoader2.getController();
            controller_s2_we1.init(this);
            Controller_S3_EP controller_s3_ep1 = addLoader3.getController();
            controller_s3_ep1.init(this);
            Controller_S4_CS controller_s4_cs1 = addLoader4.getController();
            controller_s4_cs1.init(this);
            Controller_S5_RO controller_s5_ro1 = addLoader5.getController();
            controller_s5_ro1.init(this);

            Scene scene1 = new Scene(root1);
            Scene scene2 = new Scene(root2);
            Scene scene3 = new Scene(root3);
            Scene scene4 = new Scene(root4);
            Scene scene5 = new Scene(root5);

            sceneList.clear();
            sceneList.add(scene1);
            sceneList.add(scene2);
            sceneList.add(scene3);
            sceneList.add(scene4);
            sceneList.add(scene5);

            addScene = scene1;
            addStage.setScene(addScene);
            addStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void openShowCV() {
        // needs to show both created and imported cvs
        Desktop desktop = Desktop.getDesktop();
        try {
            //desktop.open(new java.io.File("")); //irrelevant path.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openButtonClick() throws IOException {
        File fileToOpen = new File("../resources/PDFs/"+pdfList.getSelectionModel().getSelectedItem());

        if(!fileToOpen.exists()){
            String alertText = "File does not exist!";
            Alerting(alertText);
            pullFiles();
        }else {
            Desktop.getDesktop().open(fileToOpen);
        }
    }
    private void Alerting(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }
    private void pullFiles(){
        File folder = new File("../resources/PDFs/");
        File[] listOfFiles = folder.listFiles();
        pdfList.getItems().clear();
        if(listOfFiles == null)
            return;
        for(File file : listOfFiles){
            if(file.isFile()){
                pdfList.getItems().add(file.getName());
            }
        }
    }
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
    @FXML
    private void addButtonClick() throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf","*.PDF"));
        File f = fc.showOpenDialog(null);

        if(f!=null){
            File src = new File(f.getPath());
            File dest = new File("../resources/PDFs/"+f.getName());
            if (dest.exists()){
                String alertText = "File already exist!";
                Alerting(alertText);
            }else{
                copyFileUsingStream(src,dest);
                pullFiles();
            }
        }
    }
    @FXML
    private void deleteButtonClick(ActionEvent event){
        File fileToDelete = new File("../resources/PDFs/"+pdfList.getSelectionModel().getSelectedItem());
        if(!fileToDelete.exists()){
            String alertText = "File does not exist!";
            Alerting(alertText);
        }
        else{
            fileToDelete.delete();
        }
        pullFiles();
    }
    @FXML
    public void deleteCV(){
        // delete selected cv from listview and data structure that holds the selected cv
        String s  =cvList.getSelectionModel().getSelectedItem();
        if(s.equals("")) {
            return ;
        }
        cvList.getItems().remove(s);
        cvMap.remove(s);

    }
    @FXML
    public void openEditScreen(){    // If selected CV exist edit screen openable
        try{
            String title = cvList.getSelectionModel().getSelectedItem();
            CV cv = checkCV(title);
            if(cv==null){
                return;
            }
            Stage stage = new Stage();
            stage.setScene(cv.getScene1());
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public CV checkCV(String s){ //Checking CV is exist if exist return selected CV otherwise return null
        if(s!= null){
            for (String key : cvMap.keySet()) {
                if(key.equals(s)){
                    return cvMap.get(key);
                }
            }
        }
        return null;
    }


    public Controller_S1_PI getController_s1_pi() {
        return controller_s1_pi;
    }

    public Controller_S2_WE getController_s2_we() {
        return controller_s2_we;
    }

    public Controller_S3_EP getController_s3_ep() {
        return controller_s3_ep;
    }

    public Controller_S4_CS getController_s4_cs() {
        return controller_s4_cs;
    }

    public Controller_S5_RO getController_s5_ro() {
        return controller_s5_ro;
    }

    public HashMap<String, String> getInformation() {
        return information;
    }

    public void setInformation(HashMap<String, String> information) {
        this.information = information;
    }
}
