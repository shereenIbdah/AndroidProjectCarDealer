package com.example.androidproject;

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
                car.setFactoryName(jsonObject.getString("factoryName"));
                car.setType(jsonObject.getString("type"));
                car.setId(jsonObject.getString("id"));
                car.setModel(jsonObject.getString("model"));
                car.setPrice(jsonObject.getDouble("price"));
                car.setName(jsonObject.getString("name"));
                cars.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return cars;
    }
}
