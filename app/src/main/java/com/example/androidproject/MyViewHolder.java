package com.example.androidproject;

import android.app.AlertDialog;
import android.provider.ContactsContract;
import android.database.Cursor;
import android.text.Html;
import android.view.View;
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
    TextView typeView, priceView, modelView, namee, id ,fuel;

    ToggleButton favorite;

    public MyViewHolder(@NonNull View carView) {
        super(carView);
        imageView = carView.findViewById(R.id.imageview);
        factorynameView = carView.findViewById(R.id.factoryname);
        id = carView.findViewById(R.id.id);
        namee = carView.findViewById(R.id.namee);
        typeView = carView.findViewById(R.id.type);
        priceView = carView.findViewById(R.id.price);
        modelView = carView.findViewById(R.id.model);
        fuel = carView.findViewById(R.id.fuel);
        String email = SignInActivity.emailForProfile;
        CarsDataBase carsDataBase = new CarsDataBase(carView.getContext(), "cars_menu", null, 1);

        //define data base of reservations
        ReservationDataBase reserveDataBase = new ReservationDataBase(carView.getContext(), "Reservation1", null, 1);
        //remove all the reservations
        FavoriteDataBase favoriteDataBase1 = new FavoriteDataBase(carView.getContext(), "FavCar", null, 1);

        favorite = carView.findViewById(R.id.toggleButton3);
        ToggleButton reserve = carView.findViewById(R.id.toggleButton4);

        reserve.setOnClickListener(v -> {
            if (reserve.isChecked()) {
                //check if the car is already reserved
                if (reserveDataBase.isReserved(email, id.getText().toString())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                    builder.setTitle("Remove from reservations");
                    builder.setMessage("Are you sure you want to remove this car from reservations?");
                    // builder.setIcon(R.drawable.baseline_star_border_24);
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        reserveDataBase.removeReservation(email, id.getText().toString());
                        reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_border_24));
                        Toast.makeText(carView.getContext(), "Removed from reservations", Toast.LENGTH_SHORT).show();
                    });

                    builder.setNegativeButton("No", (dialog, which) -> {
                        reserve.setChecked(true); // Keep the toggle button in the checked state
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    //tween animation for the button
                    AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                    builder.setTitle("Reserve car");

                    // Show the details of the car
                    String message = "<b>Car factoryname:</b> " + "<font color='#E91E63'>" + factorynameView.getText() + "</font>" +
                            "<br>" + "<b>Car name:</b> " + "<font color='#E91E63'>" + namee.getText() + "</font>" + "<br>" +
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
                        Date date = new Date();
                        String date1 = date.toString();
                        //get only the year month and day
                        date1 = date1.substring(0, 10);
                        Time time = new Time(date.getTime());
                        //set the time only hours and minutes and seconds
                        String time1 = time.toString();
                        time1 = time1.substring(0, 8);
                        reserveDataBase.insertReservation(email, id.getText().toString(), date1, time1);
                        Toast.makeText(carView.getContext(), "Reserved Successful", Toast.LENGTH_SHORT).show();
                                reserve.startAnimation(AnimationUtils.loadAnimation(carView.getContext(), R.anim.scale));
                            }
                    );
                    builder.setNegativeButton("No", (dialog, which) -> {
                        reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_border_24));
                        Toast.makeText(carView.getContext(), "Not Reserved", Toast.LENGTH_SHORT).show();
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            }
        });


        favorite.setOnClickListener(v -> {
            if (favorite.isChecked()) {
                //check if the car is already in favorites
                if (favoriteDataBase1.isFavorite(email, id.getText().toString())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                    builder.setTitle("Remove from favorites");
                    builder.setMessage("Are you sure you want to remove this car from favorites?");
                    builder.setIcon(R.drawable.baseline_favorite_border_24);
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        favoriteDataBase1.removeFavorite(email, id.getText().toString());
                        favorite.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
                        Toast.makeText(carView.getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                        favorite.setChecked(true); // Keep the toggle button in the checked state
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                    builder.setTitle("Add to favorites");
                    builder.setMessage("Are you sure you want to add this car to favorites?");
                    builder.setIcon(R.drawable.baseline_favorite_border_24);
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        favoriteDataBase1.insertFavorite(email, id.getText().toString());
                        Toast.makeText(carView.getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                        favorite.startAnimation(AnimationUtils.loadAnimation(carView.getContext(), R.anim.scale));
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                        favorite.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
                        Toast.makeText(carView.getContext(), "Not Added", Toast.LENGTH_SHORT).show();

                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }

        });


        detailsOfSelectedCarFragment detailOfSelectedCarFragment = new detailsOfSelectedCarFragment();
        factorynameView.setOnClickListener(v -> {
            Toast.makeText(carView.getContext(), "You clicked on " + factorynameView.getText(), Toast.LENGTH_SHORT).show();
        });
    }
}