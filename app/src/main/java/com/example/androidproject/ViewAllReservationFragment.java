package com.example.androidproject;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidproject.databinding.FragmentViewAllReservationBinding;

public class ViewAllReservationFragment extends Fragment {

    private FragmentViewAllReservationBinding binding;
    private LinearLayout secondLinearLayout;
    private TextView textView1;

    @Override
    public void onResume() {
        super.onResume();
        refreshReservationData();
    }

    private void refreshReservationData() {
        secondLinearLayout.removeAllViews(); // Clear existing views
        UserDataBase userDataBase = new UserDataBase(getContext(), "projectDataBase1", null, 1);
        Cursor getAllUsers = userDataBase.getAllUsers();
        while (getAllUsers.moveToNext()) {
            String email = getAllUsers.getString(0);
            CarsDataBase carsDataBase = new CarsDataBase(getContext(), "CarsDataBase", null, 1);
            ReservationDataBase reserveDataBase = new ReservationDataBase(getContext(), "reservation1", null, 1);
            CarsDataBase offersDataBase = new CarsDataBase(getContext(), "offersDataBase", null, 1);
            Cursor getAllReservations = reserveDataBase.getAllReservations(email);
            if (getAllReservations.getCount() != 0) {
                textView1 = new TextView(getContext());
                Cursor information;
                while (getAllReservations.moveToNext()) {
                    TextView textView = new TextView(getContext());
                    if (offersDataBase.isExist(getAllReservations.getString(1))) {
                        information = offersDataBase.getCar(getAllReservations.getString(1));
                    } else {
                        information = carsDataBase.getCar(getAllReservations.getString(1));
                    }
                    information.moveToNext();

                    String customerName = "<font color='#FF0000'>Customer name: </font>" + getAllUsers.getString(2) + "  " + getAllUsers.getString(3) + "<br>";
                    String emil = "<font color='#FF0000'>Customer email: </font>" + getAllUsers.getString(0) + "<br>";
                    String carInfo = "<font color='#000000'>Car factoryname: </font>" + information.getString(2) + "\t" +
                            "<font color='#000000'>Car name: </font>" + information.getString(5) + "\t" +
                            "<font color='#000000'>Car type: </font>" + information.getString(1) + "\t" +
                            "<font color='#000000'>Car price: </font>" + information.getString(4) + "\t" +
                            "<font color='#000000'>Car model: </font>" + information.getString(3) + "\t" +
                            "<font color='#000000'>Car id: </font>" + information.getString(0) + "\t" +
                            "<font color='#000000'>Car time: </font>" + getAllReservations.getString(3) + "\t" +
                            "<font color='#000000'>Car date: </font>" + getAllReservations.getString(2) + "<br>";

                    // Combine customerName and carInfo
                    String combinedText = customerName +emil+ carInfo;

                    // Set the formatted HTML text to the textView
                    textView.setText(Html.fromHtml(combinedText, Html.FROM_HTML_MODE_LEGACY));
                    textView.setTextSize(20);

                    secondLinearLayout.addView(textView);
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewAllReservationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        secondLinearLayout = root.findViewById(R.id.layout_view_all_reservation);
        refreshReservationData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
