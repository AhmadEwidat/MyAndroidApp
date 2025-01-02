package com.example.myproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class ResponsePage extends NavBaseGarage {

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
        Button btnPickDate = findViewById(R.id.btnPickDate);
        TextView txtSelectedDate = findViewById(R.id.txtSelectedDate);
        Button btnPickTime = findViewById(R.id.btnPickTime);
        TextView txtSelectedTime = findViewById(R.id.txtSelectedTime);

        btnPickDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
                String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                txtSelectedDate.setText("Selected Date: " + date);
            }, year, month, day).show();
        });

        btnPickTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
                String time = String.format("%02d:%02d", selectedHour, selectedMinute);
                txtSelectedTime.setText("Selected Time: " + time);
            }, hour, minute, true).show();
        });
    }
}