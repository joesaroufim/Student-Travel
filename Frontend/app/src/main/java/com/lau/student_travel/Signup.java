package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    TextView req1,req2,req3,req4,req5,login;
    EditText user,number,name,pass,confirmed_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user = (EditText) findViewById(R.id.new_user);
        number = (EditText) findViewById(R.id.phone);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.new_password);
        confirmed_pass = (EditText) findViewById(R.id.confirmed_password);

        req1 = (TextView) findViewById(R.id.required1);
        req2 = (TextView) findViewById(R.id.required2);
        req3 = (TextView) findViewById(R.id.required3);
        req4 = (TextView) findViewById(R.id.required4);
        req5 = (TextView) findViewById(R.id.required5);

        req1.setVisibility(View.GONE);
        req2.setVisibility(View.GONE);
        req3.setVisibility(View.GONE);
        req4.setVisibility(View.GONE);
        req5.setVisibility(View.GONE);
    }

    public void signup (View view){
        String username = user.getText().toString();
        String full_name = name.getText().toString();
        String phone_number = number.getText().toString();
        String password = pass.getText().toString();
        String confirmed_password = confirmed_pass.getText().toString();

        req5.setText("Field Required!");

        if (username.isEmpty()){
            req1.setVisibility(View.VISIBLE);
            return;
        }else if (full_name.isEmpty()){
            req2.setVisibility(View.VISIBLE);
        }else if (phone_number.isEmpty()){
            req3.setVisibility(View.VISIBLE);
        }else if (password.isEmpty()){
            req4.setVisibility(View.VISIBLE);
        }else if (confirmed_password.isEmpty()){
            req5.setVisibility(View.VISIBLE);
        }else{
            if (!password.equals(confirmed_password)){
                confirmed_pass.setText("");
                req5.setText("Incompatible value");
                req5.setVisibility(View.VISIBLE);
            }else{
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(login);
            }

        }
    }

}