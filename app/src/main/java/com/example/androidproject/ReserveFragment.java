package com.example.androidproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidproject.databinding.FragmentReservedcarsBinding;


public class ReserveFragment extends Fragment {

    private FragmentReservedcarsBinding binding;
    private LinearLayout secondLinearLayout;

    @Override
    public void onResume() {
        super.onResume();
        refreshReservationData();
    }


    private void refreshReservationData() {
        secondLinearLayout.removeAllViews(); // Clear existing views

        // Correct the table name to match your database helper
        //  CarsDataBase reserveDataBase = new CarsDataBase(getContext(), "Reservecar", null, 1);
        // Cursor getAllReservations = reserveDataBase.getAllCars();
        String email = SignInActivity.emailForProfile;
        CarsDataBase carsDataBase = new CarsDataBase(getContext(), "CarsDataBase", null, 1);
        ReservationDataBase reserveDataBase = new ReservationDataBase(getContext(), "reservation1", null, 1);
        CarsDataBase offersDataBase = new CarsDataBase(getContext(), "offersDataBase", null, 1);
        Cursor getAllReservations = reserveDataBase.getAllReservations(email);

        Cursor information;
        while (getAllReservations.moveToNext() ) {
            TextView textView = new TextView(getContext());
            //check if the car is in the offers
            if (offersDataBase.isExist(getAllReservations.getString(1))) {
                information = offersDataBase.getCar(getAllReservations.getString(1));
                information.moveToNext();
                //String id, String type, String factoryname, String model, String price,
                // String name ,String offer,String fuel) {
                textView.setText("Car factoryname: " + information.getString(2) + "\t" +
                        "Car name: " + information.getString(5) + "\t" +
                        "Car type: " + information.getString(1) + "\t" +
                        "Car price: " + information.getString(4) + "\t" +
                        "Car model: " + information.getString(3) + "\t" +
                        "Car id: " + information.getString(0) + "\t" +
                        "Car time: " + getAllReservations.getString(3) + "\t" +
                        "Car date: " + getAllReservations.getString(2) + "\n");
                //change the color of the text
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setTextSize(20);
                secondLinearLayout.addView(textView);
            } else {
                information = carsDataBase.getCar(getAllReservations.getString(1));
                information.moveToNext();

                textView.setText("Car factoryname: " + information.getString(2) + "\t" +
                        "Car name: " + information.getString(5) + "\t" +
                        "Car type: " + information.getString(1) + "\t" +
                        "Car price: " + information.getString(4) + "\t" +
                        "Car model: " + information.getString(3) + "\t" +
                        "Car id: " + information.getString(0) + "\t" +
                        "Car time: " + getAllReservations.getString(3) + "\t" +
                        "Car date: " + getAllReservations.getString(2) + "\n");
                //change the color of the text
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setTextSize(20);
                secondLinearLayout.addView(textView);
            }
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentReservedcarsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        secondLinearLayout = root.findViewById(R.id.layout);

        refreshReservationData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
