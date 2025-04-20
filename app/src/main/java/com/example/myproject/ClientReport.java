package com.example.myproject;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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

import Mokup.Report;
import Mokup.ReportAdapter;

public class ClientReport extends NavBaseActivity {
    private RequestQueue requestQueue;
    private RecyclerView recyclerViewReport;
    private ReportAdapter reportAdapter;
    private List<Report> items = new ArrayList<>();
    //private RecyclerView RecCars;
    private static final String BASE_URL ="http://10.0.2.2/Php/Report.php";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getLayoutInflater().inflate(R.layout.activity_client_report, findViewById(R.id.fragment));

        recyclerViewReport = findViewById(R.id.recyclerViewNotifaction);
        recyclerViewReport.setLayoutManager(new LinearLayoutManager(this));


        requestQueue = Volley.newRequestQueue(this);

        BuildReport();
    }

    private void BuildReport() {
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
                                    String NameOfGarage = object.getString("NameOfGarage");
                                    Log.d(TAG , "onResponse: "+NameOfGarage);
                                    String Image = object.getString("Image");
                                    Log.d(TAG , "onResponse: "+Image);
                                    String CarModel = object.getString("Model");
                                    Log.d(TAG , "onResponse: "+CarModel);
                                    String CarId = object.getString("CarId");
                                    Log.d(TAG , "onResponse: "+CarId);
                                    String ServiceUserName = object.getString("UserName");
                                    Log.d(TAG , "onResponse: "+ServiceUserName);
                                    String ServicePrice = object.getString("price");
                                    Log.d(TAG , "onResponse: "+ServicePrice);
                                    String ServiceDescription = object.getString("description");
                                    Log.d(TAG , "onResponse: "+ServiceDescription);
                                    String ServiceReqStatus = object.getString("status");
                                    Log.d(TAG , "onResponse: "+ServiceReqStatus);
                                    String ServiceReqStartTime = object.getString("startTime");
                                    Log.d(TAG , "onResponse: "+ServiceReqStartTime);
                                    String ServiceReqEndTime = object.getString("endTime");


                                    Report report = new Report(NameOfGarage,Image,CarModel,CarId,ServiceUserName,ServicePrice,ServiceDescription,ServiceReqStatus,ServiceReqStartTime,ServiceReqEndTime);
                                    items.add(report);
                                }

                                ReportAdapter adapter = new ReportAdapter(ClientReport.this, items);
                                recyclerViewReport.setAdapter(adapter);

                            } else {
                                Toast.makeText(ClientReport.this, "No cars found", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ClientReport.this, "Error parsing response", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(ClientReport.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(ClientReport.this).add(stringRequest);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_report) {
            Intent intent = new Intent(this, ClientReport.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}