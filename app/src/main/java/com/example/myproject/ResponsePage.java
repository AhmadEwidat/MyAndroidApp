package com.example.myproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ResponsePage extends NavBaseGarage {

    private String model;
    private String carId;
    private String image;
    private String userName;
    private int notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getLayoutInflater().inflate(R.layout.activity_response_page, findViewById(R.id.fragmentGarage));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.response), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        model = getIntent().getStringExtra("Model");
        //  carId = getIntent().getStringExtra("CarId");
        image = getIntent().getStringExtra("Image");
        //  userName = getIntent().getStringExtra("UserName");
        notificationId = getIntent().getIntExtra("NotificationId", -1);
        TextView txtModel = findViewById(R.id.txtCarModel);
        ImageView imgLogo =  findViewById(R.id.imgLogo);
        txtModel.setText("Model: " + model);

        Button btnAccept = findViewById(R.id.btnAccept);
        Button btnReject = findViewById(R.id.btnReject);

        btnAccept.setOnClickListener(v -> {
            String url = "http://10.0.2.2/Php/Put.php";
            acceptNotification(url);
        });

        btnReject.setOnClickListener(v -> {
            String url = "http://10.0.2.2/Php/Delete.php";
            rejectNotification(url);
        });
    }

    private void acceptNotification(String url) {
        if (notificationId == -1) {
            Toast.makeText(this, "Invalid Notification ID", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("notificationId", notificationId);
            jsonParams.put("status", "Accepted");
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating request parameters", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonParams,
                response -> {
                    try {
                        String message = response.getString("message");
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ResponsePage.this, inService.class);
                        intent.putExtra("Model", model);
                        intent.putExtra("CarId", carId);
                        intent.putExtra("Image", image);
                        intent.putExtra("UserName", userName);
                        startActivity(intent);

                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(jsonObjectRequest);
    }

    private void rejectNotification(String url) {
        if (notificationId == -1) {
            Toast.makeText(this, "Invalid Notification ID", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("notificationId", notificationId);
            jsonParams.put("status", "Rejected");
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating request parameters", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonParams,
                response -> {
                    try {
                        String message = response.getString("message");
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(jsonObjectRequest);
    }
}