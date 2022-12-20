package com.Controllers;

import com.Classes.Main;
import com.Controllers.Add.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.*;
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
    @FXML
    private ListView cvList;
    @FXML
    private ListView pdfList;
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
        pullFiles();
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

    @FXML
    private void openButtonClick(ActionEvent event) throws IOException {

        File fileToOpen = new File("./././pdfs/"+pdfList.getSelectionModel().getSelectedItem());

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
    private   void pullFiles(){
        File folder = new File("./././pdfs/");
        File[] listOfFiles = folder.listFiles();
        pdfList.getItems().clear();
        for(File file : listOfFiles){
            if(file.isFile()){
                pdfList.getItems().add(file.getName());
            }
        }
    }
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    @FXML
    private void addButtonClick(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf","*.PDF"));
        File f = fc.showOpenDialog(null);

        if(f!=null){
            File src = new File(f.getPath());
            File dest = new File("./././pdfs/"+f.getName());
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
    private void deleteButtonClick(ActionEvent event) throws  IOException{
        File fileToDelete = new File("./././pdfs/"+pdfList.getSelectionModel().getSelectedItem());
        if(!fileToDelete.exists()){
            String alertText = "File does not exist!";
            Alerting(alertText);
        }
        else{
            fileToDelete.delete();
        }
        pullFiles();
    }




}
