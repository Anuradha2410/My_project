package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myproject.Fragments.Accountfragment;
import com.example.myproject.Fragments.Cartfragment;
import com.example.myproject.Fragments.HomeFragment;
import com.example.myproject.databinding.ActivityMenuBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Menu extends AppCompatActivity {
ActivityMenuBinding binding;

HomeFragment homeFragment=new HomeFragment();
Cartfragment cart=new Cartfragment();
Accountfragment accountfragment=new Accountfragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).commit();

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).commit();
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, cart).commit();
                        break;
                    case R.id.my_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, accountfragment).commit();
                        break;


                }
                return false;
            }
        });
    }
    }
