package com.Controllers;

import com.Classes.CV;
import com.Classes.DatabaseConnection;
import com.Classes.Main;
import com.Controllers.Add.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import org.w3c.dom.events.EventException;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
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

    @FXML
    private ImageView createImage;

    @FXML
    private ListView<String> cvList;

    @FXML
    private Label cvNumberLabel;

    @FXML
    private ImageView deleteImage;

    @FXML
    private ImageView editImage;

    @FXML
    private ImageView filterImage;

    @FXML
    private Label firstNamePreview;

    @FXML
    private ImageView imagePreview;

    @FXML
    private Label lastNamePreview;

    @FXML
    private TextField searchBarTF;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ComboBox<?> searchSelectionList;

    @FXML
    private ImageView sourceCVPreview;

    @FXML
    private Label tagsPreview;

    @FXML
    private Label titlePreview;

    @FXML
    private Label createdAtPreview;

    protected HashMap<String, String> information = new HashMap<>();

    private HashMap<String,CV> cvMap;

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

    public Label getCvNumberLabel() {
        return cvNumberLabel;
    }

    public void setCvNumberLabel(Label cvNumberLabel) {
        this.cvNumberLabel = cvNumberLabel;
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
    private Connection conn;
    private PreparedStatement getCreatedDate;
    private PreparedStatement getPersonTitle;
    private PreparedStatement getPersonImage;
    private PreparedStatement getPersonName;
    private PreparedStatement getPersonSurname;
    private PreparedStatement pull;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller_s1_pi.init(this);
        controller_s2_we.init(this);
        controller_s3_ep.init(this);
        controller_s4_cs.init(this);
        controller_s5_ro.init(this);

        String stringNumberOfCV = Integer.toString(d.getNumberOfCV());
        cvNumberLabel.setText("CV Number " + stringNumberOfCV);
        if (d.getNumberOfCV() != 0) {
            try {
                String fileName = "cvdb.db";
                conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);
                pull = conn.prepareStatement("SELECT * FROM cvs");
                ResultSet rs = pull.executeQuery();
                while (rs.next()) {
                    String cvName = rs.getString("cv_name");
                    cvList.getItems().add(cvName);
                    CV cv = new CV(scene1, scene2, scene3, scene4, scene5);
                    cvMap.put(cvName, cv);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
    public void createCV(){
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
            //When we click on create button main scene will be hidden
            Stage stage = (Stage) cvList.getScene().getWindow();
            stage.hide();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // When we click on delete button, selected cv will be deleted in database and listview
    @FXML
    public void deleteCV(){
        String cvName = cvList.getSelectionModel().getSelectedItem();
        cvList.getItems().remove(cvName);
        cvMap.remove(cvName);
        getD().deleteCV(d.getCVID());
        String stringNumberOfCV = Integer.toString(d.getNumberOfCV());
        cvNumberLabel.setText("CV Number " + stringNumberOfCV);
    }
        @FXML
    public void editCV(){    // If selected CV exist edit screen openable
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
    public CV checkCV(String s){ //Checking CV is exists if exist return selected CV otherwise return null
        if(s!= null){
            for (String key : cvMap.keySet()) {
                if(key.equals(s)){
                    return cvMap.get(key);
                }
            }
        }
        return null;
    }

    @FXML
    void filter() {

    }

    @FXML
    void search() {

    }

    @FXML
    void showSourceCV() {

    }

    //Get data from database
//    public void getDataFromDatabase(String cvName){
//        try {
//            pull = conn.prepareStatement("SELECT * FROM cvs");
//            ResultSet rs = pull.executeQuery();
//            cvList.getItems().add(cvName);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


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