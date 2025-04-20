package com.example.myproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Mokup.accountt;
import Mokup.cars;
import Mokup.services;
import Mokup.services_req;

public class CarServiceAdding extends NavBaseGarage {
    EditText editUserService;
    EditText editPhoneService;
    EditText editServiceLocation;
    ListView EditListView;
    Button btnStartService;
    Button btnFinishService;
    CheckBox checkBoxPayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getLayoutInflater().inflate(R.layout.activity_car_service_adding, findViewById(R.id.fragmentGarage));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Adding), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editUserService=findViewById(R.id.editUserService);
        editPhoneService=findViewById(R.id.editPhoneService);
        editServiceLocation=findViewById(R.id.editServiceLocation);
        EditListView=findViewById(R.id.EditListView);
        btnStartService=findViewById(R.id.btnStartService);
        btnFinishService=findViewById(R.id.btnFinishService);
        checkBoxPayed=findViewById(R.id.checkBoxPayed);
        String jobj= getIntent().getStringExtra("services_req");
        String jlist=getIntent().getStringExtra("list");
        Gson gson=new Gson();
        services_req s=gson.fromJson(jobj,services_req.class);
        editPhoneService.setText(String.valueOf(s.getAccountt_UserName().getPhone()));
        editUserService.setText(String.valueOf(s.getAccountt_UserName().getUserName()));
        editServiceLocation.setText(String.valueOf(s.getAccountt_UserName().getLocation()));
        Type listType = new TypeToken<List<services>>() {}.getType();
        List<services> servicesList = gson.fromJson(jlist, listType);
        ArrayList<String> stringlist=new ArrayList<>();
        for(int i=0;i<servicesList.size();i++){
            Log.d("item test", "onCreate: "+servicesList.get(i).getDescription());
            stringlist.add(servicesList.get(i).getDescription());
        }



        Log.d("list test", "onCreate: "+servicesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,stringlist);
        EditListView.setAdapter(adapter);
        btnFinishService.setOnClickListener(e->{
            DataSave("Paid",String.valueOf(s.getService_req()));
//            if(checkBoxPayed.isChecked()){
//
//            }
//            else{
//                DataSave("Payment Wait",String.valueOf(s.getService_req()));
//
//            }
        });


    }
    public void DataSave(String status,String serviceReq){
        String url = "http://10.0.2.2/Php/InServiceEdit.php?status=" + status + "&service_req=" + serviceReq;

        // Initialize RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a PUT request
        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("errrrrr222222222rr", "onErrorResponse: ");

                        // Handle the response
                        System.out.println("Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error]
                        Log.d("errrrrrrr", "onErrorResponse: ");
                        System.err.println("Error: " + error.getMessage());
                    }
                }
        );

        // Add the request to the queue
        requestQueue.add(putRequest);

    }
}