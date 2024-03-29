package com.example.androidproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.androidproject.R;
import com.example.androidproject.activities.authorization.SignInChoices;
import com.example.androidproject.database.helpers.CarsDataBase;
import com.example.androidproject.models.Car;
import com.example.androidproject.preferences.shared.SharedPrefManager;
import com.example.androidproject.tasks.connection.async.ConnectionAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    SharedPrefManager sharedPrefManager;

    Button connect;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    public static List<Car> allCars = new ArrayList<>();
    public static List<Car> cars_menu = new ArrayList<>();
    public static List<Car> offerscars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // define database of cars
        setContentView(R.layout.activity_main);
        connect = findViewById(R.id.connect);
        connect.setOnClickListener(v -> {
            ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
            connectionAsyncTask.execute("https://mpf340e07bcb9e7cdccc.free.beeceptor.com/data");
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
        this.allCars = cars;
        // add the cars to the database of cars
        CarsDataBase carsDataBase = new CarsDataBase(this, "CarsDataBase", null, 1);
        CarsDataBase offersDataBase = new CarsDataBase(this, "offersDataBase", null, 1);
        for (Car car : cars) {
            // no redundant data
            if (carsDataBase.isExist(car.getId())) {
                this.cars_menu.add(car);
                continue;
            }else if (offersDataBase.isExist(car.getId())){
                this.offerscars.add(car);
                continue;
            }
            else if (car.isOffers() == true) {
                this.offerscars.add(car);
                offersDataBase.insertCar(car.getId(), car.getType(), car.getFactoryName(), car.getModel(), String.valueOf(car.getPrice()), car.getName(), String.valueOf(car.isOffers()), car.getFuelType());
            } else if (car.isOffers() == false) {
                this.cars_menu.add(car);
                carsDataBase.insertCar(car.getId(), car.getType(), car.getFactoryName(), car.getModel(), String.valueOf(car.getPrice()), car.getName(), String.valueOf(car.isOffers()), car.getFuelType());
            }
        }
        Intent intent = new Intent(MainActivity.this, SignInChoices.class);
        startActivity(intent);
        finish();
    }

}




