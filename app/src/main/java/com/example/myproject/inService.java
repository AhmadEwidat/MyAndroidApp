package com.example.myproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.reycycler.InServiceRecyclerAdapter;


public class inService extends NavBaseGarage {
    RecyclerView inServiceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getLayoutInflater().inflate(R.layout.activity_in_service, findViewById(R.id.fragmentGarage));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InServiceRecyclerAdapter adapter=new InServiceRecyclerAdapter(this);
        inServiceView=findViewById(R.id.inServiceView);
        inServiceView.setAdapter(adapter);
        inServiceView.setLayoutManager(new LinearLayoutManager(this));
    }
}