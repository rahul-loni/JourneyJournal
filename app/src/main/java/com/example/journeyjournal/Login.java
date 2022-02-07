package com.example.journeyjournal;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    TextView signup_txt;
    Button login_btn;

    public EditText etEmail , etPassword;
    public  String email , password;
    public String URL="http://localhost/login/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=password="";
        etEmail=findViewById(R.id.edit_email);
        etPassword=findViewById(R.id.edit_password);

        signup_txt=findViewById(R.id.signup_txt);
        login_btn=findViewById(R.id.login);
        TextView txt_forgot=findViewById(R.id.forgot);


    }

    public void login(View view) {
        email=etEmail.getText().toString().trim();
        password=etPassword.getText().toString().trim();

        if (!email.equals("") && !password.equals("")){
          StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  if (response.equals("Success")) {
                      Intent intent = new Intent(Login.this, MainActivity.class);
                      startActivity(intent);
                      finish();
                  } else if (response.equals("Failure")) {
                      Toast.makeText(Login.this, "Invalid Login ID/Password", Toast.LENGTH_SHORT).show();

                  }
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
              }
          }
          ){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String , String> data = new HashMap<>();
                  data.put("email",email);
                  data.put("password",password);
                  return data;
              }
          };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
        else {
            Toast.makeText(Login.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();

        }
    }

    public void signup(View view) {
        Intent intent = new Intent(this,Signup.class);
        startActivity(intent);
    }
}