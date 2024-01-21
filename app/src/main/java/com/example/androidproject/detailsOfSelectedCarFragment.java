package com.example.androidproject;

import static com.example.androidproject.SignInActivity.emailForProfile;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailsOfSelectedCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailsOfSelectedCarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public detailsOfSelectedCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailsOfSelectedCarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailsOfSelectedCarFragment newInstance(String param1, String param2) {
        detailsOfSelectedCarFragment fragment = new detailsOfSelectedCarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private String carId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            carId = args.getString("carId");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RatingDataBase ratingDataBase = new RatingDataBase(getContext(), "ratingDataBase", null, 1);
        View root = inflater.inflate(R.layout.fragment_details_of_selected_car, container, false);
        CarsDataBase carsDataBase = new CarsDataBase(getContext(), "CarsDataBase", null, 1);
        CarsDataBase offersDataBase = new CarsDataBase(getContext(), "offersDataBase", null, 1);
        Cursor information = carsDataBase.getCar(carId);
        Cursor information1 = offersDataBase.getCar(carId);
        ImageView imageView = root.findViewById(R.id.imageView10);
        RatingBar ratingBar = root.findViewById(R.id.ratingBar3);
        TextView ratingTextView = root.findViewById(R.id.averageRatingTextView);
        String email = emailForProfile;
        ratingTextView.setText(ratingDataBase.getRating(carId) + "");
        EditText comment = root.findViewById(R.id.commentEditText);
        //when click enter the comment will be added to the data base
        comment.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == event.KEYCODE_ENTER) {
                if (ratingDataBase.isExist(email, carId)) {
                    ratingDataBase.updateComment(email, carId, comment.getText().toString());
                    Toast.makeText(getContext(), "Comment updated", Toast.LENGTH_SHORT).show();
                    comment.setText("");
                } else {
                    ratingDataBase.addRate(email, carId, ratingBar.getRating(), comment.getText().toString());
                    Toast.makeText(getContext(), "Thank you for comment in this car", Toast.LENGTH_SHORT).show();
                    comment.setText("");

                }
                return true;
            }
            return false;
        });

        if (ratingDataBase.isExist(email, carId)) {
            ratingBar.setRating(ratingDataBase.getRating2(email, carId));
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            //if the user already rated the car keep the old rate on the rating bar
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser) {
                    int ratingValue = (int) (rating * 20);  // Convert rating to your scale (1 star = 20, 2 stars = 40, etc.)
                    if (ratingDataBase.isExist(email, carId)) {
                        ratingDataBase.updateRate(email, carId, rating);
                        Toast.makeText(getContext(), "You have already rated this car\n Rate updated", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        ratingDataBase.addRate(email, carId, rating,comment.getText().toString());
                        Toast.makeText(getContext(), "Thank you for rating this car", Toast.LENGTH_SHORT).show();
                    }

                }
                ratingTextView.setText(ratingDataBase.getRating(carId) + "");

            }

        });
        // Check if the cursor has data
        if (information.moveToFirst()) {
            // Find the TextView by its ID
            TextView factory = root.findViewById(R.id.factoryNameTextView);
            TextView name = root.findViewById(R.id.nameTextView);
            TextView type = root.findViewById(R.id.typeTextView);
            TextView price = root.findViewById(R.id.priceTextView);
            TextView model = root.findViewById(R.id.modelTextView);
            TextView fuel = root.findViewById(R.id.fuelTextView);

            // Access cursor data
            factory.setText(information.getString(2));
            name.setText(information.getString(5));
            type.setText(information.getString(1));
            price.setText(information.getString(4));
            model.setText(information.getString(3));
            fuel.setText(information.getString(9));
            // based on the name set the image
            // for each position set a different image
            switch (information.getString(2)) {
                case "Ford":
                    if (information.getString(5).equals("Fiesta SE"))
                        imageView.setImageResource(R.drawable.ford2);
                    else if (information.getString(5).equals("Mustang EcoBoost"))
                        imageView.setImageResource(R.drawable.ford1);
                    else
                        imageView.setImageResource(R.drawable.ford3);
                    break;
                case "Dodge":
                    imageView.setImageResource(R.drawable.dodge);
                    break;
                case "Jeep":
                    if (information.getString(5).equals("Grand Cherokee"))
                        imageView.setImageResource(R.drawable.jeep3);
                    else if (information.getString(5).equals("Wrangler Sport"))
                        imageView.setImageResource(R.drawable.jeep4);
                    else
                        imageView.setImageResource(R.drawable.jeep3);
                    break;
                case "Chevrolet":
                    imageView.setImageResource(R.drawable.chevrolet);
                    break;
                case "Toyota":
                    imageView.setImageResource(R.drawable.toyota);
                    break;
                case "Honda":
                    imageView.setImageResource(R.drawable.honda3);
                    break;
                case "Tesla":
                    if (information.getString(5).equals("Model 3 Long Range"))
                        imageView.setImageResource(R.drawable.tesla5);
                    else if (information.getString(5).equals("Model S Plaid"))
                        imageView.setImageResource(R.drawable.tesla2);
                    else if (information.getString(5).equals("Model Y Performance"))
                        imageView.setImageResource(R.drawable.tesla3);
                    else
                        imageView.setImageResource(R.drawable.tesla4);
                    break;
                case "Lamborghini":
                    imageView.setImageResource(R.drawable.lamborghini1);
                    break;
                default:
                  imageView.setImageResource(R.drawable.jeep5);
                    break;
            }
        }
        else if (information1.moveToFirst()) {
            // Find the TextView by its ID
            TextView factory = root.findViewById(R.id.factoryNameTextView);
            TextView name = root.findViewById(R.id.nameTextView);
            TextView type = root.findViewById(R.id.typeTextView);
            TextView price = root.findViewById(R.id.priceTextView);
            TextView model = root.findViewById(R.id.modelTextView);
            TextView fuel = root.findViewById(R.id.fuelTextView);

            // Access cursor data
            factory.setText(information1.getString(2));
            name.setText(information1.getString(5));
            type.setText(information1.getString(1));
            price.setText(information1.getString(4));
            model.setText(information1.getString(3));
            fuel.setText(information1.getString(9));
            switch (information1.getString(2)) {
                case "Ford":
                    if (information1.getString(5).equals("Fiesta SE"))
                        imageView.setImageResource(R.drawable.ford2);
                    else if (information1.getString(5).equals("Mustang EcoBoost"))
                        imageView.setImageResource(R.drawable.ford1);
                    else
                        imageView.setImageResource(R.drawable.ford3);
                    break;
                case "Dodge":
                    imageView.setImageResource(R.drawable.dodge);
                    break;
                case "Jeep":
                    if (information1.getString(5).equals("Grand Cherokee"))
                        imageView.setImageResource(R.drawable.jeep3);
                    else if (information1.getString(5).equals("Wrangler Sport"))
                        imageView.setImageResource(R.drawable.jeep4);
                    else
                        imageView.setImageResource(R.drawable.jeep3);
                    break;
                case "Chevrolet":
                    imageView.setImageResource(R.drawable.chevrolet);
                    break;
                case "Toyota":
                    imageView.setImageResource(R.drawable.toyota);
                    break;
                case "Honda":
                    imageView.setImageResource(R.drawable.honda3);
                    break;
                case "Tesla":
                    if (information1.getString(5).equals("Model 3 Long Range"))
                        imageView.setImageResource(R.drawable.tesla5);
                    else if (information1.getString(5).equals("Model S Plaid"))
                        imageView.setImageResource(R.drawable.tesla2);
                    else if (information1.getString(5).equals("Model Y Performance"))
                        imageView.setImageResource(R.drawable.tesla3);
                    else
                        imageView.setImageResource(R.drawable.tesla4);
                    break;
                case "Lamborghini":
                    imageView.setImageResource(R.drawable.lamborghini1);
                    break;
                default:
                    imageView.setImageResource(R.drawable.jeep5);
                    break;
            }
        } else {
            Log.d("TAG", "onCreateView: " + "no data");
        }
        // Close the cursor when done
        if (information != null && !information.isClosed()) {
            information.close();
        }

        return root;

    }
}