package com.Classes;

import javafx.scene.Scene;

public class CV {

    private String title ;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private Scene scene5;
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
}
