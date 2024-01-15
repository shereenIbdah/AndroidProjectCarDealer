package com.example.androidproject;
import android.app.AlertDialog;
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

public class ViewHolder_fav extends RecyclerView.ViewHolder{
    ImageView imageView_fav;
    Button factorynameView_favorite;
    TextView typeView_f , priceView_f, modelView_f ,namee_f ,id_f;

    public ViewHolder_fav(@NonNull View carView) {
        super(carView);
        imageView_fav = carView.findViewById(R.id.imageview_fav);
        factorynameView_favorite = carView.findViewById(R.id.factoryname_fav);
        id_f = carView.findViewById(R.id.id_fav);
        namee_f = carView.findViewById(R.id.namee_fav);
        typeView_f = carView.findViewById(R.id.type_fav);
        priceView_f = carView.findViewById(R.id.price_fav);
        modelView_f = carView.findViewById(R.id.model_fav);
        String email = SignInActivity.emailForProfile;
        ToggleButton reserve = carView.findViewById(R.id.toggleButton4_fav);
        ReservationDataBase reserveDataBase = new ReservationDataBase(carView.getContext(), "Reservation1", null, 1);
        reserve.setOnClickListener(v -> {
            if (reserve.isChecked()) {
                //check if the car is already reserved
                if (reserveDataBase.isReserved(email, id_f.getText().toString())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(carView.getContext());
                    builder.setTitle("Remove from reservations");
                    builder.setMessage("Are you sure you want to remove this car from reservations?");
                    // builder.setIcon(R.drawable.baseline_star_border_24);
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        reserveDataBase.removeReservation(email, id_f.getText().toString());
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
                    String message = "<b>Car factoryname:</b> " + "<font color='#E91E63'>" + factorynameView_favorite.getText() + "</font>" +
                            "<br>" + "<b>Car name:</b> " + "<font color='#E91E63'>" + namee_f.getText() + "</font>" + "<br>" +
                            "<b>Car type:</b> " + "<font color='#E91E63'>" + typeView_f.getText() + "</font>" +
                            "<br>" +
                            "<b>Car price:</b> " + "<font color='#E91E63'>" + priceView_f.getText() + "</font>" +
                            "<br>" +
                            "<b>Car model:</b> " + "<font color='#E91E63'>" + modelView_f.getText() + "</font>" +
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
                                reserveDataBase.insertReservation(email, id_f.getText().toString(), date1, time1);
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


    }

    }

