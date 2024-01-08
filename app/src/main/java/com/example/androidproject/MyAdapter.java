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
                holder.imageView.setImageResource(R.drawable.ford1);
                break;
            case "Dodge":
                holder.imageView.setImageResource(R.drawable.dodge);
                break;
            case "Jeep":
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
