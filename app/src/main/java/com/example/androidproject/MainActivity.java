package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements detailsOfSelectedCarFragment.communicator{
    SharedPrefManager sharedPrefManager;

    Button connect;
    final FragmentManager fragmentManager = getSupportFragmentManager();


    public static List<Car> cars;

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
            connectionAsyncTask.execute("https://mp49b93c0a0b2dd1479e.free.beeceptor.com/data?fbclid=IwAR1Z711Q2vIicd9s9p-V5I7FNuGvJYrj3Q-x5Qr_s8G0-844CV3x8up4APg");

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
        this.cars = cars;
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

  detailsOfSelectedCarFragment detailsOfSelectedCarFragment = new detailsOfSelectedCarFragment();
    @Override
    public void respond(String data) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.root_layout, detailsOfSelectedCarFragment, "detailsOfSelectedCarFragment");
    }
}
/*
daata =
[
  {"type":"Grand Cherokee", "id":"2", "factory": "Jeep", "price": 45000, "model": "2023", "name": "Grand Cherokee Laredo"},
  {"type":"Wrangler", "id":"3", "factory": "Jeep", "price": 35000, "model": "2023", "name": "Wrangler Sport"},
  {"type":"Grand Caravan", "id":"4", "factory": "Dodge", "price": 30000, "model": "2023", "name": "Grand Caravan SE"},
  {"type":"Model 3", "id":"5", "factory": "Tesla", "price": 55000, "model": "2023", "name": "Model 3 Long Range"},
  {"type":"Aventador", "id":"6", "factory": "Lamborghini", "price": 400000, "model": "2023", "name": "Aventador S"},
  {"type":"Model Y", "id":"7", "factory": "Tesla", "price": 60000, "model": "2023", "name": "Model Y Performance"},
  {"type":"Mustang", "id":"8", "factory": "Ford", "price": 45000, "model": "2023", "name": "Mustang EcoBoost"},
  {"type":"Fiesta", "id":"9", "factory": "Ford", "price": 15000, "model": "2023", "name": "Fiesta SE"},
  {"type":"Alpine", "id":"10", "factory": "Alpine", "price": 60000, "model": "2023", "name": "Alpine A110S"},
  {"type":"Roadster", "id":"11", "factory": "Tesla", "price": 250000, "model": "2023", "name": "Roadster Plaid"},
  {"type":"Accord", "id":"12", "factory": "Honda", "price": 30000, "model": "2023", "name": "Accord LX"},
  {"type":"Model S", "id":"13", "factory": "Tesla", "price": 80000, "model": "2023", "name": "Model S Plaid"},
  {"type":"Prius", "id":"14", "factory": "Toyota", "price": 25000, "model": "2023", "name": "Prius L Eco"},
  {"type":"Grand Cherokee", "id":"15", "factory": "Jeep", "price": 50000, "model": "2023", "name": "Grand Cherokee Limited"},
  {"type":"Mercielago", "id":"16", "factory": "Lamborghini", "price": 500000, "model": "2023", "name": "Mercielago LP770"},
  {"type":"Countach", "id":"17", "factory": "Lamborghini", "price": 450000, "model": "2023", "name": "Countach LPI 800-4"},
  {"type":"Grand Cherokee", "id":"18", "factory": "Jeep", "price": 48000, "model": "2023", "name": "Grand Cherokee Overland"},
  {"type":"Expedition", "id":"19", "factory": "Ford", "price": 55000, "model": "2023", "name": "Expedition XLT"},
  {"type":"Model 3", "id":"20", "factory": "Tesla", "price": 48000, "model": "2023", "name": "Model 3 Standard Range Plus"},
  {"type":"Civic", "id":"21", "factory": "Honda", "price": 22000, "model": "2023", "name": "Civic LX"},
  {"type":"Element", "id":"22", "factory": "Honda", "price": 25000, "model": "2023", "name": "Element EX"},
  {"type":"1", "id":"23", "factory": "Unknown", "price": 0, "model": "Unknown", "name": "Unknown"},
  {"type":"Volt", "id":"24", "factory": "Chevrolet", "price": 34000, "model": "2023", "name": "Volt Premier"},
  {"type":"Volks", "id":"25", "factory": "Volkswagen", "price": 30000, "model": "2023", "name": "Volks Golf SE"}
]
 */


