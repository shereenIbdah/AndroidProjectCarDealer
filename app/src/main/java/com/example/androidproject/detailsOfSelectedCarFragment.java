package com.example.androidproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailsOfSelectedCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailsOfSelectedCarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public detailsOfSelectedCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailsOfSelectedCarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailsOfSelectedCarFragment newInstance(String param1, String param2) {
        detailsOfSelectedCarFragment fragment = new detailsOfSelectedCarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private String carId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            carId = args.getString("carId");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_details_of_selected_car, container, false);
        CarsDataBase carsDataBase = new CarsDataBase(getContext(), "CarsDataBase", null, 1);
        CarsDataBase offersDataBase = new CarsDataBase(getContext(), "offersDataBase", null, 1);
        Cursor information = carsDataBase.getCar(carId);
        Cursor information1 = offersDataBase.getCar(carId);

        // Check if the cursor has data
        if (information.moveToFirst()) {
            // Find the TextView by its ID
            TextView factory = root.findViewById(R.id.factoryNameTextView);
            TextView name = root.findViewById(R.id.nameTextView);
            TextView type = root.findViewById(R.id.typeTextView);
            TextView price = root.findViewById(R.id.priceTextView);
            TextView model = root.findViewById(R.id.modelTextView);
            TextView fuel = root.findViewById(R.id.fuelTextView);

            // Access cursor data
            factory.setText(information.getString(2));
            name.setText(information.getString(5));
            type.setText(information.getString(1));
            price.setText(information.getString(4));
            model.setText(information.getString(3));
            fuel.setText(information.getString(9));
        } else if (information1.moveToFirst()) {
            // Find the TextView by its ID
            TextView factory = root.findViewById(R.id.factoryNameTextView);
            TextView name = root.findViewById(R.id.nameTextView);
            TextView type = root.findViewById(R.id.typeTextView);
            TextView price = root.findViewById(R.id.priceTextView);
            TextView model = root.findViewById(R.id.modelTextView);
            TextView fuel = root.findViewById(R.id.fuelTextView);

            // Access cursor data
            factory.setText(information1.getString(2));
            name.setText(information1.getString(5));
            type.setText(information1.getString(1));
            price.setText(information1.getString(4));
            model.setText(information1.getString(3));
            fuel.setText(information1.getString(9));
        } else {
            Log.d("TAG", "onCreateView: " + "no data");
        }
        // Close the cursor when done
        if (information != null && !information.isClosed()) {
            information.close();
        }

        return root;

    }
}