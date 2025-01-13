package com.example.myproject;

import static androidx.fragment.app.FragmentManager.TAG;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

import Mokup.accountt;
import Mokup.clientss;
import Mokup.garagee;

public class LogIn extends AppCompatActivity {
    EditText editTextUsername;
    EditText editTextPassword;
    CheckBox checkRemember;
    Button btnLogin;
    Button btnSignin;
    RadioButton radCust, radGarage;
    SharedPreferences sharedPreference;
    private RequestQueue queue;
    public static final String SHARED_PREFERENCE_NAME = "sharedPreference";
    public static final String USER_NAME_KEY = "userName";
    public static final String PASSWORD_KEY = "password";
    public static accountt account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        queue = Volley.newRequestQueue(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginAccount), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreference = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        checkRemember = findViewById(R.id.checkRemember);
        radCust = findViewById(R.id.radCust);
        radGarage = findViewById(R.id.radGarage);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignin = findViewById(R.id.btnSignin);

        String userName = sharedPreference.getString(USER_NAME_KEY, null);
        String password = sharedPreference.getString(PASSWORD_KEY, null);
        if (userName != null && password != null) {
            editTextUsername.setText(userName);
            editTextPassword.setText(password);
        }

        btnLogin.setOnClickListener(e -> {
            String enteredUsername = editTextUsername.getText().toString().trim();

            String enteredPassword = editTextPassword.getText().toString().trim();

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (checkRemember.isChecked()) {
                editor.putString(USER_NAME_KEY, enteredUsername);
                editor.putString(PASSWORD_KEY, enteredPassword);
                editor.apply();
            }

            if (radCust.isChecked()) {
                String url = "http://10.0.2.2/Php/LogInClient.php";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onResponse(JSONArray response) {
                                boolean isAuthenticated = false;

                                for (int i = 0; i < response.length(); i++) {

                                    try {
                                        JSONObject user = response.getJSONObject(i);
                                        if(user.getString("UserName").equals(editTextUsername.getText().toString().trim())&&user.getString("Password").equals(editTextPassword.getText().toString().trim())){
                                            String Username = user.getString("UserName");
                                            Log.d(TAG , Username);
                                            String password = user.getString("Password");
                                            Log.d(TAG , password);
                                            String location = user.getString("location");
                                            Log.d(TAG , location);
                                            String Phone = user.getString("Phone");
                                            Log.d(TAG , Phone);
                                            String FullName = user.getString("FullName");
                                            Log.d(TAG , FullName);

                                            account=new clientss(Username,password,location,Integer.valueOf(Phone),FullName);
                                            Log.d(TAG, "onResponse:ds ");
//                                                JSONObject carObject = user.getJSONObject("car");
//                                                Cars car=new Cars(carObject.getString("Id"),carObject.getString("image"),carObject.getString("Model"));
//                                            ( (clientss)account).getArrayList().add(car);
                                            isAuthenticated = true;
                                            break;

                                        } } catch (JSONException exception) {
                                        Log.d("volley error", exception.toString());
                                    }
                                }

                                if (isAuthenticated) {
                                    Intent intent = new Intent(LogIn.this, ClientMain.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LogIn.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("volley error", error.toString());
                                Toast.makeText(LogIn.this, "Error connecting to the server", Toast.LENGTH_SHORT).show();
                            }
                        });

                queue.add(request);
            } else if (radGarage.isChecked()) {
                String url = "http://10.0.2.2/Php/LoginGarage.php";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onResponse(JSONArray response) {
                                boolean isAuthenticated = false;

                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject user = response.getJSONObject(i);
                                        if(user.getString("UserName").equals(editTextUsername.getText().toString().trim())&&user.getString("Password").equals(editTextPassword.getText().toString().trim())){
                                            String username = user.getString("UserName");
                                            Log.d(TAG , username);
                                            String password = user.getString("Password");
                                            Log.d(TAG , password);
                                            String location = user.getString("location");
                                            Log.d(TAG , location);
                                            String Phone = user.getString("Phone");
                                            Log.d(TAG , Phone);

                                            String NameOfGarage = user.getString("NameOfGarage");
                                            Log.d(TAG , NameOfGarage);
                                            account=new garagee(username,password,location,Integer.valueOf(Phone),NameOfGarage);
                                            isAuthenticated = true;
                                            break;

                                        }
                                    }catch (JSONException exception) {
                                        Log.d("volley error", exception.toString());
                                    }
                                }

                                if (isAuthenticated) {
                                    Intent intent = new Intent(LogIn.this, GarageProfile.class);
                                    startActivity(intent);


                                } else {
                                    Toast.makeText(LogIn.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("volley error", error.toString());
                                Toast.makeText(LogIn.this, "Error connecting to the server", Toast.LENGTH_SHORT).show();
                            }
                        });

                queue.add(request);



            }
        });

        btnSignin.setOnClickListener(e -> {
            if (radCust.isChecked()) {
                Intent intent = new Intent(LogIn.this, signUp.class);
                startActivity(intent);
            } else if (radGarage.isChecked()) {
                Intent intent = new Intent(LogIn.this, GarageSignUp.class);
                startActivity(intent);
            }
        });
    }
}