package com.example.exemple_recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InputFragment extends Fragment {
    private View view;
    private EditText input_field;
    private Button button;
    private InputInterface inputInterface;
    public InputFragment(InputInterface inputInterface) {
        this.inputInterface = inputInterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_input, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input_field = view.findViewById(R.id.input_field);
        button = view.findViewById(R.id.input_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_field.getText() != null){
                    inputInterface.setAmount(input_field.getText().toString());
                }
            }
        });

    }

}


