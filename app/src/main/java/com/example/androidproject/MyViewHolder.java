package com.example.androidproject;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.Date;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    Button factorynameView;
    TextView typeView, priceView, modelView ,name ;
    String id;

    public MyViewHolder(@NonNull View carView) {
        super(carView);
        imageView = carView.findViewById(R.id.imageview);
        factorynameView = carView.findViewById(R.id.factoryname);
        name = carView.findViewById(R.id.namee);
        typeView = carView.findViewById(R.id.type);
        priceView = carView.findViewById(R.id.price);
        modelView = carView.findViewById(R.id.model);
        ReserveDataBase reserveDataBase = new ReserveDataBase(carView.getContext(), "ReserveCars", null, 1);
        ToggleButton favorite = carView.findViewById(R.id.toggleButton3);
        ToggleButton reserve = carView.findViewById(R.id.toggleButton4);
        Button name = carView.findViewById(R.id.factoryname);

        reserve.setOnClickListener(v -> {
            // Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
            builder.setTitle("Reserve car");

            // Show the details of the car
            String message = "<b>Car name:</b> " + "<font color='#E91E63'>" + factorynameView.getText() + "</font>" +
                    "<br>" +
                    "<b>Car type:</b> " + "<font color='#E91E63'>" + typeView.getText() + "</font>" +
                    "<br>" +
                    "<b>Car price:</b> " + "<font color='#E91E63'>" + priceView.getText() + "</font>" +
                    "<br>" +
                    "<b>Car model:</b> " + "<font color='#E91E63'>" + modelView.getText() + "</font>" +
                    "\nAre you sure you want to reserve this car?";

            // Set the styled message to the AlertDialog
            builder.setMessage(Html.fromHtml(message));
            builder.setIcon(R.drawable.baseline_save_alt_24);
            builder.setPositiveButton("Yes", (dialog, which) -> {
               // if (!isCarAlreadyReserved(reserveDataBasee)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    Date date = new Date(currentTimeMillis);
                    Time time = new Time(currentTimeMillis);
                    // //id TEXT PRIMARY KEY,factoryname TEXT, type TEXT, model TEXT, price TEXT, name TEXT, time TEXT
                    reserveDataBase.insertCar(typeView.getText().toString(),factorynameView.getText().toString(), modelView.getText().toString(), priceView.getText().toString(), name.getText().toString(), time.toString());
                    reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));
                    Toast.makeText(carView.getContext(), "Reserved", Toast.LENGTH_SHORT).show();
              //  } else {
                    //Toast.makeText(carView.getContext(), "Car is already reserved", Toast.LENGTH_SHORT).show();
              //  }
            });

            builder.setNegativeButton("No", (dialog, which) -> {
                if (reserve.isChecked()) {
                    reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_border_24));
                    Toast.makeText(carView.getContext(), "Removed from reservations", Toast.LENGTH_SHORT).show();
                } else {
                    reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));
                    Toast.makeText(carView.getContext(), "Reserved", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        favorite.setOnClickListener(v -> {
            if (favorite.isChecked()) {
                //create a tween animation for scale
                Animation animation = AnimationUtils.loadAnimation(carView.getContext(), R.anim.scale);
                favorite.startAnimation(animation);
                favorite.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_24));
                Toast.makeText(carView.getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
            } else {
                favorite.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
                Toast.makeText(carView.getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
            }
        });


        detailsOfSelectedCarFragment detailOfSelectedCarFragment = new detailsOfSelectedCarFragment();
       // final detailsOfSelectedCarFragment.communicator communicator = (detailsOfSelectedCarFragment.communicator) carView.getContext();
        name.setOnClickListener(v -> {
            //detailOfSelectedCarFragment.trueOrFalse(true);
           // communicator.respond(nameView.getText().toString());
            Toast.makeText(carView.getContext(), "You clicked on " + factorynameView.getText(), Toast.LENGTH_SHORT).show();
        });
    }


    /**private boolean isCarAlreadyReserved(reserveDataBase reserveDataBase) {
        // Check if the car is already in the reservations database
        Cursor cursor = reserveDataBase.getCar(id);
        return cursor.getCount() > 0;
    }**/
}
