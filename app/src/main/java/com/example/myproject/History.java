package com.example.myproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.reycycler.HistoryPaidRecyclerAdapter;
import com.example.myproject.reycycler.HistoryWaitingForPaymentRecycletAdapter;


public class History extends  NavBaseGarage{
    RecyclerView historyPaidRecycler;
    RecyclerView historyWaitingRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getLayoutInflater().inflate(R.layout.activity_history, findViewById(R.id.fragmentGarage));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        HistoryPaidRecyclerAdapter adapter=new HistoryPaidRecyclerAdapter(this);
        historyPaidRecycler=findViewById(R.id.historyPaidRecycler);
        historyPaidRecycler.setAdapter(adapter);
        historyPaidRecycler.setLayoutManager(new LinearLayoutManager(this));
        HistoryWaitingForPaymentRecycletAdapter adapter2=new HistoryWaitingForPaymentRecycletAdapter(this);
        historyWaitingRecycler=findViewById(R.id.historyWaitingRecycler);
        historyWaitingRecycler.setAdapter(adapter2);
        historyWaitingRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
}