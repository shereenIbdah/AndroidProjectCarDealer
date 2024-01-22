package com.example.androidproject.activities;

import static com.example.androidproject.activities.authorization.admin.SignInAsAdmin.adminEmail;
import static com.example.androidproject.activities.authorization.admin.SignUpAsAdmin.adminName;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.androidproject.activities.authorization.admin.SignInAsAdmin;
import com.example.androidproject.database.helpers.AdminDataBase;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.databinding.ActivityHomeAdminBinding;

public class HomeAdmin extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeAdminBinding binding;
    AdminDataBase adminDataBase= new AdminDataBase(HomeAdmin.this,"admin",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHomeAdmin.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_admin, R.id.nav_addadmin, R.id.nav_customer, R.id.nav_view_reservation)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.getMenu().findItem(R.id.nav_out2).setOnMenuItemClickListener(menuItem -> {
            //ask for confirmation
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeAdmin.this);
            builder.setTitle(Html.fromHtml("<font color='#E91E63'>Confirmation</font>"));
            builder.setMessage("Are you sure you want to sign out?");
            builder.setIcon(R.drawable.baseline_logout_24);
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                Intent intent = new Intent(HomeAdmin.this, SignInAsAdmin.class);
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
        getMenuInflater().inflate(R.menu.home_admin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_admin);
        TextView textView = findViewById(R.id.profilemail);
        textView.setText(adminEmail+"");
        //get the name from the data base
        Cursor cursor = adminDataBase.getAdmin(adminEmail);
        cursor.moveToFirst();
        adminName = cursor.getString(2);
        TextView textView1 = findViewById(R.id.profilname);
        textView1.setText(adminName+"");
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}