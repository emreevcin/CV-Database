package com.Classes;

import javafx.scene.Scene;

public class CV {

    private String title ;
    private String tag;
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

    public Scene getScene1() {
        return scene1;
    }
    public Scene getScene2() {
        return scene2;
    }
    public Scene getScene3() {
        return scene3;
    }
    public Scene getScene4() {
        return scene4;
    }
    public Scene getScene5() {
        return scene5;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
