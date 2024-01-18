package com.example.androidproject;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidproject.databinding.FragmentAdminHomeBinding;
import com.example.androidproject.databinding.FragmentCallusBinding;
import com.example.androidproject.databinding.FragmentViewAllReservationBinding;

public class ViewAllReservationFragment extends Fragment {

    private FragmentViewAllReservationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentViewAllReservationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //get the button callus
        //set the action when the button is clicked implict intent call the number 0599000000
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}