package com.Controllers.Add;

import com.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller_S2_WE implements Initializable {

    private MainController mainController ;

    private ArrayList<HashMap<String,String>> data = new ArrayList<>() ;


    private Scene scene ;

    @FXML
    private TextField cityTF,countryTF,employerTF,occupationTF;

    @FXML
    private TextArea explanationTA;

    @FXML
    private DatePicker fromDate,toDate;

    @FXML
    private CheckBox isOngoing;

    @FXML
    private ComboBox<Integer>experiencesCB ;

    public void setData(ArrayList<HashMap<String, String>> data) {
        this.data = data;
        loadData(-1);
    }

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

    public void init (MainController mainController){
        setMainController(mainController);
    }

    //Alert method
    private void AlertMethod(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("PROBLEM:");
        alert.setContentText(contentText);
        alert.show();
    }

    public ArrayList<HashMap<String, String>> getData() {
        return data;
    }

    @FXML
    public void next(){
        Scene scene3 = this.getMainController().getController_s3_ep().getScene();
        this.getMainController().getAddStage().setScene(scene3);
    }
    @FXML
    public void back(){
        Scene scene1 = this.getMainController().getController_s1_pi().getScene();
        this.getMainController().getAddStage().setScene(scene1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void add(){
        HashMap<String,String> work = new HashMap<>();
        work.put("city",cityTF.getText());
        work.put("country",countryTF.getText());
        work.put("employer",employerTF.getText());
        work.put("occupation",occupationTF.getText());
        work.put("activities_responsibilities",explanationTA.getText());
        if(fromDate.getValue()==null){
            work.put("starting_date",null);
        }
        else{
            work.put("starting_date",fromDate.getValue().toString());
        }
        if(toDate.getValue()==null){
            work.put("ending_date",null);
        }
        else {
            work.put("ending_date", toDate.getValue().toString());
        }
        work.put("ongoing", String.valueOf(isOngoing));

        cityTF.setText("");
        countryTF.setText("");
        occupationTF.setText("");
        employerTF.setText("");
        explanationTA.setText("");
        fromDate.setValue(null);
        toDate.setValue(null);
        isOngoing.setSelected(false);
        data.add(work);

        int i = experiencesCB.getItems().size()+1;
        experiencesCB.getItems().add(i);


    }

    public void loadData(int index) {
        if(data==null)
            return;

        HashMap<String,String> work ;

        if(index ==-1)
            work = data.get(0);
        else
            work = data.get(index);

        if(work==null){
            System.out.println("work null");
            return;
        }

        this.getMainController().displayer(work);


        cityTF.setText(work.get("city"));
        countryTF.setText(work.get("country"));
        employerTF.setText(work.get("employer"));
        occupationTF.setText(work.get("occupation"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDateString = work.get("starting_date");
        String toDateString = work.get("ending_date");
        if(fromDateString != null){
            LocalDate fromD = LocalDate.parse(fromDateString, formatter);
            fromDate.setValue(fromD);
        }
        if(toDateString!=null){
            LocalDate toD = LocalDate.parse(toDateString, formatter);
            toDate.setValue(toD);
        }
        isOngoing.setSelected(work.get("ongoing").equals("Ongoing"));
        explanationTA.setText(work.get("activities_responsibilities"));


    }
}

