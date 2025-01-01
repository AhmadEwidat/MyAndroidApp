package com.example.myproject;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchGarage extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerViewGarageSearch;
    private GarageAdapter itemAdapter;
    private List<GarageMockup> itemList;
    Button btnSearch;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_garage);
        btnSearch=findViewById(R.id.btnSearch);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open_drawer,
                R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        recyclerViewGarageSearch = findViewById(R.id.recyclerViewGarageSearch);
        recyclerViewGarageSearch.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new GarageMockup("Garage A", "Location A", "John Doe", R.drawable.logog));
        itemList.add(new GarageMockup("Garage B", "Location B", "Jane Smith", R.drawable.logoga));
        itemList.add(new GarageMockup("Garage C", "Location C", "Mike Johnson", R.drawable.logogar));

        // Initialize adapter
        itemAdapter = new GarageAdapter(itemList);

        // Set the adapter to RecyclerView
        recyclerViewGarageSearch.setAdapter(itemAdapter);
        btnSearch.setOnClickListener(e->{
            Intent intent=new Intent(SearchGarage.this,GarageProfile.class);
            startActivity(intent);
        });
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(SearchGarage.this, "Home selected", Toast.LENGTH_SHORT).show();
                    Intent intentHome = new Intent(SearchGarage.this, ClientMain.class);
                    startActivity(intentHome);

                } else if (item.getItemId() == R.id.nav_notifications) {
                    Toast.makeText(SearchGarage.this, "Notifications selected", Toast.LENGTH_SHORT).show();
                    Intent intentNotifications = new Intent(SearchGarage.this, ClientMain.class);
                    startActivity(intentNotifications);

                } else if (item.getItemId() == R.id.nav_report) {
                    Toast.makeText(SearchGarage.this, "Report selected", Toast.LENGTH_SHORT).show();
                    Intent intentReport = new Intent(SearchGarage.this, ClientReport.class);
                    startActivity(intentReport);


                } else if (item.getItemId() == R.id.nav_logout) {
                    Toast.makeText(SearchGarage.this, "Logout selected", Toast.LENGTH_SHORT).show();
                    Intent intentLogout = new Intent(SearchGarage.this, MainActivity.class);
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