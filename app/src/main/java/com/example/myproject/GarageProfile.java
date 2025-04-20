package com.example.myproject;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myproject.reycycler.CaptionServiceAdapter;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import Mokup.garagee;
import Mokup.services;

public class GarageProfile extends NavBaseGarage {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private List<services> items = new ArrayList<>();
    private RecyclerView RecSrevice;
    private static final String BASE_URL = "http://10.0.2.2/Php/getService.php";
    private Button btnAddService;
    private TextView txtNameGarage,txtPhoneGarage,txtLocGarage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_garage_profile, findViewById(R.id.fragmentGarage));
        btnAddService=findViewById(R.id.btnAddService);
        ArrayList <String>arrayList= getIntent().getStringArrayListExtra("arr");
       RecSrevice = findViewById(R.id.RecService);
        RecSrevice.setLayoutManager(new LinearLayoutManager(this));
        List<service> serviceList = new ArrayList<>();
//        serviceList.add(new service("Service 1", "Description 1", 100, "10:00"));
//        serviceList.add(new service("Service 2", "Description 2", 200, "20:00"));
        txtNameGarage=findViewById(R.id.txtNameGarage);
        txtPhoneGarage=findViewById(R.id.txtPhoneGarage);
        txtLocGarage=findViewById(R.id.txtLocGarage);
        txtNameGarage.setText(((garagee)LogIn.account).getNameOfGarage());
        txtPhoneGarage.setText(String.valueOf (((garagee)LogIn.account).getPhone()));
        txtLocGarage.setText(((garagee)LogIn.account).getLocation());
//        ServiceAdapter adapter = new ServiceAdapter(serviceList);
//        recyclerView.setAdapter(adapter);

        btnAddService.setOnClickListener(e->{
            Intent intent=new Intent(GarageProfile.this,AddServicePage.class);
            startActivity(intent);
        });
        ShowServices();

    }
    private void ShowServices() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            if (array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    String UserName = object.getString("UserName");
                                    Log.d(TAG , "onResponse: "+UserName);
                                    String description = object.getString("description");
                                    String Time = object.getString("Time");
                                    String price = object.getString("price");
                                    String service_id = object.getString("service_id");
                                    String garagee_UserName=object.getString("garagee_UserName");
                                    services service=new services(Integer.valueOf(service_id),UserName,description,Time,Integer.valueOf(price),garagee_UserName);
                                    items.add(service);
                                }

                                // تعيين المحول (Adapter) لـ RecyclerView
                                CaptionServiceAdapter adapter = new CaptionServiceAdapter(GarageProfile.this, items);
                                RecSrevice.setAdapter(adapter);

                            } else {
                                Toast.makeText(GarageProfile.this, "No cars found", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(GarageProfile.this, "Error parsing response", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(GarageProfile.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(GarageProfile.this).add(stringRequest);
    }
}