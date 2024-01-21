package com.example.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignInActivity extends AppCompatActivity {


    SharedPrefManager sharedPrefManager;
    public static String emailForProfile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        //empty the data base
        Button signIn = findViewById(R.id.signIn);
        Button signUp = findViewById(R.id.signUpAdmin);
        CheckBox rememberMe = findViewById(R.id.remember);
        Button forgetPassword = findViewById(R.id.forgetPassword);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        UserDataBase dataBaseHelper = new UserDataBase(SignInActivity.this, "projectDataBase1", null, 1);
        // read the email from the shared preferences
        email.setText(sharedPrefManager.readString("Email", ""));
        password.setText(sharedPrefManager.readString("Password", ""));
        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignInChoices.class);
            SignInActivity.this.startActivity(intent);
            finish();
        });
        // remove all the data from the data base
        forgetPassword.setOnClickListener(v -> {
          Toast.makeText(SignInActivity.this, "Please contact the admin",
                    Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
            String message = "Please contact the admin to reset your password";
            builder.setMessage(message);
            builder.setIcon(R.drawable.baseline_security_update_warning_24);
            builder.setTitle("Forget Password");
            builder.setPositiveButton("OK", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();

        });



        signUp.setOnClickListener(v -> {
            // remove all the data from the data base
            // go to the sign up page
            Intent intent = new Intent(SignInActivity.this,SingUpActivity.class);
            SignInActivity.this.startActivity(intent);
            finish();

        });

        signIn.setOnClickListener(v -> {

            // read email and password entered
            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();

            //encrypt the password
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-256");
                byte[] hashedPassword = md.digest(passwordString.getBytes());

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
                String hashedPasswordString = hexString.toString();
                //check if this email in the data base
                Cursor cursor = dataBaseHelper.getUser(emailString);
                if (cursor.getCount() == 0){
                    email.setError("Email not found");
                }else{
                    cursor.moveToFirst();
                    //check if the password is correct
                    if (hashedPasswordString.equals(cursor.getString(1))){
                        emailForProfile = emailString;

                        //if remember me is checked
                        if (rememberMe.isChecked()){
                            sharedPrefManager.writeString("Email", email.getText().toString());
                            sharedPrefManager.writeString("Password", password.getText().toString());
                            Toast.makeText(SignInActivity.this, "Sign In Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(SignInActivity.this, Home.class);
                        SignInActivity.this.startActivity(intent);
                        finish();
                        // go to the home page
                    }else{
                        password.setError("Password is incorrect");
                    }
                }

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        });
    }
}