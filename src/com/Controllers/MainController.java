package com.Controllers;

import com.Classes.Main;
import com.Controllers.Add.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Main main ;

    private Controller_S1_PI controller_s1_pi ;
    private Controller_S2_WE controller_s2_we ;
    private Controller_S3_EP controller_s3_ep ;
    private Controller_S4_CS controller_s4_cs ;
    private Controller_S5_RO controller_s5_ro ;

    @FXML
    private Button createCV ;

    private Scene addScene ;
    private Stage addStage;
    @FXML
    private Button showCV; //CV'Yİ GÖSTER BUTONU

    public MainController() {
        this.controller_s1_pi = new Controller_S1_PI();
        this.controller_s2_we = new Controller_S2_WE();
        this.controller_s3_ep = new Controller_S3_EP();
        this.controller_s4_cs = new Controller_S4_CS();
        this.controller_s5_ro = new Controller_S5_RO();
        addStage = new Stage();
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

    public Scene getAddScene() {
        return addScene;
    }

    public void setAddScene(Scene addScene) {
        this.addScene = addScene;
    }

    public Stage getAddStage() {
        return addStage;
    }

    public void setAddStage(Stage addStage) {
        this.addStage = addStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller_s1_pi.init(this);
        controller_s2_we.init(this);
        controller_s3_ep.init(this);
        controller_s4_cs.init(this);
        controller_s5_ro.init(this);
        cvList.getItems().add("CV1"); // CV İÇİN STATİK BİR ÖRNEK EKLEDİM.
        cvList.getItems().add("CV2"); // CV İÇİN GİRİLEN TİTLE PDF NAME OLMALI

    }

    @FXML
    public void openCreateScreen(){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/personal-information-view.fxml"));
            Parent root = loader.load();

            Controller_S1_PI controller1 = loader.getController();
            controller1.init(this);

            addScene = new Scene(root);
            addStage.setScene(addScene);
            addStage.show();



        }catch(Exception e){
            e.printStackTrace();
        }


    }
    @FXML
    public void openShowCV() {
        cvList.getItems().remove(cvList.getSelectionModel().getSelectedItem());
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new java.io.File("C:\\Users\\erdem\\Desktop\\CV.pdf")); //HEPSİNİN AYNI YERDE OLMASI GEREKİYOR.
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TEKRAR VERİLERİN ÇEKİLMESİ GEREKİYOR BURADA. LİSTENİN GÜNCELLENMESİ İÇİN...


    }
