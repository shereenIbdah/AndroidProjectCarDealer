package com.example.androidproject;

import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidproject.databinding.FragmentFavBinding;
import com.example.androidproject.databinding.FragmentOffersBinding;
import com.example.androidproject.ui.offers.OffersViewModel;

import java.util.ArrayList;
import java.util.List;

public class OffersFragment extends Fragment {

    private FragmentOffersBinding binding;
    private OffersViewModel mViewModel;
    private Adapter_offers adapter;

    private List<Car> offerscars;
    private List<Car> offerscars2;

    public static OffersFragment newInstance() {
        return new OffersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
           binding = FragmentOffersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DataBaseOffers offersDataBase = new DataBaseOffers(getContext(), "Offers", null, 1);
        GalleryFragment galleryFragment = new GalleryFragment();

        //Cursor getAllFavorite = offersDataBase.getAllFav();
        offerscars = new ArrayList<>();
        Cursor getAllOffers = offersDataBase.getAllOffers();
        while (getAllOffers.moveToNext()) {
            Car car = new Car();
            car.setId(getAllOffers.getString(0));
            car.setFactoryName(getAllOffers.getString(2));
            car.setType(getAllOffers.getString(1));
            car.setModel(getAllOffers.getString(3));
            car.setPrice(Double.parseDouble(getAllOffers.getString(4)));
            car.setName(getAllOffers.getString(5));
            offerscars.add(car);
            System.out.println(car.toString());
        }
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview_offers);
        if (offerscars.size() == 0) {

        } else {
            adapter = new Adapter_offers(getContext(), offerscars);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OffersViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}




// Assuming offersDataBase is an instance of your database class (e.g., DataBaseOffers)
        /*
        offersDataBase.insertCar("26", "Sonic LT", "Chevrolet", "2023", "22000", "Sonic");
        offersDataBase.insertCar("27", "Fit Sport", "Honda", "2023", "23000", "Fit");
        offersDataBase.insertCar("28", "Camry LE", "Toyota", "2023", "28000", "Camry");
        offersDataBase.insertCar("29", "Focus SE", "Ford", "2023", "20000", "Focus");
        offersDataBase.insertCar("30", "Fiat 500 Pop", "Fiat", "2023", "18000", "Fiat");
        offersDataBase.insertCar("31", "Cruze LS", "Chevrolet", "2023", "25000", "Cruze");
        offersDataBase.insertCar("32", "Civic Sport", "Honda", "2023", "21000", "Civic");
        offersDataBase.insertCar("33", "Corolla SE", "Toyota", "2023", "26000", "Corolla");
        offersDataBase.insertCar("34", "Mustang GT", "Ford", "2023", "30000", "Mustang");
        offersDataBase.insertCar("35", "Beetle S", "Volkswagen", "2023", "24000", "Beetle");
        offersDataBase.insertCar("36", "Spark LS", "Chevrolet", "2023", "18000", "Spark");
        offersDataBase.insertCar("37", "Fit LX", "Honda", "2023", "22000", "Fit");
        offersDataBase.insertCar("38", "Prius C", "Toyota", "2023", "24000", "Prius");
        offersDataBase.insertCar("39", "Fiesta S", "Ford", "2023", "17000", "Fiesta");
        offersDataBase.insertCar("40", "Jetta SE", "Volkswagen", "2023", "26000", "Jetta");
        offersDataBase.insertCar("41", "Soul LX", "Kia", "2023", "23000", "Soul");
        offersDataBase.insertCar("42", "Malibu LS", "Chevrolet", "2023", "27000", "Malibu");*/