package com.example.androidproject;
import android.app.AlertDialog;
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
        ToggleButton reserve = carView.findViewById(R.id.toggleButton4_fav);
        ReservationDataBase reserveDataBase = new ReservationDataBase(carView.getContext(), "Reservation", null, 1);
        reserve.setOnClickListener(v -> {
            // Alert Dialog
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
           /* builder.setPositiveButton("Yes", (dialog, which) -> {
                if (!isCarAlreadyReserved(reserveDataBase1)) {
                    reserve.setBackgroundDrawable(carView.getResources().getDrawable(R.drawable.baseline_star_24));
                    Toast.makeText(carView.getContext(), "Reserved", Toast.LENGTH_SHORT).show();
                    reserveDataBase1.insertCar(id_f.getText().toString(), typeView_f.getText().toString(), factorynameView_favorite.getText().toString(), modelView_f.getText().toString(), priceView_f.getText().toString(), namee_f.getText().toString());
                } else {
                    Toast.makeText(carView.getContext(), "Car is already reserved", Toast.LENGTH_SHORT).show();
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

    }*/
  /*  private boolean isCarAlreadyReserved(DataBaseReserved reserveDataBase) {
        // Check if the car is already in the reservations database
        Cursor cursor = reserveDataBase.getCar(id_f.getText().toString());
        return cursor.getCount() > 0;
    }*/


        });
    }
    }

