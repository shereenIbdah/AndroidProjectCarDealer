package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.databinding.FragmentProfileBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ProfileFragment extends Fragment {


    private FragmentProfileBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "projectDataBase1", null, 1);
        String email = SignInActivity.emailForProfile;
        //String email = SignInActivity.emailForProfile;
        // get the user from the data base by the email
        Cursor cursor = dataBaseHelper.getUser(email);
        cursor.moveToFirst();
        final String[] hashedPasswordString = {" "};
        // get the index of the column
        int indexEmail = cursor.getColumnIndex("email");
        int indexPassword = cursor.getColumnIndex("password");
        int indexFirstName = cursor.getColumnIndex("FirstName");
        int indexLastName = cursor.getColumnIndex("LastName");
        int indexPhoneNumber = cursor.getColumnIndex("PhoneNumber");
        int indexCountry = cursor.getColumnIndex("Country");
        int indexCity = cursor.getColumnIndex("City");
        // print the data in the text view
        TextView printInfo = (TextView) root.findViewById(R.id.information);
        printInfo.setText("Email: " + cursor.getString(indexEmail) + "\n" +
                "First Name: " + cursor.getString(indexFirstName) + "\n"
                + "Last Name: " + cursor.getString(indexLastName) + "\n"
                + "Phone Number: " + cursor.getString(indexPhoneNumber) + "\n"
                + "Country: " + cursor.getString(indexCountry) + "\n"
                + "City: " + cursor.getString(indexCity) + "\n");
        printInfo.setTextSize(20);
        printInfo.setTextColor(getResources().getColor(R.color.black));
        Button change = (Button) root.findViewById(R.id.change);
        EditText FirstName = (EditText) root.findViewById(R.id.FirstNameUpdate);
        EditText LastName = (EditText) root.findViewById(R.id.lastNameUpdate);
        EditText PhoneNumber = (EditText) root.findViewById(R.id.phoneUpdate);
        EditText Password = (EditText) root.findViewById(R.id.passwordUpdate);
        final String[] FirstNameError = {""};
        final String[] LastNameError = {""};
        final String[] PhoneNumberError = {""};
        final String[] PasswordError = {""};
        //call getAreaCode method to get the area code
        String areaCode = getAreaCode();
        PhoneNumber.setText(areaCode);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if the user enter the first name(not less than 3 characters
                if (FirstName.getText().toString().length() >= 3 && FirstName.getText().toString().matches("[a-zA-Z]+")) {
                    dataBaseHelper.updateUserFirstName(email, FirstName.getText().toString());
                } else {
                    if (FirstName.getText().toString().length() != 0) {
                        FirstNameError[0] = "First Name must be at least 3 characters";
                        FirstName.setError(FirstNameError[0]);
                    }
                }
                //check if the user enter the last name(not less than 3 characters
                if (LastName.getText().toString().length() >= 3 && LastName.getText().toString().matches("[a-zA-Z]+")) {
                    dataBaseHelper.updateUserLastName(email, LastName.getText().toString());
                } else {
                    if (LastName.getText().toString().length() != 0) {
                        LastNameError[0] = "Last Name must be at least 3 characters";
                        LastName.setError(LastNameError[0]);
                    }
                }

                if (PhoneNumber.getText().toString().length() > 5 && PhoneNumber.getText().toString().matches("[0-9]+")) {
                    dataBaseHelper.updateUserPhoneNumber(email, PhoneNumber.getText().toString());
                } else {
                    PhoneNumberError[0] = "Phone Number must be more than 5 numbers";
                    PhoneNumber.setError(PhoneNumberError[0]);
                }

                //(must not be less than 5 characters and must include at least 1
                //character, 1 number, and one special character
                if (Password.getText().toString().length() >= 5 && Password.getText().toString().matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{5,}$")) {
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        byte[] hashedPassword = md.digest(Password.getText().toString().getBytes());

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
                        System.out.println("Hashed Password: " + hashedPasswordString[0]);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }

                    dataBaseHelper.updateUserPassword(email, hashedPasswordString[0]);
                } else {
                    if (Password.getText().toString().length() != 0) {
                        PasswordError[0] = "Password must be at least 5 characters and must include at least 1 character, 1 number, and one special character";
                        Password.setError(PasswordError[0]);
                    }
                }
                //update the printInfo text view
                Cursor cursor = dataBaseHelper.getUser(email);
                cursor.moveToFirst();
                TextView printInfo = (TextView) root.findViewById(R.id.information);
                printInfo.setText("Email: " + cursor.getString(indexEmail) + "\n" +
                        "First Name: " + cursor.getString(indexFirstName) + "\n"
                        + "Last Name: " + cursor.getString(indexLastName) + "\n"
                        + "Phone Number: " + cursor.getString(indexPhoneNumber) + "\n"
                        + "Country: " + cursor.getString(indexCountry) + "\n"
                        + "City: " + cursor.getString(indexCity) + "\n");
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onResume() {
        super.onResume();
        //remove the error message
        EditText FirstName = (EditText) getView().findViewById(R.id.FirstNameUpdate);
        EditText LastName = (EditText) getView().findViewById(R.id.lastNameUpdate);
        EditText PhoneNumber = (EditText) getView().findViewById(R.id.phoneUpdate);
        EditText Password = (EditText) getView().findViewById(R.id.passwordUpdate);
        FirstName.setError(null);
        LastName.setError(null);
        PhoneNumber.setError(null);
        Password.setError(null);
        //remove the text entered
        FirstName.setText("");
        LastName.setText("");
        Password.setText("");
        //define the areacode
        String areaCode = getAreaCode();
        PhoneNumber.setText(areaCode);

    }

    public String getAreaCode() {
        HashMap<String, String> areaCodes = new HashMap<>();
        areaCodes.put("Palestine", "00970");
        areaCodes.put("Jordan", "00962");
        areaCodes.put("USA", "00963");
        areaCodes.put("Egypt", "00964");
        //get the user from the data base by the email
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "projectDataBase1", null, 1);
        String email = SignInActivity.emailForProfile;
        Cursor cursor = dataBaseHelper.getUser(email);
        cursor.moveToFirst();
        int indexCountry = cursor.getColumnIndex("Country");
        String country = cursor.getString(indexCountry);
        String areaCode = areaCodes.get(country);
        return areaCode;
    }

}