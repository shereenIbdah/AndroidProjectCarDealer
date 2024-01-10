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
import androidx.lifecycle.ViewModelProvider;

import com.example.androidproject.databinding.FragmentSlideshowBinding;
import com.example.androidproject.ui.slideshow.SlideshowViewModel;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private LinearLayout secondLinearLayout;

    @Override
    public void onResume() {
        super.onResume();
        refreshReservationData();
    }

    private void refreshReservationData() {
        secondLinearLayout.removeAllViews(); // Clear existing views

        // Correct the table name to match your database helper
        reserveDataBase reserveDataBase = new reserveDataBase(getContext(), "reserveCar", null, 1);
        Cursor getAllReservations = reserveDataBase.getAllReservations();
        while (getAllReservations.moveToNext()) {
            TextView textView = new TextView(getContext());
            textView.setText(
                    "Id= " + getAllReservations.getString(0) + "\nType= " + getAllReservations.getString(1)
                            + "\nModel= " + getAllReservations.getString(2)
                            + "\nPrice= " + getAllReservations.getString(3)
                            + "\nName= " + getAllReservations.getString(4)
                            + "\nTime= " + getAllReservations.getString(5)
                            + "\n\n"
            );
            secondLinearLayout.addView(textView);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
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
