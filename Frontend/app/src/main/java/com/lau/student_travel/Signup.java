package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    TextView req1,req2,req3,req4,req5,login;
    EditText user,number,name,pass,confirmed_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        req1 = (TextView) findViewById(R.id.required1);
        req2 = (TextView) findViewById(R.id.required2);
        req3 = (TextView) findViewById(R.id.required3);
        req4 = (TextView) findViewById(R.id.required4);
        req5 = (TextView) findViewById(R.id.required5);

        
    }


}