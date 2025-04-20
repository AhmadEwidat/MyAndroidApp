package com.example.myproject;

import static androidx.fragment.app.FragmentManager.TAG;

import Mokup.*;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myproject.reycycler.HistoryPaidRecyclerAdapter;
import com.example.myproject.reycycler.HistoryWaitingForPaymentRecycletAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class History extends  NavBaseGarage{
    RecyclerView historyPaidRecycler;
    RecyclerView historyWaitingRecycler;
    ArrayList<services_req> PaidList=new ArrayList<>();
    ArrayList<services_req> WaitingList=new ArrayList<>();
    HistoryPaidRecyclerAdapter adapter;

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
        dataFetch(LogIn.account.getUserName(),"Paid");
        dataFetch(LogIn.account.getUserName(),"Payment%20wait");
        services services=new services(2,2,"description",LogIn.account.getUserName(),"ali");
        ArrayList<services>s=new ArrayList<>();
        s.add(services);
        accountt acount=new accountt( "ali",222222, "ssssssss");
        cars car=new cars("ssssssss","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjDUE5wAlt-s9hHNVcoLCO86RXHxjFQbI83g&s","kia",acount);
        services_req servicesReq=new services_req(2,"2222","waiting","sssss",s,acount,LogIn.account.getUserName(),car);
        PaidList.add(servicesReq);
        WaitingList.add(servicesReq);
        adapter=new HistoryPaidRecyclerAdapter(this,PaidList);
        historyPaidRecycler=findViewById(R.id.historyPaidRecycler);
        historyPaidRecycler.setLayoutManager(new LinearLayoutManager(this));
        historyPaidRecycler.setAdapter(adapter);
        HistoryWaitingForPaymentRecycletAdapter adapter2=new HistoryWaitingForPaymentRecycletAdapter(this,WaitingList);
        historyWaitingRecycler=findViewById(R.id.historyWaitingRecycler);
        historyWaitingRecycler.setAdapter(adapter2);
        historyWaitingRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    public void dataFetch(String garagee_UserName,String status){
        String finalStatus=status.trim().replace(" ","%20");
        garagee_UserName=garagee_UserName.trim().replace(" ","%20");
        String url = "http://10.0.2.2/Php/history.php?status="+status+"&garagee_UserName="+garagee_UserName;

        RequestQueue queue = Volley.newRequestQueue(History.this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONArray>() {
                    @SuppressLint("RestrictedApi")

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {

                            Log.d("slsl","oik: "+ response.length());
                            try {
                                JSONObject data = response.getJSONObject(i);
                                Log.d(TAG, "oik: "+data.toString());
                                Log.d(TAG, "onResponse: "+data.toString());
                                accountt acount=new accountt( data.get("accountt_UserName").toString(),Integer.parseInt(data.get("Phone").toString()), data.get("location").toString());
                                cars car=new cars(data.get("car_Id").toString(),data.get("Image").toString(),data.get("Model").toString(),acount);
                                Log.d(TAG, "onResponse: "+car.toString());
                                ArrayList<services> services=new ArrayList<>();
                                JSONArray servicesArray=data.getJSONArray("services");
                                for(int j=0;j<servicesArray.length();j++){
                                    JSONObject jService = servicesArray.getJSONObject(j);
                                    services.add(new services(jService.getInt("price"),jService.getInt("service_id"),jService.getString("description"),LogIn.account.getUserName(),data.getString("accountt_UserName")));
                                }
                                services_req servicesReq=new services_req(data.getInt("service_req"),data.getString("startTime"),data.getString("status"),data.getString("endTime"),services,acount,LogIn.account.getUserName(),car);
                                if(finalStatus.equals("Payment%20wait")){
                                    WaitingList.add(servicesReq);
                                    Log.d("waiting", "onResponse: "+WaitingList.toString());
                                    Log.d("wating", "onResponse: ");

                                }else{
                                    PaidList.add(servicesReq);
//                                    adapter.updateData(PaidList);
                                }
                                Log.d(TAG, "fl;d: "+WaitingList.toString());

                            } catch (JSONException exception) {
                                Log.d("volley error", exception.toString());
                            }
                        }
                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", error.toString());
                    }
                }
        );
        // below line is to make
        // a json object request.
        queue.add(request);
    }


}