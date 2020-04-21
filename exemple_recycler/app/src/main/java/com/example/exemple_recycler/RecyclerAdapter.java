package com.example.exemple_recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<String> list = new ArrayList<>();
    private RecyclerInterface recyclerInterface;

    public void updateList(ArrayList list){
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public void initInterface(RecyclerInterface recyclerInterface){
        this.recyclerInterface = recyclerInterface;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder,parent,false);
        RecyclerViewHolder vh = new RecyclerViewHolder(view);
        vh.initInterface(recyclerInterface);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
