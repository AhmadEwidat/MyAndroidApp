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

public class GarageProfile extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button btnGaragePf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_profile);
        btnGaragePf=findViewById(R.id.btnGaragePf);

        drawerLayout = findViewById(R.id.Garagedrawer_layout);

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
        navigationView = findViewById(R.id.nav_view);
        // Apply window insets for edge-to-edge display
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {


                } else if (item.getItemId() == R.id.nav_notifications) {
                    Toast.makeText(GarageProfile.this, "Notifications selected", Toast.LENGTH_SHORT).show();
                    Intent intentNotifications = new Intent(GarageProfile.this, GarageNotificationActivity.class);
                    startActivity(intentNotifications);

                } else if (item.getItemId() == R.id.nav_inService) {
                    Toast.makeText(GarageProfile.this, "Report selected", Toast.LENGTH_SHORT).show();
                    Intent intentReport = new Intent(GarageProfile.this, inService.class);
                    startActivity(intentReport);

                }else if (item.getItemId() == R.id.nav_logout) {
                    Toast.makeText(GarageProfile.this, "Logout selected", Toast.LENGTH_SHORT).show();
                    Intent intentLogout = new Intent(GarageProfile.this, MainActivity.class);
                    startActivity(intentLogout);

                } else if (item.getItemId() == R.id.nav_history) {
                    Toast.makeText(GarageProfile.this, "History selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GarageProfile.this, History.class);
                    startActivity(intent);

                }
                else {
                    return false;
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
        btnGaragePf.setOnClickListener(e->{
            Intent intent=new Intent(GarageProfile.this,AddServicePage.class);
            startActivity(intent);
        });

    }
}