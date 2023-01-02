package com.Controllers;

import com.Classes.CV;
import com.Classes.DatabaseConnection;
import com.Classes.Main;
import com.Controllers.Add.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.w3c.dom.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

import javax.swing.JFileChooser;



public class MainController implements Initializable {

    DatabaseConnection d = new DatabaseConnection();

    public DatabaseConnection getD() {
        return d;
    }

    private Main main;

    private Controller_S1_PI controller_s1_pi;
    private Controller_S2_WE controller_s2_we;
    private Controller_S3_EP controller_s3_ep;
    private Controller_S4_CS controller_s4_cs;
    private Controller_S5_RO controller_s5_ro;

    @FXML
    private ListView<String> cvList;

    @FXML
    private Label cvNumberLabel;

    public Label getCvNumberLabel() {
        return cvNumberLabel;
    }

    @FXML
    private TextField searchBarTF;

    @FXML
    private ComboBox<String> searchFieldCB;
    @FXML
    private ComboBox<String> filterSelection ;

    protected HashMap<String, String> information;

    private HashMap<String, CV> cvMap;

    private ArrayList<Scene> sceneList;

    private Scene addScene;
    private Stage addStage;

    private boolean isEditFunctionRunned;
    public boolean isEditFunctionRunned() {
        return isEditFunctionRunned;
    }
    public void setEditFunctionRunned(boolean editFunctionRunned) {
        isEditFunctionRunned = editFunctionRunned;
    }
    public MainController() {
        this.controller_s1_pi = new Controller_S1_PI();
        this.controller_s2_we = new Controller_S2_WE();
        this.controller_s3_ep = new Controller_S3_EP();
        this.controller_s4_cs = new Controller_S4_CS();
        this.controller_s5_ro = new Controller_S5_RO();
        addStage = new Stage();
        cvMap = new HashMap<>();
        sceneList = new ArrayList<>();
        information = new HashMap<>();
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

    public HashMap<String, String> getInformation() {
        return information;
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

        searchFieldCB.getItems().addAll("Title", "Name", "Surname", "Name-Surname", "Institution", "Employer", "Tag");
        filterSelection.getItems().addAll("A-Z","Z-A");

        d.reloadCV(cvList);

        filterSelection.setOnAction(actionEvent -> {
            String filter = filterSelection.getSelectionModel().getSelectedItem();
            filter(filter);
        });



        }

    @FXML
    public void createCV() {
        try {
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
            //When we click on create button main scene will be hidden
            Stage stage = (Stage) cvList.getScene().getWindow();
            stage.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    public void createPdf(){
//        /*
//        // Create a file chooser
//        JFileChooser fileChooser = new JFileChooser();
//        int returnValue = fileChooser.showSaveDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            // Get the selected file
//
//            java.io.File selectedFile = fileChooser.getSelectedFile();
//            // Create a Path object for the file
//            Path filePath = Paths.get(selectedFile.getAbsolutePath());
//            try {
//                // Create the file
//                Files.createFile(filePath);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//       /* final JFileChooser fc = new JFileChooser();
//        int returnVal = fc.showSaveDialog(null);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            // User has selected a file, get the file path
//            String filePath = fc.getSelectedFile().getAbsolutePath();
//            if (!filePath.endsWith(".pdf")) {
//                // Append .pdf to the file name if it doesn't already have it
//                filePath += ".pdf";
//            }*/
//        String fileName =cvList.getSelectionModel().getSelectedItem()+".pdf";
//        // Create a file chooser
//        JFileChooser fileChooser = new JFileChooser();
//        // Set the file chooser to allow the user to select a directory, not just a file
//        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int returnValue = fileChooser.showSaveDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            // Get the selected directory
//            java.io.File selectedDirectory = fileChooser.getSelectedFile();
//            // Create a Path object for the file
//            Path filePath = Paths.get(selectedDirectory.getAbsolutePath(), fileName);
//            try {
//                // Create the file
//                Files.createFile(filePath);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }}


    @FXML
    public void deleteCV() {
        // delete selected cv from listview and data structure that holds the selected cv
        String s = cvList.getSelectionModel().getSelectedItem();
        int deleted_id = d.getIDwithParam(s);

        System.out.println(s);
        System.out.println(deleted_id);
        if (s.equals("")) {
            return;
        }
        String[] tableArr = {"certificates","educations","other_information","people","projects","recommendations","skills","works" };
        for (String value : tableArr) {
            System.out.println(value);
            d.deleteFromTable(value, deleted_id);
        }
        d.deleteCV(deleted_id);

        cvList.getItems().remove(s);
        cvMap.remove(s);

    }

    @FXML
    public void editCV() {    // If selected CV exist edit screen openable
        try {
            isEditFunctionRunned = true;
            String title = cvList.getSelectionModel().getSelectedItem();
            CV cv = checkCV(title);
            if(cv==null){
                return;
            }
            Stage stage = new Stage();
            stage.setScene(cv.getScene1());
            stage.show();
            // this is the main stage and when you clickled edit screen it will be hidden
            Stage mainStage = (Stage) getCvList().getScene().getWindow();
            mainStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CV checkCV(String s) { //Checking CV is exists if exist return selected CV otherwise return null
        if (s != null) {
            for (String key : cvMap.keySet()) {
                if (key.equals(s)) {
                    return cvMap.get(key);
                }
            }
        }
        return null;
    }

    @FXML
    void filter(String sortingAs) {
        ObservableList<String> cv_names = FXCollections.observableArrayList();
        cv_names.addAll(cvList.getItems());

        if(sortingAs.equals("A-Z")){
            cv_names.sort(String::compareTo);
        }
        else if(sortingAs.equals("Z-A")){
            cv_names.sort(Collections.reverseOrder(String::compareToIgnoreCase));
        }
        cvList.getItems().clear();
        cvList.getItems().addAll(cv_names);
    }

    @FXML
    void search() throws SQLException {
        String key = searchBarTF.getText();
        String field = searchFieldCB.getSelectionModel().getSelectedItem();

        System.out.println(key + " " + field);
        if (field == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Select the relevant field for searching");
            alert.showAndWait();
            return;
        }

        HashMap<Integer, String> showList = d.searchCV(key, field);

        if (showList == null || showList.isEmpty())
            return;

        cvList.getItems().clear();

        for (Integer k : showList.keySet()) {
            cvList.getItems().add(showList.get(k));
        }


    }

    @FXML
    void showSourceCV() {

    }

}



//    @FXML
//    private void openButtonClick() throws IOException {
//        File fileToOpen = new File("../resources/PDFs/"+pdfList.getSelectionModel().getSelectedItem());
//
//        if(!fileToOpen.exists()){
//            String alertText = "File does not exist!";
//            Alerting(alertText);
//            pullFiles();
//        }else {
//            Desktop.getDesktop().open(fileToOpen);
//        }
//    }
//    private void Alerting(String contentText){
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Error");
//        alert.setHeaderText("PROBLEM:");
//        alert.setContentText(contentText);
//        alert.show();
//    }
//    private void pullFiles(){
//        File folder = new File("../resources/PDFs/");
//        File[] listOfFiles = folder.listFiles();
//        pdfList.getItems().clear();
//        if(listOfFiles == null)
//            return;
//        for(File file : listOfFiles){
//            if(file.isFile()){
//                pdfList.getItems().add(file.getName());
//            }
//        }
//    }
//    private static void copyFileUsingStream(File source, File dest) throws IOException {
//        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = is.read(buffer)) > 0) {
//                os.write(buffer, 0, length);
//            }
//        }
//    }
//    @FXML
//    private void addButtonClick() throws IOException {
//        FileChooser fc = new FileChooser();
//        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf","*.PDF"));
//        File f = fc.showOpenDialog(null);
//
//        if(f!=null){
//            File src = new File(f.getPath());
//            File dest = new File("../resources/PDFs/"+f.getName());
//            if (dest.exists()){
//                String alertText = "File already exist!";
//                Alerting(alertText);
//            }else{
//                copyFileUsingStream(src,dest);
//                pullFiles();
//            }
//        }
//    }
//    @FXML
//    private void deleteButtonClick(ActionEvent event){
//        File fileToDelete = new File("../resources/PDFs/"+pdfList.getSelectionModel().getSelectedItem());
//        if(!fileToDelete.exists()){
//            String alertText = "File does not exist!";
//            Alerting(alertText);
//        }
//        else{
//            fileToDelete.delete();
//        }
//        pullFiles();
//    }
