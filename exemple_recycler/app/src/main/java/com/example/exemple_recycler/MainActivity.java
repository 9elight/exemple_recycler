package com.example.exemple_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements InputInterface {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RecyclerFragment recyclerFragment;
    private InputFragment inputFragment;
    private int itemAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        recyclerFragment = new RecyclerFragment();
        inputFragment = new InputFragment(this);
        transaction.add(R.id.recycler_container,recyclerFragment);
        transaction.add(R.id.input_container,inputFragment);
        transaction.commit();
    }

    @Override
    public void setAmount(String i) {
       itemAmount = Integer.parseInt(i);
       recyclerFragment.addToList(itemAmount);
    }
}
