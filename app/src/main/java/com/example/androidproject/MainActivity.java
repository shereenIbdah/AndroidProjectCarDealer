package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    Button connect;

    /**
     * SAJA Email : saja@gmail.com
     * Password : saja#1
     * **/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect = findViewById(R.id.connect);
        connect.setOnClickListener(v -> {
            ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
            connectionAsyncTask.execute("https://mpd0c7e43fe8f2e3163a.free.beeceptor.com/data");

        });

    }
    public void setButtonText(String text) {
        connect.setText(text);
    }

    public void setProgress(boolean progress) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void dataLoaded(List<Car> cars) {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }



}


