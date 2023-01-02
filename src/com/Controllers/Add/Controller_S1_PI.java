package com.Controllers.Add;

import com.Classes.CV;
import com.Classes.Main;
import com.Controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class Controller_S1_PI implements Initializable {

    private MainController mainController ;
    private Main main ;

    private Scene scene ;

    @FXML
    private Button cancelButton,loadPhotoButton
            ,nextButton,addButton;
    @FXML
    private ImageView personImageView;

    @FXML
    private TextArea careerObjectiveTA;

    @FXML
    private TextField cityTF,countryTF,emailTF,firstNameTF,lastNameTF
            ,phoneTF,titleTF,tagTF;

    private ByteArrayOutputStream bos = new ByteArrayOutputStream();

    @FXML
    public void next() {
        if(mainController.isEditFunctionRunned()){

            Stage stage = new Stage();
            stage.setScene(mainController.getSceneList().get(1));
            stage.show();
            // this is the main stage and when you clicked edit screen it will be hidden
            Stage personalInfoView = (Stage) nextButton.getScene().getWindow();
            personalInfoView.hide();
        }

        else if(firstNameTF.getText().isEmpty() && lastNameTF.getText().isEmpty()) {
            AlertMethod("Please enter the first Name and last name and try again");
        }
        else if(!emailTF.getText().isEmpty() && !emailTF.getText().contains("@") && !emailTF.getText().contains(".")) {
            AlertMethod("Please enter the email correctly and try again");
        }
        else if(!phoneTF.getText().isEmpty() && phoneTF.getText().length() != 10) {
            AlertMethod("Please enter the phone number correctly and try again");
        }
        else if (!firstNameTF.getText().isEmpty() && lastNameTF.getText().isEmpty()){
            AlertMethod("Also you need to enter last name");
        }


        else
            try{allInfo();}
            catch (Exception e){e.printStackTrace();}
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        this.getMainController().getAddStage().close();
        Stage stage = (Stage) mainController.getCvList().getScene().getWindow();
        stage.show();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    private String attachedPhotoFolderPath = "./src/com/resources/attachedPhoto";
    private File cvPhotoFolder = new File(attachedPhotoFolderPath);
    public File chosenPhoto;

    @FXML
    public void loadPhoto() throws IOException {
        FileChooser fc = new FileChooser();

        FileInputStream fis;

        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");

        fc.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterPNG);
        Controller_S5_RO a = new Controller_S5_RO();
        chosenPhoto = fc.showOpenDialog(null);
        System.out.println(chosenPhoto);
        /**
        fis = new FileInputStream(file);

        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        fis.close();
         */
        Image image = new Image(chosenPhoto.getAbsolutePath());
        Controller_S5_RO controller_s5_ro = new Controller_S5_RO();
        if (!cvPhotoFolder.exists()){
            cvPhotoFolder.mkdir();
            File destinationPhoto = new File(attachedPhotoFolderPath +"/" + this.getMainController().getD().getCVName()+".png");
            File sourcePhoto = new File(chosenPhoto.getPath());

            controller_s5_ro.copyFileUsingStream(sourcePhoto,destinationPhoto);
        }
        else {

            File destinationPhoto = new File(attachedPhotoFolderPath +"/" + this.getMainController().getD().getCVName()+".png");
            File sourcePhoto = new File(chosenPhoto.getPath());
            controller_s5_ro.copyFileUsingStream(sourcePhoto,destinationPhoto);
        }

        personImageView.setImage(image);
        personImageView.setFitHeight(100);
        personImageView.setPreserveRatio(true);
        personImageView.setSmooth(true);
        personImageView.setCache(true);

    }


    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }

    private void allInfo(){
        this.getMainController().getInformation().put("firstName", firstNameTF.getText());
        this.getMainController().getInformation().put("lastName", lastNameTF.getText());
        this.getMainController().getInformation().put("tagPI", tagTF.getText());
        this.getMainController().getInformation().put("photo", bos.toString(StandardCharsets.UTF_8));
        this.getMainController().getInformation().put("titlePI", titleTF.getText());
        this.getMainController().getInformation().put("careerObjective", careerObjectiveTA.getText());
        this.getMainController().getInformation().put("emailPI", emailTF.getText());
        this.getMainController().getInformation().put("phonePI", phoneTF.getText());
        this.getMainController().getInformation().put("cityPI", cityTF.getText());
        this.getMainController().getInformation().put("countryPI", countryTF.getText());
        Scene scene2 = this.getMainController().getSceneList().get(1);
        this.getMainController().getAddStage().setScene(scene2);

    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void init (MainController mainController){
        setMainController(mainController);
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
}