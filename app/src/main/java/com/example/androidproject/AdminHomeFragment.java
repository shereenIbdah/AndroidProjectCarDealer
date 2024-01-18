package com.example.androidproject;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidproject.databinding.FragmentAdminHomeBinding;
import com.example.androidproject.databinding.FragmentCallusBinding;

public class AdminHomeFragment extends Fragment {

    private FragmentAdminHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdminDataBase adminDataBase= new AdminDataBase(getActivity(),"admin",null,1);
        String email = SignInAsAdmin.adminEmail;


        binding = FragmentAdminHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //find the admin information from the data base
        Cursor cursor = adminDataBase.getAdmin(email);
        cursor.moveToFirst();
        //    public void insertAdmin(String email, String password, String FirstName, String LastName, String PhoneNumber, String Gender, String Country, String City) {
        String firstName = cursor.getString(2);
        String lastName = cursor.getString(3);
        String phone = cursor.getString(4);
        String email1 = cursor.getString(0);
        String city = cursor.getString(6);
        String country = cursor.getString(7);
        String gender = cursor.getString(5);



        //set the admin information in the text view
        TextView textView = root.findViewById(R.id.textView);
        //add the admin information to the text view
        textView.setText(" first Name = "+firstName+"\n");
        //add icon to the text view
        textView.append("  last Name = "+lastName+"\n"+"");
        textView.append("  phone = "+phone+"\n"+"");
        textView.append("  email = "+email1+"\n"+"");
        textView.append("  city = "+city+"\n"+"");
        textView.append("  country = "+country+"\n"+"");
        textView.append("  gender = "+gender+"\n"+"");
        //set the action when the button is clicked implict intent call the number 0599000000
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}