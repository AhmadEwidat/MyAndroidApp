package com.example.myproject;

import static androidx.fragment.app.FragmentManager.TAG;

import static com.example.myproject.LogIn.account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Mokup.NotifactionsGarage;
import Mokup.cars;
import Mokup.services;
import Mokup.services_req;

public class GarageNotificationActivity extends NavBaseGarage {
    private  RequestQueue requestQueue ;
    private RecyclerView recyclerViewNotifaction;
    private List<NotifactionsGarage> items = new ArrayList<>();
    private static final String BASE_URL ="http://10.0.2.2/Php/NotificationGarage.php";
    ArrayList <services_req> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);

        getLayoutInflater().inflate(R.layout.activity_garage_notification, findViewById(R.id.fragmentGarage));

        recyclerViewNotifaction = findViewById(R.id.recyclerViewNotifaction);
        recyclerViewNotifaction.setLayoutManager(new LinearLayoutManager(this));

        loadItems() ;
    }
    @SuppressLint("RestrictedApi")
    public boolean Add(services ser, String garageName, String carId, services_req servicesReq,NotifactionsGarage notifactionsGarage){
        for (int i=0;i<arrayList.size();i++){
            Log.d(TAG , arrayList.get(i).getGaragee_UserName());
            if(arrayList.get(i).getGaragee_UserName().equals(garageName)&&arrayList.get(i).getCar().getId().equals(carId)){
                arrayList.get(i).getArrayList().add(ser);
                return true;
            }
        }
        arrayList.add(servicesReq);
        items.add(notifactionsGarage);
        return false;
    }
    private void loadItems() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            if (array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    String UserName = object.getString("AccountUserName");
                                    Log.d(TAG , UserName);
                                    String ServiceUserName = object.getString("ServiceUserName");
                                    Log.d(TAG, ServiceUserName);
                                    String Model = object.getString("Model");
                                    Log.d(TAG, Model);
                                    String image = object.getString("Image");
                                    Log.d(TAG, image);
                                    String CarId=object.getString("Id");
                                    Log.d(TAG,CarId );
                                    String Phone=object.getString("Phone");
                                    Log.d(TAG,Phone );
                                    String location=object.getString("location");
                                    Log.d(TAG, location);
                                    String price=object.getString("price");
                                    Log.d(TAG, price);
                                    String garagee_UserName=object.getString("garagee_UserName");
                                    String services_id=object.getString("service_id");
                                    String startTime=object.getString("startTime");
                                    String endTime=object.getString("endTime");
                                    String service_req=object.getString("service_req");
                                    services service=new services(Integer.valueOf(services_id),garagee_UserName,ServiceUserName);
                                    cars cars=new cars(CarId,image,Model,account);
                                    services_req req=new services_req(Integer.valueOf(service_req),startTime,"Service wait",endTime,cars,garagee_UserName);
                                    NotifactionsGarage notifactionsGarage = new NotifactionsGarage(UserName, Model,image,req);
                                    Add(service,garagee_UserName,CarId,req,notifactionsGarage);

                                }

                                AdapterNotfGarage adapter = new AdapterNotfGarage(GarageNotificationActivity.this, items);
                                recyclerViewNotifaction.setAdapter(adapter);

                            } else {
                                Toast.makeText(GarageNotificationActivity.this, "No cars found", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(GarageNotificationActivity.this, "Error parsing response", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(GarageNotificationActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(GarageNotificationActivity.this).add(stringRequest);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_notifications) {
            Intent intent = new Intent(this, GarageNotificationActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}