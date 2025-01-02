package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

public class ClientGarageActivity extends NavBaseActivity {

    // Expanded list of services
    String[] services = {
            "Oil Change",
            "Tire Rotation",
            "Brake Check",
            "Car Wash",
            "Battery Check",
            "Brake Change",
            "Engine Diagnostics",
            "Air Conditioning Repair",
            "Transmission Repair",
            "Wheel Alignment",
            "Exhaust System Repair",
            "Suspension System Check",
            "Coolant Flush",
            "Fuel System Cleaning"
    };

    String[] cars = {"Toyota Supra", "Kia Sorento", "Ford F150", "BMW X5M", "Mercedes-Benz C-Class"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_client_garage, findViewById(R.id.fragment));

        // Spinner for Cars
        Spinner carSpinner = findViewById(R.id.carSpinner);
        ArrayAdapter<String> carAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cars);
        carSpinner.setAdapter(carAdapter);

        // Spinner for Services
        Spinner servicesSpinner = findViewById(R.id.servicesSpinner);
        ArrayAdapter<String> servicesAdapterForSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, services);
        servicesSpinner.setAdapter(servicesAdapterForSpinner);

        // ListView with Switches for Services
        ListView servicesListView = findViewById(R.id.servicesListView);
        ServicesAdapter servicesAdapterForList = new ServicesAdapter();
        servicesListView.setAdapter(servicesAdapterForList);

        // Request Button
        Button requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(v -> {
            Toast.makeText(ClientGarageActivity.this, "Request Submitted Successfully!", Toast.LENGTH_SHORT).show();
        });

        // Notifications Button
        Button notificationButton = findViewById(R.id.notificationButton);
        notificationButton.setOnClickListener(v -> {
            Intent intent = new Intent(ClientGarageActivity.this, GarageNotificationActivity.class);
            startActivity(intent);
        });
    }

    // Custom Adapter for Services with Switches
    private class ServicesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return services.length;
        }

        @Override
        public Object getItem(int position) {
            return services[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(ClientGarageActivity.this).inflate(R.layout.service_item, parent, false);
            }

            TextView serviceName = convertView.findViewById(R.id.serviceName);
            Switch serviceSwitch = convertView.findViewById(R.id.serviceSwitch);

            serviceName.setText(services[position]);
            serviceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    Toast.makeText(ClientGarageActivity.this, services[position] + " selected", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }
}
