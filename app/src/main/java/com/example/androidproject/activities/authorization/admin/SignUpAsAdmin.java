package com.example.androidproject.activities.authorization.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.database.helpers.AdminDataBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class SignUpAsAdmin extends AppCompatActivity {
    private static final String TOAST_TEXT = "Sign up is successful";
    public static   String adminName ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as_admin);
        AdminDataBase adminDataBase = new AdminDataBase(this, "admin", null, 1);
        Spinner genderSpinner = findViewById(R.id.genderforadmin);
        Spinner countrySpinner = findViewById(R.id.countryforadmin);
        Spinner citySpinner = findViewById(R.id.cityforadmin);
        EditText email2 = findViewById(R.id.emailforadmin);
        EditText firstName = findViewById(R.id.firstNameforadmin);
        EditText lastName = findViewById(R.id.lastNameforadmin);
        EditText phoneNumber = findViewById(R.id.phoneNumberforadmin);
        EditText password2 = findViewById(R.id.passwordforadmin);
        EditText ConfirmPassword = findViewById(R.id.confirmPasswordforadmin);
        Button createAccount = findViewById(R.id.createaccountforadmin);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpAsAdmin.this, SignInAsAdmin.class);
            startActivity(intent);
            finish();
        });
        final String[] passwordError = {" "};
        final String[] firstNameError = {" "};
        final String[] lastNameError = {" "};
        final String[] confirmPasswordError = {" "};
        final String[] hashedPasswordString = {" "};
        final String[] emailError = {" "};
        HashMap<String, String> areaCodes = new HashMap<>();
        areaCodes.put("Palestine", "00970");
        areaCodes.put("Jordan", "00962");
        areaCodes.put("USA", "00963");
        areaCodes.put("Egypt", "00964");

        // Create an ArrayAdapter using the string array and a default spinner layout
        //for gender
        String[] options = {"Male", "Female"};
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);
        //for country
        //pinner with no less than 4 countries)
        String[] options2 = {"Egypt", "USA", "Palestine", "Jordan"};
        ArrayAdapter<String> objCountryArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options2);
        countrySpinner.setAdapter(objCountryArr);
        // if country is Egypt then the spinner will have 3 cities
        String[] options3 = {"Cairo", "Alexandria", "Giza", "Luxor"};
        ArrayAdapter<String> objCityArrforEgypt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options3);
        // if country is USA then the spinner will have 3 cities
        String[] options4 = {"New York", "Los Angeles", "Chicago", "Houston"};
        ArrayAdapter<String> objCityArrforUSA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options4);
        // if country is Palestine then the spinner will have 3 cities
        String[] options5 = {"Jerusalem", "Ramallah", "Hebron", "Nablus"};
        ArrayAdapter<String> objCityArrforPalestine = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options5);
        // if country is jorden then the spinner will have 3 cities
        String[] options6 = {"Amman", "Irbid", "Zarqa", "Aqaba"};
        ArrayAdapter<String> objCityArrforJorden = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options6);


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
                phoneNumber.setError("Phone number must be only numbers  and more than 9 digits");
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
            if (passwordError[0] == " " && firstNameError[0] == " " && lastNameError[0] == " " && confirmPasswordError[0] == " " && emailError[0] == " ") {
                adminDataBase.insertAdmin(email2.getText().toString(), hashedPasswordString[0], firstName.getText().toString(), lastName.getText().toString(), phoneNumber.getText().toString(), genderSpinner.getSelectedItem().toString(), countrySpinner.getSelectedItem().toString(), citySpinner.getSelectedItem().toString());
                adminName = firstName.getText().toString();
                // imageView.setVisibility(View.VISIBLE);
                Toast toast = Toast.makeText(SignUpAsAdmin.this, TOAST_TEXT, Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(SignUpAsAdmin.this, SignInAsAdmin.class);
                startActivity(intent);
                finish();
                //toast message that sign up is successful
            }
        });


    }

}
