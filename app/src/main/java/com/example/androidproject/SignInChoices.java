package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class SignInChoices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_choices);
        Button signAsAdmin = findViewById(R.id.SignAsAdmin);
        Button signAsCustomer = findViewById(R.id.SignAsCustomer);
        signAsCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(SignInChoices.this, SignInActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(SignInChoices.this, "Welcome Our Customer", Toast.LENGTH_SHORT).show();

        });
        signAsAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(SignInChoices.this, SignInAsAdmin.class);
            startActivity(intent);
            finish();
            Toast.makeText(SignInChoices.this, "Welcome Our Admin", Toast.LENGTH_SHORT).show();

        });
    }
}