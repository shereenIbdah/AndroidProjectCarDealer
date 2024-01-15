package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_offers extends RecyclerView.Adapter<ViewHolder_offers> {

    private Context context;
    private List<Car> car_offers;
    public Adapter_offers(Context context, List<Car> cars) {
        this.context = context;
        this.car_offers = cars;
    }

    @NonNull
    @Override
    public ViewHolder_offers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use the context provided in the constructor
        return new ViewHolder_offers(LayoutInflater.from(context).inflate(R.layout.car_view_offers, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder_offers holder, int position) {
        holder.id_offers.setText(String.valueOf(car_offers.get(position).getId()));
        holder.factorynameView_offers.setText(car_offers.get(position).getFactoryName());
        holder.namee_offers.setText(car_offers.get(position).getName());
        holder.typeView_offers.setText(car_offers.get(position).getType());
        holder.priceView_offers.setText(String.valueOf(car_offers.get(position).getPrice()));
        holder.modelView_offers.setText(car_offers.get(position).getModel());

        switch (car_offers.get(position).getFactoryName()) {
            case "Ford":
                if (car_offers.get(position).getType().equals("Fiesta"))
                    holder.imageView_offers.setImageResource(R.drawable.ford2);
                else if (car_offers.get(position).getType().equals("Mustang"))
                    holder.imageView_offers.setImageResource(R.drawable.ford1);
                else
                    holder.imageView_offers.setImageResource(R.drawable.ford3);
                break;
            case "Dodge":
                holder.imageView_offers.setImageResource(R.drawable.dodge);
                break;
            case "Jeep":
                if (car_offers.get(position).getType().equals("Grand Cherokee"))
                    holder.imageView_offers.setImageResource(R.drawable.jeep3);
                else if (car_offers.get(position).getType().equals("Wrangler"))
                    holder.imageView_offers.setImageResource(R.drawable.jeep4);
                else
                    holder.imageView_offers.setImageResource(R.drawable.jeep3);
                break;
            case "Chevrolet":
                holder.imageView_offers.setImageResource(R.drawable.chevrolet);
                break;
            case "Toyota":
                holder.imageView_offers.setImageResource(R.drawable.toyota);
                break;
            case "Honda":
                holder.imageView_offers.setImageResource(R.drawable.honda3);
                break;
            case "Tesla":
                if (car_offers.get(position).getType().equals("Model 3"))
                    holder.imageView_offers.setImageResource(R.drawable.tesla5);
                else if (car_offers.get(position).getType().equals("Model S"))
                    holder.imageView_offers.setImageResource(R.drawable.tesla2);
                else if (car_offers.get(position).getType().equals("Model Y"))
                    holder.imageView_offers.setImageResource(R.drawable.tesla3);
                else
                    holder.imageView_offers.setImageResource(R.drawable.tesla4);
                break;
            case "Lamborghini":
                holder.imageView_offers.setImageResource(R.drawable.lamborghini1);
                break;
            default:
                holder.imageView_offers.setImageResource(R.drawable.jeep5);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return car_offers.size(); // Return the size of the list
    }
}