package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ClientReport extends NavBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_client_report, findViewById(R.id.fragment));

        RecyclerView recycler = findViewById(R.id.rcycReprot);

        String[] captions = new String[Cars.car.length];
        int[] ids = new int[Cars.car.length];


        for(int i = 0; i<captions.length;i++){
            captions[i] = Cars.car[i].getName();
            ids[i] = Cars.car[i].getImage();

        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(captions, ids);
        recycler.setAdapter(adapter);
    }
}