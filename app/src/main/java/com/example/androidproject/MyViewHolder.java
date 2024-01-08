package com.example.androidproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView,typeView;

    public MyViewHolder(@NonNull View carView) {
        super(carView);
        imageView = carView.findViewById(R.id.imageview);
        nameView = carView.findViewById(R.id.name);
        typeView = carView.findViewById(R.id.type);
        ToggleButton favorite = carView.findViewById(R.id.toggleButton3);
        ToggleButton reserve = carView.findViewById(R.id.toggleButton4);
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
    }

}