package com.Controllers.Add;

import com.Classes.DatabaseConnection;
import com.Classes.Main;
import com.Controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_S1_PI implements Initializable {

    DatabaseConnection d = new DatabaseConnection();
    // yeri değiştirilebilir deneme amaçlı burada

    private MainController mainController ;
    private Main main ;

    private Scene scene ;

    @FXML
    private Button cancelButton;

//    @FXML
//    private TextArea careerObjectiveTA;
    // TEXTAREA initialize edilince hata veriyor

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
    // DATABASE testi için kullanılıyor değişecek

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField titleTF;

    @FXML
    public void next(ActionEvent actionEvent) {
        try{
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

    // bu metodun yeri nerde ve nasıl olacağı konuşulcak
    @FXML
    void addCVs(ActionEvent event) {
        d.addCV(firstNameTF.getText(), lastNameTF.getText(), titleTF.getText(),
                emailTF.getText(), phoneTF.getText(), cityTF.getText(), countryTF.getText());
        firstNameTF.setText("");
        lastNameTF.setText("");
        titleTF.setText("");
        emailTF.setText("");
        phoneTF.setText("");
        cityTF.setText("");
        countryTF.setText("");
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
}
