package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Mokup.NotificationAdapter;

public class ClientNotification extends NavBaseActivity {
    private RequestQueue requestQueue;
    private ListView lstNotficationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        requestQueue = Volley.newRequestQueue(this);
        getLayoutInflater().inflate(R.layout.activity_client_notification, findViewById(R.id.fragment));
        lstNotficationClient = findViewById(R.id.lstNotficationClient);

        loadNotifications();
    }
    private void loadNotifications() {
        String url = "http://10.0.2.2/Php/NotificationClient.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<JSONObject> notifications = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                notifications.add(response.getJSONObject(i));
                            } catch (JSONException exception) {
                                Log.d("Volley error", exception.toString());
                            }
                        }

                        NotificationAdapter adapter = new NotificationAdapter(ClientNotification.this, notifications);
                        lstNotficationClient.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley error", error.toString());
            }
        });

        requestQueue.add(request);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_notifications) {
            Intent intent = new Intent(this, ClientNotification.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}