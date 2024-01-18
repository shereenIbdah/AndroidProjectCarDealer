package com.example.androidproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidproject.databinding.FragmentGalleryBinding;
import com.example.androidproject.ui.gallery.GalleryViewModel;

public class DeleteCustomerFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        UserDataBase dataBaseHelper = new UserDataBase(getActivity(), "projectDataBase1", null, 1);
        EditText editText = binding.deleteCustomer;
        Button button = binding.deletebutton;
        button.setOnClickListener(v -> {
            //search for the email in the data base
            if (dataBaseHelper.getUser(editText.getText().toString()).getCount() == 0 || editText.getText().toString().isEmpty() || !editText.getText().toString().contains("@")) {
                // if the email is not found
                editText.setError("Email not found");
            } else {
                // if the email is found
                dataBaseHelper.deleteUser(editText.getText().toString());
                Toast.makeText(getActivity(), "Customer deleted successfully", Toast.LENGTH_SHORT).show();
                editText.setError(null);
                editText.setText("");
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}