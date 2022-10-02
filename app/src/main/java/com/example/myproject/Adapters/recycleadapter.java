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
import com.example.myproject.classes.foodinfo;
import com.example.myproject.recyclerviewselector;

import java.util.List;

public class recycleadapter extends RecyclerView.Adapter<recycleadapter.Viewholder> {
    Context context;
    List<foodinfo> arrayList;
    recyclerviewselector re;




    public recycleadapter(Context context, List<foodinfo> arrayList,recyclerviewselector re) {
        this.context = context;
        this.arrayList = arrayList;
        this.re=re;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.fooddetails,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        foodinfo fi=arrayList.get(position);
        holder.image.setImageResource(fi.getImage());
        holder.name.setText(fi.getName());
        holder.price.setText(fi.getPrice());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView price,add,increment,decrement,textView3;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView2);
            name=itemView.findViewById(R.id.textView19);
            price=itemView.findViewById(R.id.textView12);


            itemView.setOnClickListener(view -> {
                int pos=getAdapterPosition();
                re.onItemselect(pos,itemView);

            });


        }
    }
}
