package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText user,pass;
    TextView signup, user_error, pass_error, cred_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        signup = (TextView) findViewById(R.id.signup);
        user_error = (TextView) findViewById(R.id.username_error);
        pass_error = (TextView) findViewById(R.id.password_error);
        cred_error = (TextView) findViewById(R.id.credentials_error);

        user_error.setVisibility(View.GONE);
        pass_error.setVisibility(View.GONE);
        cred_error.setVisibility(View.GONE);


    }

    public void login(View view){
        String username = user.getText().toString();
        String password = pass.getText().toString();

        if(username.isEmpty()){
            user_error.setVisibility(View.VISIBLE);
            return;
        }else if(password.isEmpty()){
            pass_error.setVisibility(View.VISIBLE);
            return;
        }else{
            Intent home = new Intent(getApplicationContext(), Home.class);
            startActivity(home);
        }
    }

    public void signup (View view){
        Intent signup = new Intent (getApplicationContext(), Signup.class);
        startActivity(signup);
    }
}