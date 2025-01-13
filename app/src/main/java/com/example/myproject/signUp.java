package com.example.myproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;

public class signUp extends AppCompatActivity {
    Button SignUpImageBTN;
    Button signUpBTN;
    TextView userTF;
    TextView PasswrodTF;
    TextView signUPPlateNumber;
    TextView NumberTF;
    Spinner SignUPManufactureSpinner;
    TextView SignUPIDNumber;
    String Url;
    DatePickerDialog datePickerDialog;
    String SelectedDate;
    ImageView SignUPImageView;

    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signcust), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();


    }
    public boolean dataCheck(){
        if(userTF.getText().toString().equals("")||userTF.getText().equals(null)||PasswrodTF.getText().toString().equals("")||PasswrodTF.getText().equals(null)||
                signUPPlateNumber.getText().toString().equals("")||signUPPlateNumber.getText().equals(null)||NumberTF.getText().toString().equals("")||NumberTF.getText().equals(null)||
                SignUPIDNumber.getText().toString().equals("")||SignUPIDNumber.getText().equals(null)||SelectedDate.equals("")||SelectedDate.equals(null)||Url.equals("")||Url.equals(null)){
            Toast.makeText(signUp.this,"Please enter all the information",Toast.LENGTH_SHORT);

        } else if(userTF.getText().length()<8||userTF.getText().toString().equals("")||userTF.getText().equals(null)){
            Toast.makeText(signUp.this, "username should be at least 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (PasswrodTF.getText().toString().equals("")||PasswrodTF.getText().toString().equals(null)||PasswrodTF.getText().toString().length()<8) {
            Toast.makeText(signUp.this, "password should be at least 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!PasswrodTF.getText().toString().matches(".*[A-Z].*")||
                !PasswrodTF.getText().toString().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            Toast.makeText(signUp.this, "password should contain at least one capital and one special character", Toast.LENGTH_SHORT).show();
        } else if (NumberTF.getText().toString().equals("")||NumberTF.getText().toString().equals(null)) {
            Toast.makeText(signUp.this,"please enter phone number",Toast.LENGTH_SHORT).show();
            return false;
        } else if (NumberTF.getText().toString().length()!=10) {
            Toast.makeText(signUp.this,"please enter a valid phone number",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
        return true;
    }

    public void initialize(){
        SelectedDate="";
        Url="";
        SignUPImageView=findViewById(R.id.SignUPImageView);
        SignUpImageBTN=findViewById(R.id.SignUpImageBTN);
        signUpBTN=findViewById(R.id.signUpBTN);
        userTF=findViewById(R.id.userTF);
        PasswrodTF=findViewById(R.id.PasswrodTF);
        signUPPlateNumber=findViewById(R.id.signUPPlateNumber);
        NumberTF=findViewById(R.id.NumberTF);
        SignUPManufactureSpinner=findViewById(R.id.SignUPManufactureSpinner);
        SignUPIDNumber=findViewById(R.id.SignUPIDNumber);
        SignUpImageBTN.setOnClickListener(e->{
            PickImage();
        });
        String[] manufactureItems = getResources().getStringArray(R.array.carManufactures);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, manufactureItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SignUPManufactureSpinner.setAdapter(adapter);
        signUpBTN.setOnClickListener(e->{
            if(dataCheck()){
                clientSign(userTF.getText().toString(),PasswrodTF.getText().toString(),"location",Integer.parseInt(NumberTF.getText().toString())
                        ,userTF.getText().toString(),SignUPIDNumber.getText().toString(),signUPPlateNumber.getText().toString(),Url,SignUPManufactureSpinner.getSelectedItem().toString());

            }
        });

    }

    private DatePickerDialog createDialogWithoutDateField() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(signUp.this, (view1, selectedYear, selectedMonth, selectedDay) -> {
            // Handle the selected date here
            SelectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;}, year, month, day
        );

        datePickerDialog.show();
        return datePickerDialog;
    }

    private void PickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void clientSign(String UserName, String Password, String location, int Phone,String FullName,String IdNum,String Id,String Image,String Model) {
        String url = "http://10.0.2.2/Php/clientSignUp.php";

        RequestQueue queue = Volley.newRequestQueue(signUp.this);
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("UserName",UserName);
            jsonParams.put("Password", Password);
            jsonParams.put("location", location);
            jsonParams.put("Phone", Phone);
            jsonParams.put("FullName", FullName);
            jsonParams.put("IdNum", IdNum);
            jsonParams.put("Id", Id);
            jsonParams.put("Image", Image);
            jsonParams.put("Model", Model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String str = "";
                        try {
                            str = response.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(signUp.this, str, Toast.LENGTH_SHORT).show();

                    }
                },
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                Uri imageUri = data.getData();
                SignUPImageView.setImageURI(imageUri);
                Url=imageUri.toString();
            }
        }
    }
}