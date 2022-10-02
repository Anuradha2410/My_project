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
import com.example.myproject.classes.itemlist;
import com.example.myproject.recyclerviewselector;

import java.util.ArrayList;
import java.util.List;

public class itemadapter extends RecyclerView.Adapter<itemadapter.viewholder> {
    Context context;
    List<itemlist> itemlists=new ArrayList<>();
    recyclerviewselector re;

    public itemadapter(Context context, List<itemlist> itemlists,recyclerviewselector re) {
        this.context = context;
        this.itemlists = itemlists;
        this.re=re;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemlist,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        itemlist it=itemlists.get(position);
        holder.image.setImageResource(it.getImage());
        holder.name.setText(it.getName());
        holder.price.setText(it.getPrice());
        holder.desc.setText(it.getDescription());

    }

    @Override
    public int getItemCount() {
        return itemlists.size();
    }

    public class  viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView price;
        TextView desc;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView2);
            name=itemView.findViewById(R.id.textView19);
            price=itemView.findViewById(R.id.textView12);
            desc=itemView.findViewById(R.id.textView13);
            itemView.setOnClickListener(view -> {
                int pos=getAdapterPosition();
                re.onItemselect(pos,itemView);

            });

        }
    }
}
