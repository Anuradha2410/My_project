package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.Adapters.Cartrecycleradapter;
import com.example.myproject.Fragments.HomeFragment;
import com.example.myproject.classes.category;
import com.example.myproject.classes.foodinfo;
import com.example.myproject.classes.itemcart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity implements recyclerviewselector {
RecyclerView recyclerView;
Cartrecycleradapter cartrecycleradapter;
    List<itemcart> list,list1,list2;
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putSerializable("list1", (Serializable) list);
        Toast.makeText(this, "instalesave", Toast.LENGTH_SHORT).show();
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        list2=savedInstanceState.getParcelable("list1");
        Toast.makeText(this, "instalerestore", Toast.LENGTH_SHORT).show();

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", String.valueOf(list));
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent=getIntent();
        list= (List<itemcart>) intent.getSerializableExtra("list");

//        ListView view=findViewById(R.id.a1);
//
//        ArrayAdapter<itemcart> arrayAdapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
//        view.setAdapter(arrayAdapter);
        if(savedInstanceState==null){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            recyclerView=findViewById(R.id.recycleviewitem);
            cartrecycleradapter=new Cartrecycleradapter(this,list,cart.this);
            recyclerView.setAdapter(cartrecycleradapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            list1=new ArrayList<>();

            list1.addAll(list);
        }
        else{
            Toast.makeText(this, "not null", Toast.LENGTH_SHORT).show();

            list.addAll(list2);
            recyclerView=findViewById(R.id.recycleviewitem);
            cartrecycleradapter=new Cartrecycleradapter(this,list,cart.this);
            recyclerView.setAdapter(cartrecycleradapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }



    }

    public int var;
    public String pos1="-1";
    public void add_items(View view1,int pos){


        TextView decrement=view1.findViewById(R.id.addi2);
        TextView increment=view1.findViewById(R.id.addi3);
        itemcart itemcart=list.get(pos);

//        Toast.makeText(this, String.valueOf(pos), Toast.LENGTH_SHORT).show();
        TextView textView3=view1.findViewById(R.id.addq);
        var=Integer.parseInt(itemcart.getQuan());


        textView3.setText(String.valueOf(var));

        increment.setOnClickListener(view2 -> {
                var++;
                textView3.setText(String.valueOf(var));
                pos1=String.valueOf(pos);
                list1.set(pos,new itemcart(itemcart.getName(),itemcart.getPrice(),String.valueOf(var),itemcart.getImage(),pos1));


            });
            decrement.setOnClickListener(view2 -> {
                if (var > 1) {
                    var--;
                    textView3.setText(String.valueOf(var));
                    pos1=String.valueOf(pos);
                    list1.set(pos,new itemcart(itemcart.getName(),itemcart.getPrice(),String.valueOf(var),itemcart.getImage(),pos1));


                }
                else{
//                    var--;
//                    Toast.makeText(this, String.valueOf(), Toast.LENGTH_SHORT).show();
                    pos1=String.valueOf(pos);
                    list1.set(pos,new itemcart(itemcart.getName(),itemcart.getPrice(),"0",itemcart.getImage(),pos1));
                    list.remove(pos);

                    recyclerView=findViewById(R.id.recycleviewitem);
                    cartrecycleradapter=new Cartrecycleradapter(this,list,cart.this);
                    recyclerView.setAdapter(cartrecycleradapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));


                }

            });


//



    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("posi", (Serializable) list1);
        //intent.putExtra("var",String.valueOf(var));
        setResult(RESULT_OK,intent);

        super.onBackPressed();
    }

    @Override
    public void onitemSelect(String postion) {

    }



    @Override
    public void onItemselect(int postion, View view) {
        add_items(view,postion);
    }
}
