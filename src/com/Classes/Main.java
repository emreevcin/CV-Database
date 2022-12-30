package com.Classes;

import com.Controllers.*;
import com.Controllers.Add.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/main-view.fxml"));
        Scene mainScene = new Scene(loader.<Parent>load());

        MainController mainController =new MainController();
        mainController.setMain(this);


        // loaders for adding process
        FXMLLoader addLoader1 = new FXMLLoader(getClass().getResource("../resources/personal-information-view.fxml"));
        FXMLLoader addLoader2 = new FXMLLoader(getClass().getResource("../resources/work-experience-view.fxml"));
        FXMLLoader addLoader3 = new FXMLLoader(getClass().getResource("../resources/education-projects-view.fxml"));
        FXMLLoader addLoader4 = new FXMLLoader(getClass().getResource("../resources/certificates-skills-view.fxml"));
        FXMLLoader addLoader5 = new FXMLLoader(getClass().getResource("../resources/recommendation-others-view.fxml"));




        //scenes of adding process
        Parent root1 = addLoader1.load();
        Scene scene1 = new Scene(root1);
        Parent root2 = addLoader2.load();
        Scene scene2 = new Scene(root2);
        Parent root3 = addLoader3.load();
        Scene scene3 = new Scene(root3);
        Parent root4 = addLoader4.load();
        Scene scene4 = new Scene(root4);
        Parent root5 = addLoader5.load();
        Scene scene5 = new Scene(root5);

        // controllers for adding process
        Controller_S1_PI controller_s1_pi = new Controller_S1_PI();
        Controller_S2_WE controller_s2_we = new Controller_S2_WE();
        Controller_S3_EP controller_s3_ep = new Controller_S3_EP();
        Controller_S4_CS controller_s4_cs = new Controller_S4_CS();
        Controller_S5_RO controller_s5_ro = new Controller_S5_RO();

        controller_s1_pi.setMain(this);
        controller_s1_pi.setMainController(mainController);
        controller_s1_pi.setScene(scene1);

        controller_s2_we.setMain(this);
        controller_s2_we.setMainController(mainController);
        controller_s2_we.setScene(scene2);

        controller_s3_ep.setMainController(mainController);
        controller_s3_ep.setMain(this);
        controller_s3_ep.setScene(scene3);

        controller_s4_cs.setMainController(mainController);
        controller_s4_cs.setMain(this);
        controller_s4_cs.setScene(scene4);

        controller_s5_ro.setMain(this);
        controller_s5_ro.setMainController(mainController);
        controller_s5_ro.setScene(scene5);

        mainController.setController_s1_pi(controller_s1_pi);
        mainController.setController_s2_we(controller_s2_we);
        mainController.setController_s3_ep(controller_s3_ep);
        mainController.setController_s4_cs(controller_s4_cs);
        mainController.setController_s5_ro(controller_s5_ro);

        primaryStage.setTitle("CV DATABASE");
        primaryStage.getIcons().add(new Image("com\\resources\\icons\\application_icon.png"));
        primaryStage.setScene(mainScene);
        primaryStage.setMinWidth(602);
        primaryStage.setMinHeight(432);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
