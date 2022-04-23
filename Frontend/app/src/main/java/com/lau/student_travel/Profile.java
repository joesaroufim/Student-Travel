package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    EditText arriving_date, location, sex, uni, field;
    TextView can_help, need_help;
    String gender, date, college, major, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        arriving_date = (EditText) findViewById(R.id.date);
        location = (EditText) findViewById(R.id.country);
        sex = (EditText) findViewById(R.id.gender);
        uni = (EditText) findViewById(R.id.uni);
        field = (EditText) findViewById(R.id.major);

        can_help = (TextView) findViewById(R.id.can_help);
        need_help = (TextView) findViewById(R.id.need_help);

    }

    public void canHelp(View view){
        status = "can help";
    }

    public void needHelp(View view){
        status = "need help";
    }

    public void update(View view){
        gender = sex.getText().toString();
        date = arriving_date.getText().toString();
        college = uni.getText().toString();
        major = field.getText().toString();

        if (status.isEmpty()){

        }else{

        }
    }

    public void home(View view){
        Intent home = new Intent(getApplicationContext(), Home.class);
        startActivity(home);
    }
}