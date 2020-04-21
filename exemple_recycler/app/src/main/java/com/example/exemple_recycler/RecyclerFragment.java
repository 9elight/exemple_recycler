package com.example.exemple_recycler;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecyclerFragment extends Fragment implements RecyclerInterface {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    public RecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        addToList(100);
    }

    private void initRecycler(){
        recyclerView = view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter();
        adapter.initInterface(this);
        recyclerView.setAdapter(adapter);
    }

    public void addToList(int amount){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= amount; i++) {
            list.add("item " + i);
        }
        adapter.updateList(list);
    }

    @Override
    public void onItemClick(String s) {
        Intent intent = new Intent(getActivity(),DetailActivity.class);
        intent.putExtra("key",s);
        getActivity().startActivity(intent);
    }
}
