package com.example.myproject.classes;

import java.io.Serializable;

public class itemcart implements Serializable {
    String quan;
    String name,price,pos="-1";
    int image;


    public itemcart(String name, String price, String quan, int image) {
        this.name = name;
        this.price = price;
        this.quan = quan;
        this.image = image;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public itemcart(String name, String price, String quan,int image, String pos) {
        this.quan = quan;
        this.name = name;
        this.price = price;
        this.pos = pos;
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

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
