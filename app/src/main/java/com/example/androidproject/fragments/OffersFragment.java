package com.example.androidproject.fragments;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.viewholder.Adapter_offers;
import com.example.androidproject.models.Car;
import com.example.androidproject.activities.MainActivity;
import com.example.androidproject.R;
import com.example.androidproject.databinding.FragmentOffersBinding;
import com.example.androidproject.viewmodels.ui1.offers.OffersViewModel;

import java.util.List;

public class OffersFragment extends Fragment {

    private FragmentOffersBinding binding;
    private OffersViewModel mViewModel;
    private Adapter_offers adapter;

    private List<Car> offerscars;
    public static OffersFragment newInstance() {
        return new OffersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
           binding = FragmentOffersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        offerscars = MainActivity.offerscars;
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview_offers);
        if (offerscars.size() == 0) {

        } else {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            adapter = new Adapter_offers(getContext(), offerscars, fragmentManager,root);
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


