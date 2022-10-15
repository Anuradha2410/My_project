package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myproject.Adapters.itemadapter;
import com.example.myproject.Fragments.HomeFragment;
import com.example.myproject.classes.itemcart;
import com.example.myproject.classes.itemlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements  recyclerviewselector{
RecyclerView recyclerView;
    int var=1;
    List<itemcart> itemcarts;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getTitle().toString().equals("Cart")) {
            Intent intent=new Intent(getApplicationContext(), cart.class);
//            Toast.makeText(getContext(), itemcarts.toString(), Toast.LENGTH_SHORT).show();
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra("list",(Serializable) itemcarts);
//            Toast.makeText(getContext(), itemcarts.toString(), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    String item_name="";
    List<itemlist> pizza,burger,beverage,dessert,hotdogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        item_name=intent.getStringExtra("pos");
        recyclerView=findViewById(R.id.recycleitem);
        itemcarts=new ArrayList<>();
        if(item_name.equals("Pizza")){
            pizza=new ArrayList<>();
            pizza.add(new itemlist(R.drawable.logo,"Margherita Pizza","120",""));
            pizza.add(new itemlist(R.drawable.logo,"Onion Pizza","70",""));
            pizza.add(new itemlist(R.drawable.pop_3,"farmhouse Pizza","200",""));
            pizza.add(new itemlist(R.drawable.pop_3,"panner special Pizza","270",""));
            pizza.add(new itemlist(R.drawable.pop_3,"crisp capsicum and tomato Pizza","350",""));
            pizza.add(new itemlist(R.drawable.pop_3,"vegetable loaded Pizza","250",""));
            pizza.add(new itemlist(R.drawable.pop_3,"Mexican green wave Pizza","450",""));
            itemadapter iadapter=new itemadapter(this,pizza,this);
            recyclerView.setAdapter(iadapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
        else if(item_name.equals("Burger")){
            burger=new ArrayList<>();
            burger.add(new itemlist(R.raw.pop_2,"Tikki twist","50",""));
            burger.add(new itemlist(R.raw.pop_2,"veg crispy","50",""));
            burger.add(new itemlist(R.raw.pop_2,"Double crispy","80",""));
            burger.add(new itemlist(R.raw.pop_2,"schewan paneer burger","150",""));
            burger.add(new itemlist(R.raw.pop_2,"classic veggie","90",""));
            itemadapter iadapter=new itemadapter(this,burger,this);
            recyclerView.setAdapter(iadapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


        }
    }

    @Override
    public void onitemSelect(String postion) {

    }
    public void add_items(View view1,int pos){
        TextView textView=view1.findViewById(R.id.addbutton1);
        TextView decrement=view1.findViewById(R.id.minus1);
        TextView increment=view1.findViewById(R.id.plus1);
        TextView textView3=view1.findViewById(R.id.textView3a);
        TextView add2=view1.findViewById(R.id.addbutton3);
        textView.setOnClickListener(view -> {

            decrement.setVisibility(View.VISIBLE);
            increment.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            textView3.setText(String.valueOf(var));
            textView.setVisibility(View.INVISIBLE);
            add2.setVisibility(View.VISIBLE);

            increment.setOnClickListener(view2 -> {
                var++;
                textView3.setText(String.valueOf(var));

            });
            decrement.setOnClickListener(view2 -> {
                if (var > 0) {
                    var--;
                    textView3.setText(String.valueOf(var));
                }
            });

            add2.setOnClickListener(view2 -> {
                increment.setVisibility(View.INVISIBLE);
                decrement.setVisibility(View.INVISIBLE);
                add2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.setText("Added to cart");

                if(item_name.equals("Pizza")){
                    itemlist it=pizza.get(pos);
                    itemcarts.add(new itemcart(it.getName(),it.getPrice(),String.valueOf(var),it.getImage()));


                }else if(item_name.equals("Burger")){
                    itemlist it=burger.get(pos);
                    itemcarts.add(new itemcart(it.getName(),it.getPrice(),String.valueOf(var),it.getImage()));

                }




            });


        });
    }
    @Override
    public void onItemselect(int postion, View view1) {
            var=1;
            add_items(view1,postion);
        }


    }
