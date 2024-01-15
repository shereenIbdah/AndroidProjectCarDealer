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
            connectionAsyncTask.execute("https://mpbe83ab6381c3169f22.free.beeceptor.com/data");
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
        System.out.println("all cars " + allCars.size());
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
        System.out.println("cars menu " + carsDataBase.getAllCars().getCount());
        System.out.println("offers " + offersDataBase.getAllCars().getCount());
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


/*[

  {"type":"Sonic", "id":"26", "factory": "Chevrolet", "price": 22000, "model": "2023", "name": "Sonic LT"},
  {"type":"Fit", "id":"27", "factory": "Honda", "price": 23000, "model": "2023", "name": "Fit Sport"},
  {"type":"Camry", "id":"28", "factory": "Toyota", "price": 28000, "model": "2023", "name": "Camry LE"},
  {"type":"Focus", "id":"29", "factory": "Ford", "price": 20000, "model": "2023", "name": "Focus SE"},
  {"type":"Fiat", "id":"30", "factory": "Fiat", "price": 18000, "model": "2023", "name": "Fiat 500 Pop"},
  {"type":"Cruze", "id":"31", "factory": "Chevrolet", "price": 25000, "model": "2023", "name": "Cruze LS"},
  {"type":"Civic", "id":"32", "factory": "Honda", "price": 21000, "model": "2023", "name": "Civic Sport"},
  {"type":"Corolla", "id":"33", "factory": "Toyota", "price": 26000, "model": "2023", "name": "Corolla SE"},
  {"type":"Mustang", "id":"34", "factory": "Ford", "price": 30000, "model": "2023", "name": "Mustang GT"},
  {"type":"Beetle", "id":"35", "factory": "Volkswagen", "price": 24000, "model": "2023", "name": "Beetle S"},
  {"type":"Spark", "id":"36", "factory": "Chevrolet", "price": 18000, "model": "2023", "name": "Spark LS"},
  {"type":"Fit", "id":"37", "factory": "Honda", "price": 22000, "model": "2023", "name": "Fit LX"},
  {"type":"Prius", "id":"38", "factory": "Toyota", "price": 24000, "model": "2023", "name": "Prius C"},
  {"type":"Fiesta", "id":"39", "factory": "Ford", "price": 17000, "model": "2023", "name": "Fiesta S"},
  {"type":"Jetta", "id":"40", "factory": "Volkswagen", "price": 26000, "model": "2023", "name": "Jetta SE"},
  {"type":"Soul", "id":"41", "factory": "Kia", "price": 23000, "model": "2023", "name": "Soul LX"},
  {"type":"Malibu", "id":"42", "factory": "Chevrolet", "price": 27000, "model": "2023", "name": "Malibu LS"}
]
*/


