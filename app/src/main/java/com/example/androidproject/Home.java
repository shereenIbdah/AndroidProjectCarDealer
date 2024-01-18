package com.example.androidproject;
import static com.example.androidproject.SignInActivity.emailForProfile;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidproject.databinding.ActivityHomeBinding;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
import org.w3c.dom.Text;

import java.util.Objects;
public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    UserDataBase dataBaseHelper = new UserDataBase(Home.this,"projectDataBase1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
        //binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
        /** @Override
        public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        });**/

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_carmenu, R.id.nav_reservedcars,R.id.nav_call, R.id.nav_profile , R.id.nav_favorites,R.id.nav_offers)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // if nav_out is selected, then intent to sign in page\
        navigationView.getMenu().findItem(R.id.nav_out).setOnMenuItemClickListener(menuItem -> {
            //ask for confirmation
            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setTitle(Html.fromHtml("<font color='#E91E63'>Confirmation</font>"));
            builder.setMessage("Are you sure you want to sign out?");
            builder.setIcon(R.drawable.baseline_logout_24);
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                Intent intent = new Intent(Home.this, SignInActivity.class);
                startActivity(intent);
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> {
                //do nothing

            });
            builder.create().show();
            return true;


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

   @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        //get the image in Firebase and set it to the imageView
       // StorageReference storageReference = FirebaseStorage.getInstance().getReference();
       TextView textView = findViewById(R.id.profileemail);
       textView.setText(emailForProfile);
       TextView textView1 = findViewById(R.id.name);
       //find the name from the data base
         Cursor cursor = dataBaseHelper.getUser(emailForProfile);
            cursor.moveToFirst();
            String name = cursor.getString(2);
            textView1.setText(name+"");




        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
