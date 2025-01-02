package com.example.myproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class GarageNotificationActivity extends NavBaseGarage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_garage_notification, findViewById(R.id.fragmentGarage));

        // Find the ListView (currently empty)
        ListView notificationListView = findViewById(R.id.notificationListView);

        // You can populate this ListView later with data
    }
}
