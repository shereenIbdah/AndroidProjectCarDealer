package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_fav extends RecyclerView.Adapter<ViewHolder_fav> {

    private Context context;
    private List<Car> car;

    public Adapter_fav(Context context, List<Car> cars) {
        this.context = context;
        this.car = cars;
    }

    @NonNull
    @Override
    public ViewHolder_fav onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use the context provided in the constructor
        return new ViewHolder_fav(LayoutInflater.from(context).inflate(R.layout.car_view_fav, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_fav holder, int position) {
        holder.factorynameView_favorite.setText(car.get(position).getFactoryName());
        holder.namee_f.setText(car.get(position).getName());
        holder.typeView_f.setText(car.get(position).getType());
        holder.priceView_f.setText(String.valueOf(car.get(position).getPrice()));
        holder.modelView_f.setText(car.get(position).getModel());
        holder.id_f.setText(String.valueOf(car.get(position).getId()));
        // based on the name set the image
        // for each position set a different image
        switch (car.get(position).getFactoryName()) {
            case "Ford":
                if (car.get(position).getType().equals("Fiesta"))
                    holder.imageView_fav.setImageResource(R.drawable.ford2);
                else if (car.get(position).getType().equals("Mustang"))
                    holder.imageView_fav.setImageResource(R.drawable.ford1);
                else
                    holder.imageView_fav.setImageResource(R.drawable.ford3);
                break;
            case "Dodge":
                holder.imageView_fav.setImageResource(R.drawable.dodge);
                break;
            case "Jeep":
                if (car.get(position).getType().equals("Grand Cherokee"))
                    holder.imageView_fav.setImageResource(R.drawable.jeep3);
                else if (car.get(position).getType().equals("Wrangler"))
                    holder.imageView_fav.setImageResource(R.drawable.jeep4);
                else
                    holder.imageView_fav.setImageResource(R.drawable.jeep3);
                break;
            case "Chevrolet":
                holder.imageView_fav.setImageResource(R.drawable.chevrolet);
                break;
            case "Toyota":
                holder.imageView_fav.setImageResource(R.drawable.toyota);
                break;
            case "Honda":
                holder.imageView_fav.setImageResource(R.drawable.honda3);
                break;
            case "Tesla":
                if (car.get(position).getType().equals("Model 3"))
                    holder.imageView_fav.setImageResource(R.drawable.tesla5);
                else if (car.get(position).getType().equals("Model S"))
                    holder.imageView_fav.setImageResource(R.drawable.tesla2);
                else if (car.get(position).getType().equals("Model Y"))
                    holder.imageView_fav.setImageResource(R.drawable.tesla3);
                else
                    holder.imageView_fav.setImageResource(R.drawable.tesla4);
                break;
            case "Lamborghini":
                holder.imageView_fav.setImageResource(R.drawable.lamborghini1);
                break;
            default:
                holder.imageView_fav.setImageResource(R.drawable.jeep5);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return car.size(); // Return the size of the list
    }
}
