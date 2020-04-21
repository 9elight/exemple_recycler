package com.example.exemple_recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private RecyclerInterface recyclerInterface;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerInterface.onItemClick(textView.getText().toString());
            }
        });
    }

    public void onBind(String s) {
        textView.setText(s);
    }

    public void initInterface(RecyclerInterface recyclerInterface){
        this.recyclerInterface = recyclerInterface;
    }
}
