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

public class ClientMain extends AppCompatActivity {
    private DrawerLayout drawerLayout;
Button btnAddCar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);
        btnAddCar=findViewById(R.id.btnAddCar);
        drawerLayout = findViewById(R.id.drawerlayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_nav,
                R.string.close_nav
        );

        // Link toggle with DrawerLayout
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Apply window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAddCar.setOnClickListener(e->{
            Intent intent=new Intent(ClientMain.this,carAdd.class);
            startActivity(intent);
        });
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_search) {
                    Toast.makeText(ClientMain.this, "Home selected", Toast.LENGTH_SHORT).show();
                    Intent intentHome = new Intent(ClientMain.this, SearchGarage.class);
                    startActivity(intentHome);

                } else if (item.getItemId() == R.id.nav_notifications) {
                    Toast.makeText(ClientMain.this, "Notifications selected", Toast.LENGTH_SHORT).show();
                    Intent intentNotifications = new Intent(ClientMain.this, ClientNotification.class);
                    startActivity(intentNotifications);

                } else if (item.getItemId() == R.id.nav_report) {
                    Toast.makeText(ClientMain.this, "Report selected", Toast.LENGTH_SHORT).show();
                    Intent intentReport = new Intent(ClientMain.this, ClientReport.class);
                    startActivity(intentReport);


                } else if (item.getItemId() == R.id.nav_logout) {
                    Toast.makeText(ClientMain.this, "Logout selected", Toast.LENGTH_SHORT).show();
                    Intent intentLogout = new Intent(ClientMain.this, MainActivity.class);
                    startActivity(intentLogout);

                } else {
                    return false;
                }


                drawerLayout.closeDrawers();
                return true;
            }
        });
    }



}