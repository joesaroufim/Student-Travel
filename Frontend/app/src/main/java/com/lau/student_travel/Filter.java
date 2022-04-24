package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Filter extends AppCompatActivity {

    EditText uni, location, field;
    TextView need_help, can_help;
    String college, country, status, major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        uni = (EditText) findViewById(R.id.university_name);
        location = (EditText) findViewById(R.id.country_name);
        field = (EditText) findViewById(R.id.major_name);
        need_help = (TextView) findViewById(R.id.need_help1);
        can_help = (TextView) findViewById(R.id.can_help1);

    }

    public void canHelp(View view){
        status = "can help";
        view.setBackgroundColor(Color.parseColor("#138B9A"));
        need_help.setBackgroundColor(Color.parseColor("#F2F6F6"));
    }

    public void needHelp(View view){
        status = "need help";
        view.setBackgroundColor(Color.parseColor("#138B9A"));
        can_help.setBackgroundColor(Color.parseColor("#F2F6F6"));
    }

    public void search(View view){
        college = uni.getText().toString();
        country = location.getText().toString();
        major = field.getText().toString();

        if (status.isEmpty() || country.isEmpty() || major.isEmpty()){
            // write hereee
        }else{
            Intent list = new Intent(getApplicationContext(), List.class);
            list.putExtra("college", college);
            list.putExtra("country", country);
            list.putExtra("major", major);
            list.putExtra("status", status);
            startActivity(list);
        }
    }
}