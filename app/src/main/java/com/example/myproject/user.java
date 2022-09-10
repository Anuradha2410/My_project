package com.example.myproject;



public class user {
    public String uname,email ,pass;
    public long phone;

    public user(String uname, String email, String pass, long phone) {
        this.uname = uname;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
    }

    public user() {
    }

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }



}