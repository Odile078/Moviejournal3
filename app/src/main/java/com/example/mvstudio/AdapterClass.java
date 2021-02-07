package com.example.mvstudio;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    ArrayList<Movies> list;
    public  AdapterClass( ArrayList<Movies> list){
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.category.setText(list.get(position).getCategory());
        holder.detail.setText(list.get(position).getDetail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView name, category, detail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.viewname);
            category = itemView.findViewById(R.id.viewcategory);
            detail = itemView.findViewById(R.id.viewdetail);
        }
    }
}

