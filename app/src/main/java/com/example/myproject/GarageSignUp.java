package com.example.myproject;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class GarageSignUp extends AppCompatActivity {
    ImageView imgSignUp;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private EditText edtxtNameSign ;
    private EditText edittxtPassSign;
    private EditText edtxtNumberSign;
    private EditText edittxtLocSign;
    private Button btnSignUp ;
    private  Button btnChossing;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_garage_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SignUp), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgSignUp = findViewById(R.id.imgCarage);
        Button chooseImageButton = findViewById(R.id.btnChossing);
        chooseImageButton.setOnClickListener(v -> openGallery());

        edtxtNameSign = findViewById(R.id.edtxtNameSign);
        edittxtPassSign = findViewById(R.id.edittxtPassSign);
        edtxtNumberSign = findViewById(R.id.edtxtNumberSign);
        edittxtLocSign = findViewById(R.id.edittxtLocSign);
        btnSignUp = findViewById(R.id.btnSignUp);
//        btnChossing = findViewById(R.id.btnChossing);


        btnSignUp.setOnClickListener(e->{

            String UserName = edtxtNameSign.getText().toString();
            String Password = edittxtPassSign.getText().toString();
            String location = edittxtLocSign.getText().toString();
            String NameOfGarage=edtxtNameSign.getText().toString();
            int Phone  = Integer.parseInt(edtxtNumberSign.getText().toString());





            GarageSign(NameOfGarage,UserName, Password, location,Phone);




        });

    }

    private void GarageSign(String NameOfGarage,String UserName, String Password, String location, int Phone) {
        String url = "http://10.0.2.2/Php/SignUpGarage.php";

        RequestQueue queue = Volley.newRequestQueue(GarageSignUp.this);
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("NameOfGarage",NameOfGarage);
            jsonParams.put("UserName",UserName);
            jsonParams.put("Password", Password);
            jsonParams.put("location", location);
            jsonParams.put("Phone", Phone);
            jsonParams.put("Image", "sjk");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String str = "";
                        try {
                            str = response.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(GarageSignUp.this, str,
                                Toast.LENGTH_SHORT).show();

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




    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                Uri imageUri = data.getData();
                imgSignUp.setImageURI(imageUri);
            }
        }
    }

}

