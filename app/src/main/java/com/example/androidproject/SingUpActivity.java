package com.example.androidproject;

import static com.example.androidproject.firebase.ImageHelper.uploadImage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.androidproject.firebase.ImageHelper;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class SingUpActivity extends AppCompatActivity {
    private static final String TOAST_TEXT = "Sign up is successful";
    ImageView imageView;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        UserDataBase dataBaseHelper = new UserDataBase(this, "projectDataBase2", null, 1);
        Spinner genderSpinner = findViewById(R.id.gender);
        Spinner citySpinner = findViewById(R.id.city);
        Spinner countrySpinner = findViewById(R.id.country);
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText phoneNumber = findViewById(R.id.phoneNumber);
        EditText password2 = findViewById(R.id.password2);
        EditText ConfirmPassword = findViewById(R.id.confirmPassword);
        EditText email2 = findViewById(R.id.email2);
        TextView textView9 = findViewById(R.id.textView9);
        Button createAccount = findViewById(R.id.createaccount);
        imageView = findViewById(R.id.profilPhoto);
        floatingActionButton = findViewById(R.id.updateProfileButton);
        Button back = findViewById(R.id.back);
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
                    System.out.println("Hashed Password: " + hashedPasswordString[0]);
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
            Cursor cursor = dataBaseHelper.getUser(email2.getText().toString());
            if (cursor.getCount() != 0) {
                emailError[0] += "Email is already exist";
            }
            if (emailError[0] != " ") {
                email2.setError(emailError[0]);
            }

            //if all the fields are correct then add the email and password to the data base
            if (passwordError[0] == " " && firstNameError[0] == " " && lastNameError[0] == " " && confirmPasswordError[0] == " " && emailError[0] == " "  && phoneNumberError[0] == " ") {
                ImageHelper.downloadImage();
                String imagePath = "";
                if(ImageHelper.uri != null)
                    imagePath = ImageHelper.uri.toString();

                dataBaseHelper.insertUser(email2.getText().toString(), hashedPasswordString[0], firstName.getText().toString(), lastName.getText().toString(), phoneNumber.getText().toString(), genderSpinner.getSelectedItem().toString(), countrySpinner.getSelectedItem().toString(), citySpinner.getSelectedItem().toString(), imagePath);
                imageView.setVisibility(View.VISIBLE);
                Toast toast = Toast.makeText(SingUpActivity.this, TOAST_TEXT, Toast.LENGTH_SHORT);
                toast.show();
                imageView.setImageURI(ImageHelper.uri);
                Intent intent = new Intent(SingUpActivity.this, SignInActivity.class);
                SingUpActivity.this.startActivity(intent);
                finish();
                //toast message that sign up is successful

            }
        });
        back.setOnClickListener(v -> {
            // go to the sign in activity
            Intent intent = new Intent(SingUpActivity.this, SignInActivity.class);
            SingUpActivity.this.startActivity(intent);
            finish();

        });

        floatingActionButton.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //imageView.setImageURI(data.getData());
        if (resultCode == RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            imageView.setImageURI(data.getData());
            //save the uri in variable
            ImageHelper.uri = data.getData();
            //store the image in firebase
            uploadImage();

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        }
    }

    // define onReume
    @Override
    protected void onResume() {
        super.onResume();
           //download the image from firebase
    }

}