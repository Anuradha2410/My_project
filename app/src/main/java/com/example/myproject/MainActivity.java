package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper db;
        db = new DBHelper(this);
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.editTextTextPersonName3.getText().toString();
                String password = binding.editTextTextPassword.getText().toString();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean checkpassword = db.checkpassword(username,password);
                    if(checkpassword==true)
                    {
                        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent in =new Intent(getApplicationContext(),Menu.class);
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.button6.setOnClickListener(view -> {
            Intent in =new Intent(getApplicationContext(), account.class);
            startActivity(in);
        });

    }
}