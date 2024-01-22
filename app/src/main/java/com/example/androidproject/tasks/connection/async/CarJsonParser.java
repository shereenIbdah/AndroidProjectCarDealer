package com.example.androidproject.tasks.connection.async;

import com.example.androidproject.models.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class CarJsonParser {
    public static List<Car> getObjectFromJson(String json) {
        List<Car> cars;

        try {
            JSONArray jsonArray = new JSONArray(json);
            cars = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Car car = new Car();
                car.setType(jsonObject.getString("type"));
                car.setId(jsonObject.getString("id"));
                car.setFactoryName(jsonObject.getString("factory"));
                car.setModel(jsonObject.getString("model"));
                car.setPrice(jsonObject.getDouble("price"));
                car.setOffers(jsonObject.getBoolean("offers"));
                car.setName(jsonObject.getString("name"));
                car.setFuelType(jsonObject.getString("fuel"));
                //add to the
                cars.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return cars;
    }
}
