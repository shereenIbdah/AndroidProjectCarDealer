package com.example.androidproject.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidproject.database.helpers.AdminDataBase;
import com.example.androidproject.databinding.FragmentSlideshowBinding;
import com.example.androidproject.viewmodels.ui.slideshow.SlideshowViewModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AdminDataBase adminDataBase = new AdminDataBase(getActivity(), "admin", null, 1);
          Spinner genderSpinner = binding.genderforadmin3;
        Spinner countrySpinner = binding.countryforadmin3;
        Spinner citySpinner = binding.cityforadmin3;
        EditText email2 = binding.email3foradmin;
        EditText firstName = binding.firstNameforadmin3;
        EditText lastName = binding.lastNameforadmin3;
        EditText phoneNumber = binding.phoneNumberforadmin3;
        EditText password2 = binding.password3foradmin;
        EditText ConfirmPassword = binding.confirmPasswordforadmin3;
        Button createAccount = binding.createaccountforadmin3;
        final String[] passwordError = {" "};
        final String[] firstNameError = {" "};
        final String[] lastNameError = {" "};
        final String[] confirmPasswordError = {" "};
        final String[] hashedPasswordString = {" "};
        final String[] emailError = {" "};
        final String[] phoneNumberError = {" "};
        HashMap<String, String> areaCodes = new HashMap<>();
        areaCodes.put("Palestine", "00970");
        areaCodes.put("Jordan", "00962");
        areaCodes.put("USA", "00963");
        areaCodes.put("Egypt", "00964");


        final String[] options = {"Male", "Female"};
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);
       final  String[] options2 = {"Egypt", "USA", "Palestine", "Jordan"};
        ArrayAdapter<String> objCountryArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options2);
        countrySpinner.setAdapter(objCountryArr);
        // if country is Egypt then the spinner will have 3 cities
        final String[] options3 = {"Cairo", "Alexandria", "Giza", "Luxor"};
        ArrayAdapter<String> objCityArrforEgypt = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options3);
        // if country is USA then the spinner will have 3 cities
        final  String[] options4 = {"New York", "Los Angeles", "Chicago", "Houston"};
        ArrayAdapter<String> objCityArrforUSA = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options4);
        // if country is Palestine then the spinner will have 3 cities
        final String[] options5 = {"Jerusalem", "Ramallah", "Hebron", "Nablus"};
        ArrayAdapter<String> objCityArrforPalestine = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options5);
        // if country is jorden then the spinner will have 3 cities
        final  String[] options6 = {"Amman", "Irbid", "Zarqa", "Aqaba"};
        ArrayAdapter<String> objCityArrforJorden = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options6);



        // on click listener for the country spinner
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCountry = countrySpinner.getSelectedItem().toString();
                String areaCode = areaCodes.get(selectedCountry);
                phoneNumber.setText(areaCode);
                switch (selectedCountry) {
                    case "Egypt":
                        citySpinner.setAdapter(objCityArrforEgypt);
                        break;
                    case "USA":
                        citySpinner.setAdapter(objCityArrforUSA);
                        break;
                    case "Palestine":
                        citySpinner.setAdapter(objCityArrforPalestine);
                        break;
                    case "Jordan":
                        citySpinner.setAdapter(objCityArrforJorden);
                        break;
                    default:
                        // Handle default case or do nothing
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle no selection if needed
            }
        });

        //check there length
        createAccount.setOnClickListener(v -> {
            //first empty all errors
            passwordError[0] = " ";
            firstNameError[0] = " ";
            lastNameError[0] = " ";
            confirmPasswordError[0] = " ";
            emailError[0] = " ";
            phoneNumberError[0] = " ";
            ///First name (not less than 3 characters).
            if (firstName.getText().toString().length() < 3) {
                firstNameError[0] += "First name must be at least 3 characters";
            }
            // check if the first name is only letters
            if (!firstName.getText().toString().matches("[a-zA-Z]+")) {
                firstNameError[0] += "First name must be only letters";
            }
            if (firstNameError[0] != " ") {
                firstName.setError(firstNameError[0]);
            }

            if (lastName.getText().toString().length() < 3) {
                lastNameError[0] += "Last name must be at least 3 characters";
            }
            // check if the last name is only letters
            if (!lastName.getText().toString().matches("[a-zA-Z]+")) {
                lastNameError[0] += "Last name must be only letters";
            }
            if (lastNameError[0] != " ") {
                lastName.setError(lastNameError[0]);
            }

            //(must not be less than 5 characters and must include at least 1character, 1 number, and one special character
            if (password2.getText().toString().length() < 5) {
                passwordError[0] += "Password must be at least 5 characters";
            }
            if (!password2.getText().toString().matches(".*[a-zA-Z]+.*")) {
                passwordError[0] += "Password must include at least 1 character";
            }
            if (!password2.getText().toString().matches(".*[0-9]+.*")) {
                passwordError[0] += "Password must include at least 1 number";
            }
            if (!password2.getText().toString().matches(".*[!@#$%^&*()_+]+.*")) {
                passwordError[0] += "Password must include at least 1 special character";
            }
            if (passwordError[0] != " ") {
                password2.setError(passwordError[0]);
            }
            // check if the password and confirm password are the same
            if (!password2.getText().toString().equals(ConfirmPassword.getText().toString())) {
                confirmPasswordError[0] += "Password and confirm password must be the same";
            } else {
                // encrypt the password using a secure hash function
                // Create MessageDigest instance for SHA-256

                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    byte[] hashedPassword = md.digest(password2.getText().toString().getBytes());

                    // Convert the byte array to hex string
                    StringBuilder hexString = new StringBuilder();
                    for (byte b : hashedPassword) {
                        // Convert each byte to hexadecimal representation
                        String hex = Integer.toHexString(0xff & b);
                        if (hex.length() == 1) {
                            // Pad the hex string with 0 if it's a single digit
                            hexString.append('0');
                        }
                        hexString.append(hex);
                    }

                    // Now 'hexString' contains the hashed password as a hexadecimal string
                    hashedPasswordString[0] = hexString.toString();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

            }
            if (confirmPasswordError[0] != " ") {
                ConfirmPassword.setError(confirmPasswordError[0]);
            }
            //(must have pre zip code (area code)).
            //Eg. Palestine (00970), Jordan (00962), Syria (00963) …etc.
            //check if the phone number is only numbers

            if (!phoneNumber.getText().toString().matches("[0-9]+" ) || phoneNumber.getText().toString().length() <= 9 ) {
                phoneNumberError[0] += "Phone number must be only numbers  and more than 9 digits";
            }
            if (phoneNumberError[0] != " ") {
                phoneNumber.setError(phoneNumberError[0]);
            }
            //if e
            // emil is empty and not include @
            if (email2.getText().toString().length() == 0 || !email2.getText().toString().contains("@")) {
                emailError[0] += "Email must be not empty and include @";
            }
            //check the email unique in the data base
            Cursor cursor = adminDataBase.getAdmin(email2.getText().toString());
            if (cursor.getCount() != 0) {
                emailError[0] += "Email is already exist";
            }
            if (emailError[0] != " ") {
                email2.setError(emailError[0]);
            }

            //if all the fields are correct then add the email and password to the data base
            if (passwordError[0] == " " && firstNameError[0] == " " && lastNameError[0] == " " && confirmPasswordError[0] == " " && emailError[0] == " " && phoneNumberError[0] == " ") {
                adminDataBase.insertAdmin(email2.getText().toString(), hashedPasswordString[0], firstName.getText().toString(), lastName.getText().toString(), phoneNumber.getText().toString(), genderSpinner.getSelectedItem().toString(), countrySpinner.getSelectedItem().toString(), citySpinner.getSelectedItem().toString());
                // imageView.setVisibility(View.VISIBLE);
                Toast toast = Toast.makeText(getActivity(), "Add Admin Done Successfuly", Toast.LENGTH_LONG);
                toast.show();
                //toast message that sign up is successful
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