package com.example.myproject;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchGarage extends NavBaseActivity {
    private RecyclerView recyclerViewGarageSearch;
    private GarageAdapter itemAdapter;
    private List<GarageMockup> itemList;
    Button btnSearch;
    private Spinner searchTypeSpinner;
    private AutoCompleteTextView autoCompleteSearch;
    private ConstraintLayout constraintLayout;
    private List<String> locations = new ArrayList<>();
    private List<String> garages = new ArrayList<>();
    private List<String> services = new ArrayList<>();
    int click;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_search_garage, findViewById(R.id.fragment));
        btnSearch = findViewById(R.id.btnSearch);
        RecyclerView recycler = findViewById(R.id.recyclerViewGarageSearch);
        searchTypeSpinner = findViewById(R.id.search_type_spinner);
        autoCompleteSearch = findViewById(R.id.autoCompleteSearch);
        constraintLayout=findViewById(R.id.drawer_layout);
//        String[] captions = new String[GarageMockup.gar.length];
//        int[] ids = new int[GarageMockup.gar.length];
//
//
//        for (int i = 0; i < captions.length; i++) {
//            captions[i] = GarageMockup.gar[i].getGarageName();
//            ids[i] = GarageMockup.gar[i].getLogoImageResource();
//
//        }
//        recycler.setLayoutManager(new LinearLayoutManager(this));
//        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(new ArrayList<>());
//        recycler.setAdapter(adapter);

//        btnSearch.setOnClickListener(e -> {
//            Intent intent = new Intent(SearchGarage.this, GarageProfile.class);
//            startActivity(intent);
//        });
         click=0;
        autoCompleteSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    btnSearch.setVisibility(View.VISIBLE);
                    searchTypeSpinner.setVisibility(View.GONE);
                    click=1;
                } else {
                    btnSearch.setVisibility(View.GONE);
                    searchTypeSpinner.setVisibility(View.VISIBLE);
                    click=0;
                }
            }
        });
        constraintLayout.setOnTouchListener((v, event) -> {
                if (click==0) {
                    btnSearch.setVisibility(View.GONE);
                    searchTypeSpinner.setVisibility(View.VISIBLE);
                    return  true;
                }

            return false;
        });
        autoCompleteSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Update suggestions dynamically
                filterSuggestions(s.toString(), searchTypeSpinner.getSelectedItem().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });

        requestQueue = Volley.newRequestQueue(this);

        setupSpinner();

        autoCompleteSearch.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            Toast.makeText(SearchGarage.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
        });

        btnSearch.setOnClickListener(v -> performSearch());
        fetchDataFromServer();
    }

    private void setupSpinner() {
        String [] str={"Name of Garage", "Location", "Service"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,str

        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(spinnerAdapter);
    }

    private void fetchDataFromServer() {
        String url = "http://10.0.2.2/Php/Search.php";;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse "garagee" data
                            JSONArray garageArray = response.getJSONArray("garagee");
                            for (int i = 0; i < garageArray.length(); i++) {
                                JSONObject garageObject = garageArray.getJSONObject(i);
                                String NameOfGarage = garageObject.getString("NameOfGarage");
//                                String Image = garageObject.getString("Image");

                                garages.add(garageObject.getString("NameOfGarage"));
                                Log.d(TAG , garageObject.getString("NameOfGarage"));
                                locations.add(garageObject.getString("location"));
                                Log.d(TAG , garageObject.getString("location"));
                            }

                            // Parse "services" data
                            JSONArray serviceArray = response.getJSONArray("services");
                            for (int i = 0; i < serviceArray.length(); i++) {
                                JSONObject serviceObject = serviceArray.getJSONObject(i);
                                services.add(serviceObject.getString("UserName"));
                                Log.d(TAG , serviceObject.getString("UserName"));
                            }

                            // Update AutoCompleteTextView suggestions
                            updateAutoCompleteSuggestions(searchTypeSpinner.getSelectedItem().toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SearchGarage.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchGarage.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
    private void filterSuggestions(String query, String searchType) {
        List<String> suggestions = new ArrayList<>();

        // Choose the list to filter based on search type
        if (searchType.equals("Location")) {
            suggestions = locations;
        } else if (searchType.equals("Name of Garage")) {
            suggestions = garages;
        } else if (searchType.equals("Service")) {
            suggestions = services;
        }

        // Filter the list based on the query
        List<String> filteredSuggestions = new ArrayList<>();
        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().contains(query.toLowerCase())) {
                filteredSuggestions.add(suggestion);
            }
        }

        // Update AutoCompleteTextView suggestions
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, filteredSuggestions);
        autoCompleteSearch.setAdapter(adapter);

        // Show dropdown
        autoCompleteSearch.showDropDown();
    }
    private void updateAutoCompleteSuggestions(String searchType) {
        List<String> suggestions = new ArrayList<>();
        if (searchType.equals("Location")) {
            suggestions = locations;
        } else if (searchType.equals("Name of Garage")) {
            suggestions = garages;
        } else if (searchType.equals("Service")) {
            suggestions = services;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, suggestions);
        autoCompleteSearch.setAdapter(adapter);
    }

    private void performSearch() {
        String selectedType = searchTypeSpinner.getSelectedItem().toString();
        String query = autoCompleteSearch.getText().toString();
        Intent intent=new Intent(SearchGarage.this,ClientGarageActivity.class);
        startActivity(intent);

//        Toast.makeText(this, "Searching for " + query + " in " + selectedType, Toast.LENGTH_SHORT).show();
        // Use query and selectedType to filter data in RecyclerView
    }
}

