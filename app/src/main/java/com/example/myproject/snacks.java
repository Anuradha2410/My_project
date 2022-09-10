package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class snacks extends AppCompatActivity {
RecyclerView recyclerView;
recycleadapter recycleadapte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        ArrayList<foodinfo> arrayList=new ArrayList<>();

        recyclerView=findViewById(R.id.recycle);

        new firebase().readdata(new firebase.datastatus() {
            @Override
            public void dataIsloaded(List<String> key, List<foodinfo> finfo) {
                LinearLayoutManager manager=new LinearLayoutManager(snacks.this);
                recycleadapte=new recycleadapter(snacks.this,finfo);
                recyclerView.setAdapter(recycleadapte);
                recyclerView.setLayoutManager(manager);

            }

            @Override
            public void dataisInserted() {

            }

            @Override
            public void dataIsupdated() {

            }

            @Override
            public void dataIsDeleted() {

            }
        });


    }
}