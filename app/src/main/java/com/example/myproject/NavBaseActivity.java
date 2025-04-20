package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public abstract class NavBaseActivity extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_base);


        drawerLayout = findViewById(R.id.nav_layout_base);
        navigationView = findViewById(R.id.base_nav);

        Toolbar toolbar = findViewById(R.id.toolbar_base);
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
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            Intent homeIntent=new Intent(this,ClientMain.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
            startActivity(homeIntent);
        }
        else if (itemId == R.id.nav_search) {
            Intent searchIntent = new Intent(this,SearchGarage.class);
            searchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show();
            startActivity(searchIntent);
        } else if (itemId == R.id.nav_notifications) {
            Intent notifactionIntent = new Intent(this ,ClientNotification.class);
            notifactionIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Toast.makeText(this, "Notifications selected", Toast.LENGTH_SHORT).show();
            startActivity(notifactionIntent);
        } else if (itemId == R.id.nav_report) {
            Intent reportIteam = new Intent(this ,ClientReport.class);
            reportIteam.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Toast.makeText(this, "Report selected", Toast.LENGTH_SHORT).show();
            startActivity(reportIteam);
        } else if (itemId == R.id.nav_logout) {
            Intent logoutIteam = new Intent(this ,LogIn.class);
            logoutIteam.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Toast.makeText(this, "Logout selected", Toast.LENGTH_SHORT).show();
            startActivity(logoutIteam);
        } else {
            Toast.makeText(this, "Unknown item selected", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }



}