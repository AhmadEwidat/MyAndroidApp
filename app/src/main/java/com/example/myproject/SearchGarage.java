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

public class SearchGarage extends NavBaseActivity {
    private RecyclerView recyclerViewGarageSearch;
    private GarageAdapter itemAdapter;
    private List<GarageMockup> itemList;
    Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_search_garage, findViewById(R.id.fragment));
        btnSearch = findViewById(R.id.btnSearch);
        RecyclerView recycler = findViewById(R.id.recyclerViewGarageSearch);

        String[] captions = new String[GarageMockup.gar.length];
        int[] ids = new int[GarageMockup.gar.length];


        for (int i = 0; i < captions.length; i++) {
            captions[i] = GarageMockup.gar[i].getGarageName();
            ids[i] = GarageMockup.gar[i].getLogoImageResource();

        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(captions, ids);
        recycler.setAdapter(adapter);

        btnSearch.setOnClickListener(e -> {
            Intent intent = new Intent(SearchGarage.this, GarageProfile.class);
            startActivity(intent);
        });
    }
}