package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;

public class cart extends AppCompatActivity implements recyclerviewselector {
RecyclerView recyclerView;
Cartrecycleradapter cartrecycleradapter;
    List<itemcart> list;
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

        recyclerView=findViewById(R.id.recycleviewitem);
        cartrecycleradapter=new Cartrecycleradapter(this,list,cart.this);
        recyclerView.setAdapter(cartrecycleradapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    public int var;
    public String pos1="-1";
    public void add_items(View view1,int pos){
        HomeFragment hf=new HomeFragment();

        TextView decrement=view1.findViewById(R.id.addi2);
        TextView increment=view1.findViewById(R.id.addi3);
        itemcart itemcart=list.get(pos);

        TextView textView3=view1.findViewById(R.id.addq);
        var=Integer.parseInt(itemcart.getQuan());


            textView3.setText(String.valueOf(var));
            increment.setOnClickListener(view2 -> {
                var++;
                textView3.setText(String.valueOf(var));

            });
            decrement.setOnClickListener(view2 -> {
                if (var > 1) {
                    var--;
                    textView3.setText(String.valueOf(var));
                }
                else{

                    list.remove(pos);
                    pos1=String.valueOf(pos);
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
        intent.putExtra("posi",pos1);
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