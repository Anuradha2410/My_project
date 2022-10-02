package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {

                 //   Toast.makeText(splash.this, String.valueOf(R.raw.), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(splash.this,MainActivity.class));
                }
            }
        }).start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}