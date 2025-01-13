package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myproject.CaptionedImagesAdapter;
//import com.example.myproject.cars;
import com.example.myproject.R;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import Mokup.accountt;
import Mokup.cars;
import Mokup.clientss;
import Mokup.garagee;

public class ClientMain extends NavBaseActivity {
    private List<cars> items = new ArrayList<>();
    private RecyclerView RecCars;
    private static final String BASE_URL = "http://10.0.2.2/Php/getCars.php";
    private TextView txtMainName,txtPhoneMain,txtLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_client_main, findViewById(R.id.fragment));
        RecCars = findViewById(R.id.RecCars);
        txtMainName=findViewById(R.id.txtMainName);
        txtPhoneMain=findViewById(R.id.txtPhoneMain);
        txtLoc=findViewById(R.id.txtLoc);


        txtMainName.setText(((accountt)LogIn.account).getUserName());
        txtPhoneMain.setText(String.valueOf (((accountt)LogIn.account).getPhone()));
        txtLoc.setText(((accountt)LogIn.account).getLocation());



        RecCars.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
    }

    private void loadItems() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            if (array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    String id = object.getString("Id");
                                    String name = object.getString("Model");
                                    String image = object.getString("Image");

                                    cars car = new cars(id, image,name,LogIn.account);
                                    items.add(car);
                                }

                                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(ClientMain.this, items);
                                RecCars.setAdapter(adapter);

                            } else {
                                Toast.makeText(ClientMain.this, "No cars found", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ClientMain.this, "Error parsing response", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(ClientMain.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(ClientMain.this).add(stringRequest);
    }
}