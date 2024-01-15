package com.example.androidproject;

import android.app.AlertDialog;
import android.provider.ContactsContract;
import android.database.Cursor;
import android.text.Html;
import android.view.View;
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
    TextView typeView, priceView, modelView ,namee ,id;

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
        String email = SignInActivity.emailForProfile;
        CarsDataBase carsDataBase = new CarsDataBase(carView.getContext(), "cars", null, 1);
        //define data base of reservations
        ReservationDataBase reserveDataBase = new ReservationDataBase(carView.getContext(), "Reservation", null, 1);
        //remove all the reservations
        DataBasefavorites favoriteDataBase = new DataBasefavorites(carView.getContext(), "FAVcars", null, 1);

        favorite = carView.findViewById(R.id.toggleButton3);
        ToggleButton reserve = carView.findViewById(R.id.toggleButton4);

        reserve.setOnClickListener(v -> {
            // Alert Dialog
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
                // check if the car is already reserved this car
                if(reserveDataBase.isReserved(email,id.getText().toString())){
                    Toast.makeText(carView.getContext(), "Car is already reserved", Toast.LENGTH_SHORT).show();
                    reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));

                }else {
                    // Add the car to the reservations database by the user email
                    //get the current date and time
                    Date date = new Date();
                    String date1 = date.toString();
                    //get only the year month and day
                    date1 = date1.substring(0, 10);
                    Time time = new Time(date.getTime());
                    //set the time only hours and minutes and seconds
                    String time1 = time.toString();
                    time1 = time1.substring(0, 8);
                    reserveDataBase.insertReservation(email, id.getText().toString(), date1, time1);
                    reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));
                    Toast.makeText(carView.getContext(), "Reserved", Toast.LENGTH_SHORT).show();
                }

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
                if (!isCarAlreadyInFavorites(favoriteDataBase)) {
                    favoriteDataBase.insertCar(id.getText().toString(), typeView.getText().toString(), factorynameView.getText().toString(), modelView.getText().toString(), priceView.getText().toString(), namee.getText().toString());
                    favorite.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_24));
                    Toast.makeText(carView.getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(carView.getContext(), "Car is already in favorites", Toast.LENGTH_SHORT).show();
                }
            } else {
                removeFromFavorites(favoriteDataBase);
            }
        });

        detailsOfSelectedCarFragment detailOfSelectedCarFragment = new detailsOfSelectedCarFragment();
        factorynameView.setOnClickListener(v -> {
            Toast.makeText(carView.getContext(), "You clicked on " + factorynameView.getText(), Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isCarAlreadyInFavorites(DataBasefavorites favoriteDataBase) {
        // Check if the car is already in the favorites database
        Cursor cursor = favoriteDataBase.getCar(id.getText().toString());
        return cursor.getCount() > 0;
    }

//    private boolean isCarAlreadyReserved(ReservationDataBase reserveDataBase) {
//        // Check if the car is already in the reservations database
//        Cursor cursor = reserveDataBase.getCar(id.getText().toString());
//        return cursor.getCount() > 0;
//    }


    private void removeFromFavorites(DataBasefavorites favoriteDataBase) {
        // Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
        builder.setTitle("Remove from Favorites");
        builder.setMessage("Are you sure you want to remove this car from favorites?");
        builder.setIcon(R.drawable.baseline_favorite_border_24);

        builder.setPositiveButton("Yes", (dialog, which) -> {
            favoriteDataBase.removeCar(id.getText().toString());
            favorite.setBackgroundDrawable(itemView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
            Toast.makeText(itemView.getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            favorite.setChecked(true); // Keep the toggle button in the checked state
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
