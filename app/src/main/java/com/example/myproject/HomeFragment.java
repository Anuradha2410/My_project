package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myproject.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    Intent in;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView view1=getView().findViewById(R.id.textView2);
        TextView view2=getView().findViewById(R.id.textView3);
        TextView view3=getView().findViewById(R.id.textView4);
        TextView view4=getView().findViewById(R.id.textView5);

        view1.setOnClickListener(view5 -> {
            in=new Intent(getContext(),snacks.class);
            startActivity(in);
        });
//        view2.setOnClickListener(this);
//        view3.setOnClickListener(this);
//        view4.setOnClickListener(this);

        super.onViewCreated(view, savedInstanceState);
    }
}
