package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class NavBaseGarage extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_base_garage);


        drawerLayout = findViewById(R.id.nav_layout_base_garage);
        navigationView = findViewById(R.id.base_nav_garage);

        Toolbar toolbar = findViewById(R.id.toolbar_base_garage);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    handleNavigation(item);
                    return true;
                }
            });
        }

    }
    protected void handleNavigation(MenuItem item) {


        if (item.getItemId() == R.id.nav_home) {
            Toast.makeText(NavBaseGarage.this, "home selected", Toast.LENGTH_SHORT).show();
            Intent intentNotifications = new Intent(NavBaseGarage.this, GarageProfile.class);
            startActivity(intentNotifications);

        } else if (item.getItemId() == R.id.nav_notifications) {
            Toast.makeText(NavBaseGarage.this, "Notifications selected", Toast.LENGTH_SHORT).show();
            Intent intentNotifications = new Intent(NavBaseGarage.this, GarageNotificationActivity.class);
            startActivity(intentNotifications);

        } else if (item.getItemId() == R.id.nav_inService) {
            Toast.makeText(NavBaseGarage.this, "Report selected", Toast.LENGTH_SHORT).show();
            Intent intentReport = new Intent(NavBaseGarage.this, inService.class);
            startActivity(intentReport);

        }else if (item.getItemId() == R.id.nav_logout) {
            Toast.makeText(NavBaseGarage.this, "Logout selected", Toast.LENGTH_SHORT).show();
            Intent intentLogout = new Intent(NavBaseGarage.this, MainActivity.class);
            startActivity(intentLogout);

        } else if (item.getItemId() == R.id.nav_history) {
            Toast.makeText(NavBaseGarage.this, "History selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NavBaseGarage.this, History.class);
            startActivity(intent);

        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }


}