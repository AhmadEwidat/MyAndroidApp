package com.example.myproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class GarageProfile extends NavBaseGarage {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button btnGaragePf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_garage_profile, findViewById(R.id.fragmentGarage));
        btnGaragePf=findViewById(R.id.btnGaragePf);



        btnGaragePf.setOnClickListener(e->{
            Intent intent=new Intent(GarageProfile.this,AddServicePage.class);
            startActivity(intent);
        });

    }
}