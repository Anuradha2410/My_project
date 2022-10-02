package com.example.myproject.classes;

import java.io.Serializable;

public class category implements Serializable {
    private String intro;
    private  int img;

    public category(String intro, int img) {
        this.intro = intro;
        this.img = img;
    }

    public category() {
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
