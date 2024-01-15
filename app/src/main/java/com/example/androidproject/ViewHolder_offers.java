package com.example.androidproject;

import android.app.AlertDialog;
import android.database.Cursor;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_offers extends RecyclerView.ViewHolder{

    ImageView imageView_offers;
    Button factorynameView_offers;
    TextView typeView_offers;
    TextView priceView_offers;
    TextView modelView_offers;
    TextView namee_offers;
    TextView id_offers;
    ToggleButton favorite_offers , reserve_offers;
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
        ReservationDataBase reserveDataBase = new ReservationDataBase(carView.getContext(), "Reservation", null, 1);
        //FavoriteDataBase favoriteDataBase = new DataBasefavorites(carView.getContext(), "FAVcars", null, 1);
        reserve_offers.setOnClickListener(v -> {
            // Alert Dialog
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
         /*   builder.setPositiveButton("Yes", (dialog, which) -> {
                if (!isCarAlreadyReserved(reserveDataBase)) {
                    reserve_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));
                    Toast.makeText(carView.getContext(), "Reserved", Toast.LENGTH_SHORT).show();
                    reserveDataBase1.insertCar(id_offers.getText().toString(), typeView_offers.getText().toString(), factorynameView_offers.getText().toString(), modelView_offers.getText().toString(), priceView_offers.getText().toString(), namee_offers.getText().toString());
                } else {
                    Toast.makeText(carView.getContext(), "Car is already reserved", Toast.LENGTH_SHORT).show();
                }
            });*/

          /*  builder.setNegativeButton("No", (dialog, which) -> {
                if (reserve_offers.isChecked()) {
                    reserve_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_border_24));
                    Toast.makeText(carView.getContext(), "Removed from reservations", Toast.LENGTH_SHORT).show();
                } else {
                    reserve_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));
                    Toast.makeText(carView.getContext(), "Reserved", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });*/

      /*  favorite_offers.setOnClickListener(v -> {
            if (favorite_offers.isChecked()) {
                if (!isCarAlreadyInFavorites(favoriteDataBase)) {
                    favoriteDataBase.insertCar(id_offers.getText().toString(), typeView_offers.getText().toString(), factorynameView_offers.getText().toString(), modelView_offers.getText().toString(), priceView_offers.getText().toString(), namee_offers.getText().toString());
                    favorite_offers.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_favorite_24));
                    Toast.makeText(carView.getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(carView.getContext(), "Car is already in favorites", Toast.LENGTH_SHORT).show();
                }
            } else {
                removeFromFavorites(favoriteDataBase);
            }
        });


    }*/

  /*  private boolean isCarAlreadyReserved(DataBaseReserved reserveDataBase) {
        // Check if the car is already in the reservations database
        Cursor cursor = reserveDataBase.getCar(id_offers.getText().toString());
        return cursor.getCount() > 0;
    }
    private boolean isCarAlreadyInFavorites(DataBasefavorites favoriteDataBase) {
        // Check if the car is already in the favorites database
        Cursor cursor = favoriteDataBase.getCar(id_offers.getText().toString());
        return cursor.getCount() > 0;
    }
    private void removeFromFavorites(DataBasefavorites favoriteDataBase) {
        // Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
        builder.setTitle("Remove from Favorites");
        builder.setMessage("Are you sure you want to remove this car from favorites?");
        builder.setIcon(R.drawable.baseline_favorite_border_24);

        builder.setPositiveButton("Yes", (dialog, which) -> {
            favoriteDataBase.removeCar(id_offers.getText().toString());
            favorite_offers.setBackgroundDrawable(itemView.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
            Toast.makeText(itemView.getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            favorite_offers.setChecked(true); // Keep the toggle button in the checked state
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/
    });
    }
}
