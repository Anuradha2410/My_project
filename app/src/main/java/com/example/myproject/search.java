package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.myproject.Adapters.categoryadapter;
import com.example.myproject.classes.category;

import java.util.ArrayList;

import java.util.List;

public class search extends AppCompatActivity implements recyclerviewselector{
RecyclerView recyclerView;
categoryadapter categoryadapte;
SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent=getIntent();
        List<category> list= (List<category>) intent.getSerializableExtra("list");
        searchView=findViewById(R.id.search);
        searchView.clearFocus();

        List<category> finalList = list;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<category> foodinfoList=new ArrayList<>();
                for (category finfo: finalList){
                    if(newText.isEmpty()){
                        foodinfoList.clear();
                    }
                    else if(finfo.getIntro().toLowerCase().contains(newText.toLowerCase())){
                        foodinfoList.add(finfo);
                    }

                }
                if(foodinfoList.isEmpty()){
                    if(newText.isEmpty()){
                        categoryadapte.setfoodinfo(foodinfoList);
                      }else{
                        foodinfoList.clear();
                        categoryadapte.setfoodinfo(foodinfoList);
                        Toast.makeText(getApplicationContext(), "No food found", Toast.LENGTH_SHORT).show();
                    }
                  }
                else{
                    recyclerView = findViewById(R.id.recycle);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                    categoryadapte = new categoryadapter(foodinfoList, getApplicationContext(), search.this);
                    categoryadapte.setfoodinfo(foodinfoList);
                    recyclerView.setAdapter(categoryadapte);
                    recyclerView.setLayoutManager(manager);


                }
                return true;
            }
        });

}

    @Override
    public void onitemSelect(String postion) {
        Intent intent=new Intent(getApplicationContext(), MainActivity2.class);
        intent.putExtra("pos",postion);
        startActivity(intent);    }

    @Override
    public void onItemselect(int postion, View view) {

    }
}
