package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    }


}