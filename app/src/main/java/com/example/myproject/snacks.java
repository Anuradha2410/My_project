package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;

public class snacks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        ArrayList<foodinfo> arrayList=new ArrayList<foodinfo>();
        arrayList.add(new foodinfo(R.drawable.bev,"","",""));
    }
}