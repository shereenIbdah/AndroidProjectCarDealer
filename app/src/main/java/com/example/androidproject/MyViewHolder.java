package com.example.androidproject;

import android.content.ClipData;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    Button nameView;
    TextView typeView , priceView , modelView;



    public MyViewHolder(@NonNull View carView) {
        super(carView);
        imageView = carView.findViewById(R.id.imageview);
        nameView = carView.findViewById(R.id.name);
        typeView = carView.findViewById(R.id.type);
        priceView = carView.findViewById(R.id.price);
        modelView = carView.findViewById(R.id.model);


        ToggleButton favorite = carView.findViewById(R.id.toggleButton3);
        ToggleButton reserve = carView.findViewById(R.id.toggleButton4);
        Button name = carView.findViewById(R.id.name);
        favorite.setOnClickListener(v -> {
            if (favorite.isChecked()) {
                favorite.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_24));
                Toast.makeText(carView.getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
            } else {
                favorite.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
                Toast.makeText(carView.getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
            }
        });
        reserve.setOnClickListener(v -> {
            if (reserve.isChecked()) {
                reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));
                Toast.makeText(carView.getContext(), "Reserved", Toast.LENGTH_SHORT).show();
            } else {
                reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_border_24));
                Toast.makeText(carView.getContext(), "Reservation canceled", Toast.LENGTH_SHORT).show();
            }
        });
        name.setOnClickListener(v -> {
            Toast.makeText(carView.getContext(), "You clicked on " + nameView.getText(), Toast.LENGTH_SHORT).show();
        });
    }

}