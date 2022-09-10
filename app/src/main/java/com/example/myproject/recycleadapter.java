package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recycleadapter extends RecyclerView.Adapter<recycleadapter.ViewHolder> {
    Context context;
    List<foodinfo> arrayList;


    public recycleadapter(Context context, List<foodinfo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.fooddetails,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        foodinfo fi=arrayList.get(position);
//        holder.image.setImageResource(fi.getImage());
        holder.name.setText(fi.getName());
        holder.price.setText(fi.getPrice());
        holder.desc.setText(fi.getDescription());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView price;
        TextView desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.textView11);
            price=itemView.findViewById(R.id.textView12);
            desc=itemView.findViewById(R.id.textView13);

        }
    }
}
