package com.example.myproject.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myproject.Adapters.categoryadapter;
import com.example.myproject.Adapters.recycleadapter;
import com.example.myproject.MainActivity2;
import com.example.myproject.R;
import com.example.myproject.cart;
import com.example.myproject.classes.category;
import com.example.myproject.classes.firebase;
import com.example.myproject.classes.foodinfo;

import com.example.myproject.classes.itemcart;
import com.example.myproject.recyclerviewselector;
import com.example.myproject.search;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements recyclerviewselector {

    public  List<itemcart> itemcarts;
    TextView textView ;
    int var=1;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

            inflater.inflate(R.menu.menu,menu);
            super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getTitle().toString().equals("Cart")) {
            Intent intent=new Intent(getContext(),cart.class);
            intent.putExtra("list",(Serializable) itemcarts);
            startActivityForResult(intent,1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                List<itemcart> strEditText = (List<itemcart>) data.getSerializableExtra("posi");
                //String value = data.getStringExtra("var");
                for(itemcart it:strEditText)
                {
//                    Toast.makeText(getContext(), it.getPos(), Toast.LENGTH_SHORT).show();

                    int check=Integer.parseInt(it.getPos());
                    if(it.getQuan().equals("0")){
                        itemcarts.remove(check);
                    }
                     else if(itemcarts.size()>check&&check>-1)
                    {
                        itemcarts.remove(check);
                        itemcarts.add(check,new itemcart(it.getName(),it.getPrice(),it.getQuan(),it.getImage()));
                    }

                }

            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    RecyclerView recyclerView,recyclerView1;
    categoryadapter categoryadapte;
    recycleadapter recycleadapte;
    RelativeLayout relativeLayout;
    ImageView imageView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imageView=view.findViewById(R.id.imageView3);
        imageView.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
        });


        List<category> cate=new ArrayList<>();
        cate.add(new category("Pizza",R.drawable.cat_1));
        cate.add(new category("Burger",R.drawable.cat_2));
        cate.add(new category("Momos",R.drawable.cat_3));
        cate.add(new category("Beverage",R.drawable.cat_4));
        cate.add(new category("Dessert",R.drawable.cat_5));


        relativeLayout=view.findViewById(R.id.search);
        relativeLayout.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), search.class);
            intent.putExtra("list",(Serializable)cate);
            startActivity(intent);
        });




        recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        categoryadapte=new categoryadapter(cate,getContext(),this);
        recyclerView.setAdapter(categoryadapte);
        recyclerView.setLayoutManager(manager);
        recyclerView1=view.findViewById(R.id.recyclerView2);
        itemcarts=new ArrayList<>();


        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("user_info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("uname");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name= (String) snapshot.getValue();
                TextView textView=view.findViewById(R.id.textView15);
                textView.setText("Hi "+name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new firebase().readdata(new firebase.datastatus(){
            @Override
            public void dataIsloaded(List<String> key, List<foodinfo> finfo) {
                LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                recycleadapte=new recycleadapter(getContext(),finfo,HomeFragment.this);
                recyclerView1.setAdapter(recycleadapte);
                recyclerView1.setLayoutManager(manager);

            }
        });
        //textView=view.findViewById(R.id.addbutton);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onitemSelect(String postion) {
            Intent intent=new Intent(getContext(), MainActivity2.class);
            intent.putExtra("pos",postion);
            startActivity(intent);

        Toast.makeText(getContext(), postion, Toast.LENGTH_SHORT).show();


    }
    public void add_items(View view1,int pos){
        textView=view1.findViewById(R.id.addbutton1);
        TextView decrement=view1.findViewById(R.id.minus1);
        TextView increment=view1.findViewById(R.id.plus1);
        TextView textView3=view1.findViewById(R.id.textView3a);
        TextView add2=view1.findViewById(R.id.addbutton2);
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



                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("detail").child(String.valueOf(pos+1));
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int image=0;

                        String name,price;
                        image=  snapshot.child("image").getValue(Integer.class);
                        price=snapshot.child("price").getValue(String.class);
                        name=snapshot.child("name").getValue(String.class);
                        itemcarts.add( new itemcart(name,price,String.valueOf(var),image));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                    });


        });
    }
    @Override
    public void onItemselect(int postion,View view1) {
             var=1;
              add_items(view1,postion);

    }
}
