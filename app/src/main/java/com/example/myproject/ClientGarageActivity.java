package com.example.myproject;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Mokup.cars;
import Mokup.services;
import Mokup.services_req;

public class ClientGarageActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String NameOfGarage;
    private ImageView garageLogo;
    private TextView txtGarageName, txtPhonGarage;
    private ListView servicesListView;
    private Spinner carSpinner;
    Button requestButton;
    services_req req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_garage);
        requestButton=findViewById(R.id.requestButton);
        NameOfGarage = getIntent().getStringExtra("Garage");
        if (NameOfGarage == null || NameOfGarage.isEmpty()) {
            Toast.makeText(this, "Garage name not provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
         req=new services_req();
        carSpinner =findViewById(R.id.carSpinner);
        txtGarageName = findViewById(R.id.NameOfGarage);
        txtPhonGarage = findViewById(R.id.txtPhonGarage);
        garageLogo = findViewById(R.id.garageLogo);
        servicesListView = findViewById(R.id.servicesListView);
        requestQueue = Volley.newRequestQueue(this);
        requestButton.setOnClickListener(e -> {
            if (carSpinner.getSelectedItem() == null) {
                Toast.makeText(ClientGarageActivity.this, "Please select a car", Toast.LENGTH_SHORT).show();
                return;
            }

            String selectedCar = carSpinner.getSelectedItem().toString();
            String garageUserName = req.getGaragee_UserName();

            if (garageUserName == null || garageUserName.isEmpty()) {
                Toast.makeText(ClientGarageActivity.this, "Garage details not loaded yet", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "http://10.0.2.2/Php/AddServiceRequest.php"; // رابط ملف PHP
            JSONObject postData = new JSONObject();
            try {
                postData.put("car", selectedCar);
                postData.put("garage_user_name", garageUserName);
            } catch (JSONException ex) {
                Log.e(TAG, "Error creating JSON for request", ex);
                Toast.makeText(ClientGarageActivity.this, "Error creating request data", Toast.LENGTH_SHORT).show();
                return;
            }

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    postData,
                    response -> {
                        try {
                            boolean success = response.getBoolean("success");
                            String message = response.getString("message");

                            if (success) {
                                Toast.makeText(ClientGarageActivity.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                            } else {
//                                Toast.makeText(ClientGarageActivity.this, "Failed: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex) {
//                            Log.e(TAG, "Error parsing response JSON", ex);
//                            Toast.makeText(ClientGarageActivity.this, "Error processing server response", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
//                        Log.e(TAG, "Error sending request", error);
//                        Toast.makeText(ClientGarageActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            );

            requestQueue.add(request);
        });
        setupSpinner();
        fetchDataFromServer();
    }

    private void setupSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ClientMain.mycars
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carSpinner.setAdapter(spinnerAdapter);
    }

    private void fetchDataFromServer() {
        String url = "http://10.0.2.2/Php/ClientGarage.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        boolean garageFound = false;
                        ArrayList<String> services = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject garageObject = response.getJSONObject(i);

                            if (garageObject.has("NameOfGarage") &&
                                    garageObject.getString("NameOfGarage").equals(NameOfGarage)) {
                                garageFound = true;


                                String UserName = garageObject.optString("NameOfGarage", "Unknown");
                                txtGarageName.setText(UserName);
                                req.setGaragee_UserName(UserName);

                                String Image = garageObject.optString("Image", "");
                                if (!Image.isEmpty()) {
                                    Glide.with(ClientGarageActivity.this)
                                            .load(Image)
                                            .into(garageLogo);
                                }

                                String Phone = garageObject.optString("Phone", "No phone available");
                                txtPhonGarage.setText(Phone);
                                // تعبئة الخدمات
                                JSONArray servicesArray = garageObject.optJSONArray("services");
                                if (servicesArray != null) {
                                    for (int j = 0; j < servicesArray.length(); j++) {
                                        services.add(servicesArray.getString(j));
                                        req.getArrayList().add(new services((i+6),servicesArray.getString(j),UserName));
                                    }
                                }
                                break;
                            }
                        }

                        if (garageFound) {
                            // إعداد الـ ListView
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    ClientGarageActivity.this,
                                    android.R.layout.simple_list_item_1,
                                    services
                            );
                            servicesListView.setAdapter(adapter);
                        } else {
                            txtGarageName.setText("Garage not found");
                            txtPhonGarage.setText("No phone available");
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "Error parsing JSON", e);
                        Toast.makeText(ClientGarageActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e(TAG, "Error fetching data", error);
                    Toast.makeText(ClientGarageActivity.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }
}
