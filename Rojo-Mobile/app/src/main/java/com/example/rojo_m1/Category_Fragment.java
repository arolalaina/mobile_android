package com.example.rojo_m1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Category_Fragment extends Fragment {

    private Button restaurant_button, hopital_button, hotel_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        restaurant_button = (Button) rootView.findViewById(R.id.restaurant_button);
        hopital_button = (Button) rootView.findViewById(R.id.hopital_button);
        hotel_button = (Button) rootView.findViewById(R.id.hotel_button);


        restaurant_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra("REFERENCE", "0");
                startActivity(intent);
            }
        });
        hopital_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra("REFERENCE", "1");
                startActivity(intent);
            }
        });

        hotel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra("REFERENCE", "2");
                startActivity(intent);
            }
        });

        return rootView;


    }





}
