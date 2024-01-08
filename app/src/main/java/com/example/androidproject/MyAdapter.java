package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Car> cars;

    public MyAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use the context provided in the constructor
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.car_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(cars.get(position).getFactoryName());
        holder.typeView.setText(cars.get(position).getType());
        // based on the name set the image
        //for each position set different image
        switch (cars.get(position).getFactoryName()) {
            case "Ford":
                 if (cars.get(position).getType().equals("Fiesta"))
                    holder.imageView.setImageResource(R.drawable.ford2);
                else if (cars.get(position).getType().equals("Mustang"))
                    holder.imageView.setImageResource(R.drawable.ford1);
                else
                   holder.imageView.setImageResource(R.drawable.ford3);
                break;
            case "Dodge":
                holder.imageView.setImageResource(R.drawable.dodge);
                break;
            case "Jeep":
                if (cars.get(position).getType().equals("Grand Cherokee"))
                    holder.imageView.setImageResource(R.drawable.jeep3);
                else if (cars.get(position).getType().equals("Wrangler"))
                    holder.imageView.setImageResource(R.drawable.jeep4);
                else
                holder.imageView.setImageResource(R.drawable.jeep3);
                break;
           case "Chevrolet":
                holder.imageView.setImageResource(R.drawable.chevrolet);
                break;
           case "Toyota":
                holder.imageView.setImageResource(R.drawable.toyota);
                break;
              case "Honda":
                holder.imageView.setImageResource(R.drawable.honda3);
                break;
            case "Tesla":
                  if (cars.get(position).getType().equals("Model 3"))
                    holder.imageView.setImageResource(R.drawable.tesla5);
                else if (cars.get(position).getType().equals("Model S"))
                    holder.imageView.setImageResource(R.drawable.tesla2);
                else if (cars.get(position).getType().equals("Model Y"))
                    holder.imageView.setImageResource(R.drawable.tesla3);
                else
                holder.imageView.setImageResource(R.drawable.tesla4);
                break;
            case "Lamborghini":
                holder.imageView.setImageResource(R.drawable.lamborghini1);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.jeep5);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
