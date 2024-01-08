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

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
