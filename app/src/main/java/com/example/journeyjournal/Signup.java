package com.example.journeyjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Signup extends AppCompatActivity {
    Button signup_btn;
    TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_btn=findViewById(R.id.signup);
        txt_login=findViewById(R.id.login_txt);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_intent=new Intent(Signup.this,MainActivity.class);
                startActivity(signup_intent);

            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_txt_intent=new Intent(Signup.this,Login.class);
                startActivity(login_txt_intent);
            }
        });
    }
}