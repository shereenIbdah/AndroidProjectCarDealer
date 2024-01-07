package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;

    /**
     * SAJA Email : saja@gmail.com
     * Password : saja#1
     * **/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button connect = findViewById(R.id.connect);
        connect.setOnClickListener(v -> {
            //befor that we need to load the Json data from the server
            // make intent to go to the sign in activity
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();

        });
}
}