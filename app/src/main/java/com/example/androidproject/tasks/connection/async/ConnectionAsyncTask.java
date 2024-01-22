package com.example.androidproject.tasks.connection.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.androidproject.activities.MainActivity;
import com.example.androidproject.models.Car;

import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, Void, Pair<String, List<Car>>> {
    Activity activity;


    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ((MainActivity) activity).setButtonText("Connecting");
        super.onPreExecute();
        ((MainActivity) activity).setProgress(true);
    }

    @Override
    protected Pair<String, List<Car>> doInBackground(String... params) {
        try {
            String data = HttpManager.getData(params[0]);
            List<Car> cars = CarJsonParser.getObjectFromJson(data);
            return new Pair<>(null, cars);  // No error, return cars
        } catch (Exception e) {
            String errorMessage = "Error: " + e.getMessage();
            return new Pair<>(errorMessage, null);  // Return error message
        }
    }

    @Override
    protected void onPostExecute(Pair<String, List<Car>> result) {
        super.onPostExecute(result);
        ((MainActivity) activity).setProgress(false);
       // List<Car> students = CarJsonParser.getObjectFromJson(result.first);
        if (result.second != null) {
            // Data loaded successfully
            ((MainActivity) activity).setButtonText("CONNECTED");
            ((MainActivity) activity).dataLoaded(result.second);

        } else {
            // Show error message if data loading failed
            ((MainActivity) activity).setButtonText("Connect");
            Toast.makeText(activity, "The all cars type does not load", Toast.LENGTH_LONG).show();

        }
    }
}
