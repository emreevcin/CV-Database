package com.Controllers.Add;

import com.Classes.Main;
import com.Controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Button cancelButton;
    @FXML
    private ImageView personImageView;
    @FXML
    private Button loadPhotoButton;
    @FXML
    private TextArea careerObjectiveTA;

    @FXML
    private TextField cityTF;

    @FXML
    private TextField countryTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private Button nextButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField titleTF;

    private ByteArrayOutputStream bos = new ByteArrayOutputStream();

    @FXML
    public void next(ActionEvent actionEvent) {
        try{
            this.getMainController().getInformation().put("firstName", firstNameTF.getText());
            this.getMainController().getInformation().put("lastName", lastNameTF.getText());
            this.getMainController().getInformation().put("photo", bos.toString(StandardCharsets.UTF_8));
            this.getMainController().getInformation().put("titlePI", titleTF.getText());
            this.getMainController().getInformation().put("careerObjective", careerObjectiveTA.getText());
            this.getMainController().getInformation().put("emailPI", emailTF.getText());
            this.getMainController().getInformation().put("phonePI", phoneTF.getText());
            this.getMainController().getInformation().put("cityPI", cityTF.getText());
            this.getMainController().getInformation().put("countryPI", countryTF.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/work-experience-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = this.getMainController().getAddStage();
            Controller_S2_WE controller_s2_we = loader.getController();
            controller_s2_we.init(this.getMainController());
            stage.setScene(scene);


        }catch (Exception e){
            e.printStackTrace();
        }
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

    @FXML
    public void next(){
        try{
            Scene scene2 = this.getMainController().getSceneList().get(1);
            this.getMainController().getAddStage().setScene(scene2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadPhoto() throws IOException {
        FileChooser fc = new FileChooser();

        FileInputStream fis;

        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");

        fc.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterPNG);

        File file = fc.showOpenDialog(null);

        fis = new FileInputStream(file);

        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        fis.close();

        Image image = new Image(file.getAbsolutePath());

        personImageView.setImage(image);
        personImageView.setFitHeight(100);
        personImageView.setPreserveRatio(true);
        personImageView.setSmooth(true);
        personImageView.setCache(true);
    }
}
