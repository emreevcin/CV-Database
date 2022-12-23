package com.Classes;

import javafx.scene.Scene;

public class CV {

    private String title ;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private Scene scene5;

    private boolean isCreatedNew ;

    public CV(Scene scene1, Scene scene2, Scene scene3, Scene scene4, Scene scene5) {
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.scene3 = scene3;
        this.scene4 = scene4;
        this.scene5 = scene5;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Scene getScene1() {
        return scene1;
    }

    public void setScene1(Scene scene1) {
        this.scene1 = scene1;
    }

    public Scene getScene2() {
        return scene2;
    }

    public void setScene2(Scene scene2) {
        this.scene2 = scene2;
    }

    public Scene getScene3() {
        return scene3;
    }

    public void setScene3(Scene scene3) {
        this.scene3 = scene3;
    }

    public Scene getScene4() {
        return scene4;
    }

    public void setScene4(Scene scene4) {
        this.scene4 = scene4;
    }

    public Scene getScene5() {
        return scene5;
    }

    public void setScene5(Scene scene5) {
        this.scene5 = scene5;
    }

    public boolean isCreatedNew() {
        return isCreatedNew;
    }

    public void setCreatedNew(boolean createdNew) {
        isCreatedNew = createdNew;
    }
}
