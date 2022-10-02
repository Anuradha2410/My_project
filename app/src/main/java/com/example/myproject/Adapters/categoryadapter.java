package com.example.myproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.classes.category;
import com.example.myproject.recyclerviewselector;

import java.util.ArrayList;
import java.util.List;

public class categoryadapter extends RecyclerView.Adapter<categoryadapter.viewHolder> {

    List<category> cate=new ArrayList<>();
    Context context;
    recyclerviewselector re;
    public static int i;

    public categoryadapter(List<category> cate, Context context,recyclerviewselector re) {
        this.cate = cate;
        this.context = context;
        this.re=re;
    }
    public void setfoodinfo(List<category> fin){
        cate=fin;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.category,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        category cy= cate.get(position);
        holder.imageView.setImageResource(cy.getImg());

        holder.textView.setText(cy.getIntro());


    }

    @Override
    public int getItemCount() {
        return cate.size();
    }

    public class  viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView6);
            textView=itemView.findViewById(R.id.textView4);
            itemView.setOnClickListener(view -> {
                String s=textView.getText().toString();

                    re.onitemSelect(s);
            });

        }
    }

 }
