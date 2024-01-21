package com.example.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignInAsAdmin extends AppCompatActivity {
    SharedPrefManagerAdmin sharedPrefManagerAdmin;
    public static  String adminEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as_admin);
        Button signAsAdmin = findViewById(R.id.signInAsAdmin);
        Button signUpAsAdmin = findViewById(R.id.signUpAdmin);
        CheckBox rememberMe = findViewById(R.id.rememberForAdmin);
        EditText email = findViewById(R.id.emailForAdmin);
        EditText password = findViewById(R.id.passwordForAdmin);
        Button forgetPassword = findViewById(R.id.forgetPassword2);
        AdminDataBase adminDataBase= new AdminDataBase(SignInAsAdmin.this,"admin",null,1);
        SharedPrefManagerAdmin sharedPrefManagerAdmin = SharedPrefManagerAdmin.getInstance(this);
        // read the email from the shared preferences
        forgetPassword.setOnClickListener(v -> {
            Toast.makeText(SignInAsAdmin.this, "Please contact the admin",
                    Toast.LENGTH_SHORT).show();
          AlertDialog.Builder builder = new AlertDialog.Builder(SignInAsAdmin.this);
            String message = "Please contact the admin to reset your password";
            builder.setMessage(message);
            builder.setIcon(R.drawable.baseline_security_update_warning_24);
            builder.setTitle("Forget Password");
            builder.setPositiveButton("OK", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();


        });
        signUpAsAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(SignInAsAdmin.this, SignUpAsAdmin.class);
            startActivity(intent);
            finish();
        });
        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(SignInAsAdmin.this,SignInChoices.class);
            SignInAsAdmin.this.startActivity(intent);
            finish();
        });
        email.setText(sharedPrefManagerAdmin.readString("Email", ""));
        password.setText(sharedPrefManagerAdmin.readString("Password", ""));
        signAsAdmin.setOnClickListener(v -> {

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
                Cursor cursor = adminDataBase.getAdmin(emailString);
                if (cursor.getCount() == 0){
                    email.setError("Email not found");
                }else{
                    cursor.moveToFirst();
                    //check if the password is correct
                    if (hashedPasswordString.equals(cursor.getString(1))){
                        adminEmail = emailString;

                        //if remember me is checked
                        if (rememberMe.isChecked()){
                            sharedPrefManagerAdmin.writeString("Email", email.getText().toString());
                            sharedPrefManagerAdmin.writeString("Password", password.getText().toString());
                            Toast.makeText(SignInAsAdmin.this, "Sign In Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(SignInAsAdmin.this, HomeAdmin.class);
                        SignInAsAdmin.this.startActivity(intent);
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