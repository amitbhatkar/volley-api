package com.example.javaapivolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    EditText etEmail, etPass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=findViewById(R.id.etEmail);
        etPass=findViewById(R.id.etPass);
    }

    public void btLogin(View view) {
        String UserEmail= etEmail.getText().toString();
        String UserPass=  etPass.getText().toString();


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://192.168.215.5/login",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals("success"))
                        {
                            Toast.makeText(login.this, " "+response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login.this, Profile.class));
                            finish();
                        }
                        else {
                            Toast.makeText(login.this, "email and password is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(login.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hm =new HashMap<>();

                hm.put("key_email",UserEmail);
                hm.put("key_pass", UserPass);

                return hm;
            }
        };
        requestQueue.add(stringRequest);
    }

}