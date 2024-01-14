package com.example.androidproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.databinding.FragmentFavBinding;

import java.util.ArrayList;
import java.util.List;

public class favFragment extends Fragment {

    private FragmentFavBinding binding;
    private Adapter_fav adapter;
    private List<Car> favcars;

    public static favFragment newInstance() {
        return new favFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFavBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        favcars = new ArrayList<>();
        DataBasefavorites favoriteDataBase = new DataBasefavorites(getContext(), "FAVcars", null, 1);
        Cursor getAllFavorite = favoriteDataBase.getAllFav();

        while (getAllFavorite.moveToNext()) {
            Car car = new Car();
            car.setId(getAllFavorite.getString(0));
            car.setFactoryName(getAllFavorite.getString(2));
            car.setType(getAllFavorite.getString(1));
            car.setModel(getAllFavorite.getString(3));
            car.setPrice(Double.parseDouble(getAllFavorite.getString(4)));
            car.setName(getAllFavorite.getString(5));
            favcars.add(car);
        }

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview_favorite);
        TextView noFavTextView = root.findViewById(R.id.no_fav);

        if (favcars.size() == 0) {
            noFavTextView.setVisibility(View.VISIBLE);
        } else {
            noFavTextView.setVisibility(View.INVISIBLE);

            adapter = new Adapter_fav(getContext(), favcars);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }

        return root; // Return the inflated view
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
