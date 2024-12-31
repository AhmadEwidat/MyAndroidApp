package com.example.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogIn extends AppCompatActivity {
    EditText editTextUsername;
    EditText editTextPassword;
    CheckBox checkRemember;
    Button  btnLogin;
    Button  btnSignin;
    RadioButton radCust,radGarage;
    SharedPreferences sharedPreference;
    public static final String SHARED_PREFERENCE_NAME="sharedPreference";
    public static final String USER_NAME_KEY="userName";
    public static final String PASSWORD_KEY="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginAccount), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
              });
        sharedPreference=getSharedPreferences(SHARED_PREFERENCE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreference.edit();
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextUsername=findViewById(R.id.editTextUsername);
        checkRemember=findViewById(R.id.checkRemember);
        radCust=findViewById(R.id.radCust);
        radGarage=findViewById(R.id.radGarage);
        btnLogin=findViewById( R.id.btnLogin);
        String userName=sharedPreference.getString(USER_NAME_KEY,null);
        String password=sharedPreference.getString(PASSWORD_KEY,null);
        if(userName!=null&&password!=null){
            editTextUsername.setText(userName);
            editTextPassword.setText(password);
        }
        btnLogin.setOnClickListener(e->{
            if(checkRemember.isChecked()){
                editor.putString(USER_NAME_KEY,editTextUsername.getText().toString());
                editor.putString(PASSWORD_KEY,editTextPassword.getText().toString());
                editor.apply();
            }
            if(radCust.isChecked()) {
                Intent intent = new Intent(LogIn.this, signUp.class);
                startActivity(intent);
            } else if (radGarage.isChecked()) {
                Intent intent = new Intent(LogIn.this, GarageSignUp.class);
                startActivity(intent);
            }
        });
        btnSignin=findViewById( R.id.btnSignin);
        btnSignin.setOnClickListener(e->{
            if(radCust.isChecked()) {
                Intent intent = new Intent(LogIn.this, SearchGarage.class);
                startActivity(intent);
            }else if (radGarage.isChecked()) {
                Intent intent = new Intent(LogIn.this, GarageSignUp.class);
                startActivity(intent);
            }
       });
    }
}