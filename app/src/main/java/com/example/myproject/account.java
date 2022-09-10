package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.databinding.ActivityAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class account extends AppCompatActivity {
    ActivityAccountBinding binding;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth=FirebaseAuth.getInstance();



        binding.button.setOnClickListener(view -> {
            String pass=binding.editTextTextPassword2.getText().toString().trim();
            String email=binding.editTextTextEmailAddress.getText().toString().trim();
            String name=binding.editTextTextPersonName.getText().toString().trim();
            long phone=Long.parseLong(binding.editTextPhone2.getText().toString());

                if(email.isEmpty()){
                    binding.editTextTextEmailAddress.setError("Email is required");
                    binding.editTextTextEmailAddress.requestFocus();
                }
                if(pass.isEmpty()){
                    binding.editTextTextPassword2.setError("Password is required");
                    binding.editTextTextPassword2.requestFocus();

                }
                if(name.isEmpty()){
                    binding.editTextTextPersonName.setError("Name is required");
                    binding.editTextTextPersonName.requestFocus();
                }
                if(String.valueOf(phone).isEmpty()){
                    binding.editTextPhone2.setError("Phone is required");
                    binding.editTextPhone2.requestFocus();
                }

                if(String.valueOf(phone).length()!=10){
                    binding.editTextPhone2.setError("Invalid number");
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                     binding.editTextTextEmailAddress.setError("Invalid email address");
                }
                if(pass.length()<6){
                    binding.editTextTextPassword2.setError("Password must be atleast of 6 letters");
                }
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            user u=new user(
                                    name,
                                    email,
                                    pass,
                                    phone
                            );
                            FirebaseDatabase.getInstance().getReference("user_info").child(String.valueOf(phone)).setValue(u);
                            finish();
                            startActivity(new Intent(getApplicationContext(),Menu.class));
                        }
                        else{

                            Toast.makeText(account.this, "Error in signup", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        });
        binding.textView5.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(account.this,MainActivity.class));
        });
    }
}