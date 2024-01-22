package com.example.androidproject.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.models.Car;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {

    private Context context;
    private List<Car> cars;

    private List<Car> filteredCars;
    FragmentManager fragmentManager_1;
    View root;

    public MyAdapter(Context context, List<Car> cars, FragmentManager f, View root) {
        this.context = context;
        this.cars = cars;
        this.filteredCars = new ArrayList<>(cars);
        this.fragmentManager_1 = f;
        this.root = root;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use the context provided in the constructor
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.car_view, parent, false), fragmentManager_1, root);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.factorynameView.setText(cars.get(position).getFactoryName());
        holder.namee.setText(cars.get(position).getName());
        holder.typeView.setText(cars.get(position).getType());
        holder.priceView.setText(String.valueOf(cars.get(position).getPrice()));
        holder.modelView.setText(cars.get(position).getModel());
        holder.id.setText(String.valueOf(cars.get(position).getId()));
        holder.fuel.setText(cars.get(position).getFuelType());

        // based on the name set the image
        // for each position set a different image
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Car> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filteredCars);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Car car : filteredCars) {
                    if (car.getFactoryName().toLowerCase().contains(filterPattern) || car.getType().toLowerCase().contains(filterPattern) || car.getModel().toLowerCase().contains(filterPattern) || String.valueOf(car.getPrice()).contains(filterPattern) || car.getFuelType().toLowerCase().contains(filterPattern)) {
                        filteredList.add(car);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Clear the existing data
            cars.clear();
            // Add the filtered results to the list
            cars.addAll((List<Car>) results.values);
            // Notify the adapter that the data has changed
            notifyDataSetChanged();
        }
    };

    }


