package com.example.myproject.classes;

import androidx.appcompat.widget.SearchView;

import java.io.Serializable;

public class foodinfo implements Serializable {
    int image;

    public foodinfo() {
    }

    String name,price;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public foodinfo(int image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;

    }
}
