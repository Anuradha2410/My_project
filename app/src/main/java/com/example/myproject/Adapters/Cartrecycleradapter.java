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
import com.example.myproject.classes.itemcart;
import com.example.myproject.recyclerviewselector;

import java.util.List;

public class Cartrecycleradapter extends RecyclerView.Adapter<Cartrecycleradapter.viewholder> {
    Context context;
    List<itemcart> arrayList;

    recyclerviewselector re;
    public Cartrecycleradapter(Context context, List<itemcart> arrayList,recyclerviewselector re) {
        this.context = context;
        this.arrayList = arrayList;
        this.re=re;

    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cartitem,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        itemcart fi=arrayList.get(position);

        holder.image.setImageResource(fi.getImage());
        holder.name.setText(fi.getName());
        holder.price.setText(fi.getPrice());
        holder.add.setText(fi.getQuan());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView price,add;
        public viewholder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.imageView2);
            name=itemView.findViewById(R.id.textView19);
            price=itemView.findViewById(R.id.textView12);
            add=itemView.findViewById(R.id.addq);
            itemView.setOnClickListener(view -> {
                int pos=getAdapterPosition();
                re.onItemselect(pos,itemView);
            });

        }
    }
}
