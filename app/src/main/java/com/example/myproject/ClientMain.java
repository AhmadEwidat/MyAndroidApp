package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.ArrayList;

public class ClientMain extends NavBaseActivity {

    Button btnAddCar;
    TextView txtMainName,txtPhoneMain,txtloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_client_main, findViewById(R.id.fragment));
        btnAddCar = findViewById(R.id.btnAddCar);
        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList <String>arrayList= getIntent().getStringArrayListExtra("arr");
        txtMainName=findViewById(R.id.txtMainName);
        txtPhoneMain=findViewById(R.id.txtPhoneMain);
        txtloc=findViewById(R.id.txtLoc);
        txtMainName.setText(arrayList.get(0));
        txtPhoneMain.setText(arrayList.get(3));
        txtloc.setText(arrayList.get(2));

        btnAddCar.setOnClickListener(e -> {
            Intent intent = new Intent(ClientMain.this, carAdd.class);
            startActivity(intent);
        });





    }



}