package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements detailsOfSelectedCarFragment.communicator {
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
            connectionAsyncTask.execute("https://mp29ff306ce70567aac2.free.beeceptor.com/data");
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
        CarsDataBase carsDataBase = new CarsDataBase(this, "cars_menu", null, 1);
        CarsDataBase offersDataBase = new CarsDataBase(this, "offers_Cars", null, 1);
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
                offersDataBase.insertCar(car.getId(), car.getType(), car.getFactoryName(), car.getModel(), String.valueOf(car.getPrice()), car.getName(), String.valueOf(car.isOffers()));
            } else if (car.isOffers() == false) {
                this.cars_menu.add(car);
                carsDataBase.insertCar(car.getId(), car.getType(), car.getFactoryName(), car.getModel(), String.valueOf(car.getPrice()), car.getName(), String.valueOf(car.isOffers()));
            }
        }
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    detailsOfSelectedCarFragment detailsOfSelectedCarFragment = new detailsOfSelectedCarFragment();

    @Override
    public void respond(String data) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.root_layout, detailsOfSelectedCarFragment, "detailsOfSelectedCarFragment");
        fragmentTransaction.commit(); // commit the transaction
    }
}




/*[
  {"type":"Grand Cherokee", "id":"2", "factory": "Jeep", "price": 45000, "model": "2023", "name": "Grand Cherokee Laredo", "offers": false},
  {"type":"Wrangler", "id":"3", "factory": "Jeep", "price": 35000, "model": "2023", "name": "Wrangler Sport", "offers": false},
  {"type":"Grand Caravan", "id":"4", "factory": "Dodge", "price": 30000, "model": "2023", "name": "Grand Caravan SE", "offers": false},
  {"type":"Model 3", "id":"5", "factory": "Tesla", "price": 55000, "model": "2023", "name": "Model 3 Long Range", "offers": false},
  {"type":"Aventador", "id":"6", "factory": "Lamborghini", "price": 400000, "model": "2023", "name": "Aventador S", "offers": false},
  {"type":"Model Y", "id":"7", "factory": "Tesla", "price": 60000, "model": "2023", "name": "Model Y Performance", "offers": false},
  {"type":"Mustang", "id":"8", "factory": "Ford", "price": 45000, "model": "2023", "name": "Mustang EcoBoost", "offers": false},
  {"type":"Fiesta", "id":"9", "factory": "Ford", "price": 15000, "model": "2023", "name": "Fiesta SE", "offers": false},
  {"type":"Alpine", "id":"10", "factory": "Alpine", "price": 60000, "model": "2023", "name": "Alpine A110S", "offers": false},
  {"type":"Roadster", "id":"11", "factory": "Tesla", "price": 250000, "model": "2023", "name": "Roadster Plaid", "offers": false},
  {"type":"Accord", "id":"12", "factory": "Honda", "price": 30000, "model": "2023", "name": "Accord LX", "offers": false},
  {"type":"Model S", "id":"13", "factory": "Tesla", "price": 80000, "model": "2023", "name": "Model S Plaid", "offers": false},
  {"type":"Prius", "id":"14", "factory": "Toyota", "price": 25000, "model": "2023", "name": "Prius L Eco", "offers": false},
  {"type":"Grand Cherokee", "id":"15", "factory": "Jeep", "price": 50000, "model": "2023", "name": "Grand Cherokee Limited", "offers": false},
  {"type":"Mercielago", "id":"16", "factory": "Lamborghini", "price": 500000, "model": "2023", "name": "Mercielago LP770", "offers": false},
  {"type":"Countach", "id":"17", "factory": "Lamborghini", "price": 450000, "model": "2023", "name": "Countach LPI 800-4", "offers": false},
  {"type":"Grand Cherokee", "id":"18", "factory": "Jeep", "price": 48000, "model": "2023", "name": "Grand Cherokee Overland","offers":false},
  {"type":"Expedition", "id":"19", "factory": "Ford", "price": 55000, "model": "2023", "name": "Expedition XLT", "offers": false},
  {"type":"Model 3", "id":"20", "factory": "Tesla", "price": 48000, "model": "2023", "name": "Model 3 Standard Range Plus", "offers": false},
  {"type":"Civic", "id":"21", "factory": "Honda", "price": 22000, "model": "2023", "name": "Civic LX", "offers": false},
  {"type":"Element", "id":"22", "factory": "Honda", "price": 25000, "model": "2023", "name": "Element EX", "offers": false},
  {"type":"1", "id":"23", "factory": "Unknown", "price": 0, "model": "Unknown", "name": "Unknown", "offers": false},
  {"type":"Volt", "id":"24", "factory": "Chevrolet", "price": 34000, "model": "2023", "name": "Volt Premier", "offers": false},
  {"type":"Volks", "id":"25", "factory": "Volkswagen", "price": 30000, "model": "2023", "name": "Volks Golf SE", "offers": false},
  {"type":"Sonic", "id":"26", "factory": "Chevrolet", "price": 22000, "model": "2023", "name": "Sonic LT", "offers": true},
  {"type":"Fit", "id":"27", "factory": "Honda", "price": 23000, "model": "2023", "name": "Fit Sport", "offers": true},
  {"type":"Camry", "id":"28", "factory": "Toyota", "price": 28000, "model": "2023", "name": "Camry LE", "offers": true},
  {"type":"Focus", "id":"29", "factory": "Ford", "price": 20000, "model": "2023", "name": "Focus SE", "offers": true},
  {"type":"Fiat", "id":"30", "factory": "Fiat", "price": 18000, "model": "2023", "name": "Fiat 500 Pop", "offers": true},
  {"type":"Cruze", "id":"31", "factory": "Chevrolet", "price": 25000, "model": "2023", "name": "Cruze LS", "offers": true},
  {"type":"Civic", "id":"32", "factory": "Honda", "price": 21000, "model": "2023", "name": "Civic Sport", "offers": true},
  {"type":"Corolla", "id":"33", "factory": "Toyota", "price": 26000, "model": "2023", "name": "Corolla SE", "offers": true},
  {"type":"Mustang", "id":"34", "factory": "Ford", "price": 30000, "model": "2023", "name": "Mustang GT", "offers": true},
  {"type":"Beetle", "id":"35", "factory": "Volkswagen", "price": 24000, "model": "2023", "name": "Beetle S", "offers": true},
  {"type":"Spark", "id":"36", "factory": "Chevrolet", "price": 18000, "model": "2023", "name": "Spark LS", "offers": true},
  {"type":"Fit", "id":"37", "factory": "Honda", "price": 22000, "model": "2023", "name": "Fit LX", "offers": true},
  {"type":"Prius", "id":"38", "factory": "Toyota", "price": 24000, "model": "2023", "name": "Prius C", "offers": true},
  {"type":"Fiesta", "id":"39", "factory": "Ford", "price": 17000, "model": "2023", "name": "Fiesta S", "offers": true},
  {"type":"Jetta", "id":"40", "factory": "Volkswagen", "price": 26000, "model": "2023", "name": "Jetta SE", "offers": true},
  {"type":"Soul", "id":"41", "factory": "Kia", "price": 23000, "model": "2023", "name": "Soul LX", "offers": true},
  {"type":"Malibu", "id":"42", "factory": "Chevrolet", "price": 27000, "model": "2023", "name": "Malibu LS", "offers": true}
]*/
