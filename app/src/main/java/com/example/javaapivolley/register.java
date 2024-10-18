package com.example.javaapivolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class register extends AppCompatActivity {

    EditText et_name, et_email, et_pass;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);

        button2 = findViewById(R.id.button2);

    }

    public void registerUser(View view) {

        String name=et_name.getText().toString();
        String email=et_email.getText().toString();
        String pass= et_pass.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://192.168.215.5/register",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(register.this, "register success"+response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(register.this, Profile.class));
                        finish();
                    }
                },

        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(register.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                }
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hm =new HashMap<>();
                hm.put("KEY_name", name);
                hm.put("KEY_email",email);
                hm.put("KEY_pass", pass);

                return hm;
            }
        };
        requestQueue.add(stringRequest);
     }
}