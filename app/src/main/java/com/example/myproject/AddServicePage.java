package com.example.myproject;


import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myproject.NavBaseGarage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Mokup.garagee;
import Mokup.services;

public class AddServicePage extends NavBaseGarage {
    private Button btnAdd;
    private EditText textInputName;
    private EditText edtDescription;
    private EditText edtPrice;
    private EditText edtDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_add_service_page, findViewById(R.id.fragmentGarage));
        btnAdd = findViewById(R.id.btnAdd);
        textInputName = findViewById(R.id.editTextName);
        edtDescription = findViewById(R.id.edtDescription);
        edtPrice = findViewById(R.id.edtPrice);
        edtDay = findViewById(R.id.edtDay);
        String garagee_UserName =LogIn.account.getUserName();
        btnAdd.setOnClickListener(e -> {
            String UserName = textInputName.getText().toString();
            int price = Integer.parseInt(edtPrice.getText().toString());
            String Time = edtDay.getText().toString();
            String description = edtDescription.getText().toString();

            AddServices(UserName, garagee_UserName, description, price, Time);
        });

    }


    @SuppressLint("RestrictedApi")
    private void AddServices(String UserName, String garagee_UserName, String description, int price, String Time) {
        String url = "http://10.0.2.2/Php/AddService.php";

        RequestQueue queue = Volley.newRequestQueue(AddServicePage.this);


        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("garagee_UserName", garagee_UserName);
            Log.d(TAG, "ss" + garagee_UserName);
            jsonParams.put("UserName", UserName);
            Log.d(TAG, "ss" + UserName);
            jsonParams.put("description", description);
            Log.d(TAG, description);
            jsonParams.put("price", price);
            Log.d(TAG, "ss" + String.valueOf(price));
            jsonParams.put("Time", Time);
            Log.d(TAG, "ss" + Time);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String str = "";
                        try {
                            str = response.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(AddServicePage.this, str,
                                Toast.LENGTH_SHORT).show();

                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", error.toString());
                    }
                }
        );
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}