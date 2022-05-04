package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Filter extends AppCompatActivity {

    // Declaring variables
    EditText uni, location, field;
    TextView need_help, can_help, error_msg;
    String college, country, status, major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Initializing EditText views
        uni = (EditText) findViewById(R.id.university_name);
        location = (EditText) findViewById(R.id.country_name);
        field = (EditText) findViewById(R.id.major_name);

        // Initializing TextViews
        need_help = (TextView) findViewById(R.id.need_help1);
        can_help = (TextView) findViewById(R.id.can_help1);
        error_msg = (TextView) findViewById(R.id.error);

    }

    public void canHelp(View view){
        // Choosing "can help" as status to search for
        status = "can help";
        view.setBackgroundColor(Color.parseColor("#138B9A"));
        need_help.setBackgroundColor(Color.parseColor("#F2F6F6"));
    }

    public void needHelp(View view){
        // Choosing "need help" as status to search for
        status = "need help";
        view.setBackgroundColor(Color.parseColor("#138B9A"));
        can_help.setBackgroundColor(Color.parseColor("#F2F6F6"));
    }

    public void search(View view){
        // Storing the entered values in String objects
        college = uni.getText().toString();
        country = location.getText().toString();
        major = field.getText().toString();

        // Checking if every field is filled
        if (status.isEmpty() || country.isEmpty() || major.isEmpty()){
            error_msg.setVisibility(View.VISIBLE);
        }else{ // Moving to the List page to list the available users by sending them their characteristics using Intent
            Intent list = new Intent(getApplicationContext(), List.class);
            list.putExtra("college", college);
            list.putExtra("country", country);
            list.putExtra("major", major);
            list.putExtra("status", status);
            startActivity(list);
        }
    }
}