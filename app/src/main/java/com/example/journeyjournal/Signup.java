package com.example.journeyjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    Button signup_btn;
    TextView txt_login,tvstatus;
    private EditText etName,etEmail,etPassword,etConfirmPassword;
    private String name,email,password,confirmpassword;
    private String URL="http://localhost/127.0.0.1:80/login/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_btn=findViewById(R.id.signup);
        txt_login=findViewById(R.id.login_txt);

        etName=findViewById(R.id.name);
        etEmail=findViewById(R.id.email);
        etPassword=findViewById(R.id.password);
        etConfirmPassword=findViewById(R.id.confirmpassword);
        tvstatus=findViewById(R.id.status);



        name=email=password=confirmpassword="";
        }

    public void signup(View view) {

        name=etName.getText().toString().trim();
        email=etEmail.getText().toString().trim();
        password=etPassword.getText().toString().trim();
        confirmpassword=etConfirmPassword.toString().trim();
        if (password.equals(confirmpassword)){
            Toast.makeText(Signup.this, "Password Missmatch", Toast.LENGTH_SHORT).show();
        }else if(!name.equals("") && !email.equals("")  && !password.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Success")) {
                        tvstatus.setText("Successful login");
                        signup_btn.setClickable(false);

                    } else if (response.equals("Failure")) {
                    tvstatus.setText("Somthing Want wroung");

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Signup.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> data = new HashMap<>();
                    data.put("name",name);
                    data.put("email",email);
                    data.put("password",password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
    }

    public void txt_login(View view) {
        Intent intent =new Intent(Signup.this, Login.class);
        startActivity(intent);
        finish();
    }
}