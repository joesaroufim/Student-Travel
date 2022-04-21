package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView col1, col2,col3;
    TableLayout table;
    TableRow row;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        table = (TableLayout) findViewById(R.id.table);

        table.setColumnStretchable(0,true);
        table.setColumnStretchable(1,true);
        table.setColumnStretchable(2,true);

        row = new TableRow(this);
        col1 = new TextView(this);
        col2= new TextView(this);
        col3 = new TextView(this);
        col1.setText("Joe Saroufim");
        col1.setTextSize(20);
        col1.setGravity(Gravity.CENTER);
        col1.setTypeface(null, Typeface.BOLD);
        col2.setText("MIT");
        col2.setTextSize(20);
        col2.setGravity(Gravity.CENTER);
        col2.setTypeface(null, Typeface.BOLD);
        col3.setText("hello");
        col3.setTextSize(20);
        col3.setGravity(Gravity.CENTER);
        col3.setTypeface(null, Typeface.BOLD);
        row.addView(col1);
        row.addView(col2);
        row.addView(col3);
        table.addView(row);

        row = new TableRow(this);
        col1 = new TextView(this);
        col2= new TextView(this);
        col3 = new TextView(this);
        col1.setText("Joe Saroufim");
        col1.setTextSize(20);
        col1.setGravity(Gravity.CENTER);
        col1.setTypeface(null, Typeface.BOLD);
        col2.setText("MIT");
        col2.setTextSize(20);
        col2.setGravity(Gravity.CENTER);
        col2.setTypeface(null, Typeface.BOLD);
        col3.setText("hello");
        col3.setTextSize(20);
        col3.setGravity(Gravity.CENTER);
        col3.setTypeface(null, Typeface.BOLD);
        row.addView(col1);
        row.addView(col2);
        row.addView(col3);
        table.addView(row);

        row = new TableRow(this);
        col1 = new TextView(this);
        col2= new TextView(this);
        col3 = new TextView(this);
        col1.setText("Joe Saroufim");
        col1.setTextSize(20);
        col1.setGravity(Gravity.CENTER);
        col1.setTypeface(null, Typeface.BOLD);
        col2.setText("MIT");
        col2.setTextSize(20);
        col2.setGravity(Gravity.CENTER);
        col2.setTypeface(null, Typeface.BOLD);
        col3.setText("hello");
        col3.setTextSize(20);
        col3.setGravity(Gravity.CENTER);
        col3.setTypeface(null, Typeface.BOLD);
        row.addView(col1);
        row.addView(col2);
        row.addView(col3);
        table.addView(row);

        row = new TableRow(this);
        col1 = new TextView(this);
        col2= new TextView(this);
        col3 = new TextView(this);
        col1.setText("Joe Saroufim");
        col1.setTextSize(20);
        col1.setGravity(Gravity.CENTER);
        col1.setTypeface(null, Typeface.BOLD);
        col2.setText("MIT");
        col2.setTextSize(20);
        col2.setGravity(Gravity.CENTER);
        col2.setTypeface(null, Typeface.BOLD);
        col3.setText("hello");
        col3.setTextSize(20);
        col3.setGravity(Gravity.CENTER);
        col3.setTypeface(null, Typeface.BOLD);
        row.addView(col1);
        row.addView(col2);
        row.addView(col3);
        table.addView(row);

        row = new TableRow(this);
        col1 = new TextView(this);
        col2= new TextView(this);
        col3 = new TextView(this);
        col1.setText("Joe Saroufim");
        col1.setTextSize(20);
        col1.setGravity(Gravity.CENTER);
        col1.setTypeface(null, Typeface.BOLD);
        col2.setText("MIT");
        col2.setTextSize(20);
        col2.setGravity(Gravity.CENTER);
        col2.setTypeface(null, Typeface.BOLD);
        col3.setText("hello");
        col3.setTextSize(20);
        col3.setGravity(Gravity.CENTER);
        col3.setTypeface(null, Typeface.BOLD);
        row.addView(col1);
        row.addView(col2);
        row.addView(col3);
        table.addView(row);

        row = new TableRow(this);
        col1 = new TextView(this);
        col2= new TextView(this);
        col3 = new TextView(this);
        col1.setText("Joe Saroufim");
        col1.setTextSize(20);
        col1.setGravity(Gravity.CENTER);
        col1.setTypeface(null, Typeface.BOLD);
        col2.setText("MIT");
        col2.setTextSize(20);
        col2.setGravity(Gravity.CENTER);
        col2.setTypeface(null, Typeface.BOLD);
        col3.setText("hello");
        col3.setTextSize(20);
        col3.setGravity(Gravity.CENTER);
        col3.setTypeface(null, Typeface.BOLD);
        row.addView(col1);
        row.addView(col2);
        row.addView(col3);
        table.addView(row);

        row = new TableRow(this);
        col1 = new TextView(this);
        col2= new TextView(this);
        col3 = new TextView(this);
        col1.setText("Joe Saroufim");
        col1.setTextSize(20);
        col1.setGravity(Gravity.CENTER);
        col1.setTypeface(null, Typeface.BOLD);
        col2.setText("MIT");
        col2.setTextSize(20);
        col2.setGravity(Gravity.CENTER);
        col2.setTypeface(null, Typeface.BOLD);
        col3.setText("hello");
        col3.setTextSize(20);
        col3.setGravity(Gravity.CENTER);
        col3.setTypeface(null, Typeface.BOLD);
        row.addView(col1);
        row.addView(col2);
        row.addView(col3);
        table.addView(row);

    }

    public void profile (View view){
        Intent profile = new Intent(getApplicationContext(), Profile.class);
        startActivity(profile);
    }




}