package com.example.androidproject;

import android.app.AlertDialog;
import android.database.Cursor;
import android.text.Html;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.Date;

public class ViewHolder_offers extends RecyclerView.ViewHolder {

    ImageView imageView_offers;
    Button factorynameView_offers;
    TextView typeView_offers;
    TextView priceView_offers;
    TextView modelView_offers;
    TextView namee_offers;
    TextView id_offers;
    ToggleButton favorite_offers, reserve_offers;

    public ViewHolder_offers(android.view.View carView) {
        super(carView);
        imageView_offers = carView.findViewById(R.id.imageview_offers);
        factorynameView_offers = carView.findViewById(R.id.factoryname_offers);
        id_offers = carView.findViewById(R.id.id_offers);
        namee_offers = carView.findViewById(R.id.namee_offers);
        typeView_offers = carView.findViewById(R.id.type_offers);
        priceView_offers = carView.findViewById(R.id.price_offers);
        modelView_offers = carView.findViewById(R.id.model_offers);
        favorite_offers = carView.findViewById(R.id.toggleButton3_offers);
        reserve_offers = carView.findViewById(R.id.toggleButton4_offers);
        ReservationDataBase reserveDataBase = new ReservationDataBase(carView.getContext(), "reservation1", null, 1);
        FavoriteDataBase favoriteDataBase1 = new FavoriteDataBase(carView.getContext(), "FavcarDataBase", null, 1);
        String email = SignInActivity.emailForProfile;

        reserve_offers.setOnClickListener(v -> {
            if (reserve_offers.isChecked()) {
                //check if the car is already reserved
                if (reserveDataBase.isReserved(email, id_offers.getText().toString())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                    builder.setTitle("Remove from reservations");
                    builder.setMessage("Are you sure you want to remove this car from reservations?");
                    // builder.setIcon(R.drawable.baseline_star_border_24);
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        reserveDataBase.removeReservation(email, id_offers.getText().toString());
                        reserve_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_border_24));
                        Toast.makeText(carView.getContext(), "Removed from reservations", Toast.LENGTH_SHORT).show();
                    });

                    builder.setNegativeButton("No", (dialog, which) -> {
                        reserve_offers.setChecked(true); // Keep the toggle button in the checked state
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    //tween animation for the button
                    AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                    builder.setTitle("Reserve car");

                    // Show the details of the car
                    String message = "<b>Car factoryname:</b> " + "<font color='#E91E63'>" + factorynameView_offers.getText() + "</font>" +
                            "<br>" + "<b>Car name:</b> " + "<font color='#E91E63'>" + namee_offers.getText() + "</font>" + "<br>" +
                            "<b>Car type:</b> " + "<font color='#E91E63'>" + typeView_offers.getText() + "</font>" +
                            "<br>" +
                            "<b>Car price:</b> " + "<font color='#E91E63'>" + priceView_offers.getText() + "</font>" +
                            "<br>" +
                            "<b>Car model:</b> " + "<font color='#E91E63'>" + modelView_offers.getText() + "</font>" +
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
                                reserveDataBase.insertReservation(email, id_offers.getText().toString(), date1, time1);
                                Toast.makeText(carView.getContext(), "Reserved Successful", Toast.LENGTH_SHORT).show();
                                reserve_offers.startAnimation(AnimationUtils.loadAnimation(carView.getContext(), R.anim.scale));
                            }
                    );
                    builder.setNegativeButton("No", (dialog, which) -> {
                        reserve_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_border_24));
                        Toast.makeText(carView.getContext(), "Not Reserved", Toast.LENGTH_SHORT).show();
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            }
        });
        favorite_offers.setOnClickListener(v -> {
        if (favorite_offers.isChecked()) {
            //check if the car is already in favorites
            if (favoriteDataBase1.isFavorite(email, id_offers.getText().toString())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                builder.setTitle("Remove from favorites");
                builder.setMessage("Are you sure you want to remove this car from favorites?");
                builder.setIcon(R.drawable.baseline_favorite_border_24);
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    favoriteDataBase1.removeFavorite(email, id_offers.getText().toString());
                    favorite_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
                    Toast.makeText(carView.getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    favorite_offers.setChecked(true); // Keep the toggle button in the checked state
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                builder.setTitle("Add to favorites");
                builder.setMessage("Are you sure you want to add this car to favorites?");
                builder.setIcon(R.drawable.baseline_favorite_border_24);
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    favoriteDataBase1.insertFavorite(email, id_offers.getText().toString());
                    Toast.makeText(carView.getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                    favorite_offers.startAnimation(AnimationUtils.loadAnimation(carView.getContext(), R.anim.scale));
                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    favorite_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
                    Toast.makeText(carView.getContext(), "Not Added", Toast.LENGTH_SHORT).show();

                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    });


}
}
