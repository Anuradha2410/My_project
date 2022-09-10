package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.databinding.ActivityAccountBinding;


import java.util.ArrayList;


public class account extends AppCompatActivity {
    ActivityAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper db;
        ArrayList<EditText> arrayList=new ArrayList();
        arrayList.add(binding.editTextTextPersonName);
        arrayList.add(binding.editTextTextPersonName2);
        arrayList.add(binding.editTextTextEmailAddress);
        arrayList.add(binding.editTextPhone2);

        db = new DBHelper(this);
        binding.button.setOnClickListener(view -> {

            for(EditText e:arrayList){

                String text=e.getText().toString().trim();
                if(text.length()==0){
                    Toast.makeText(this, "Data required in all field", Toast.LENGTH_SHORT).show();
                }
                else if(binding.editTextPhone2.getId()==e.getId()){

                    if(text.length()!=10){
                        e.setError("Invalid number");
                    }
                }
                else if(binding.editTextTextEmailAddress.getId()==e.getId()){

                    if(!Patterns.EMAIL_ADDRESS.matcher(e.getText()).matches()){
                        e.setError("Invalid email address");
                    }
                }

            }

            String username = binding.editTextTextPersonName.getText().toString();
            String password = binding.editTextTextPassword2.getText().toString();
            String address = binding.editTextTextPersonName2.getText().toString();
            long contactno = Long.parseLong(binding.editTextPhone2.getText().toString());
            String email = binding.editTextTextEmailAddress.getText().toString();

            Boolean insert = db.insertuser(username,password,address,email,contactno);
            if(insert==true) {
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
                Intent in =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
            else {
                Toast.makeText(this, "Registered failed", Toast.LENGTH_SHORT).show();
            }

        });
    }
}