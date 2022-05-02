package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    EditText arriving_date, location, uni, field;
    TextView can_help, need_help;
    Spinner spinner;
    String gender, date, college, major, status, country, post_url;
    String message;
    SharedPreferences shared;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        shared = this.getSharedPreferences("com.lau.student_travel", Context.MODE_PRIVATE);
        id = shared.getInt("id", -1);
        String flag = shared.getString("flag", "");

        arriving_date = (EditText) findViewById(R.id.date);
        location = (EditText) findViewById(R.id.country);
        uni = (EditText) findViewById(R.id.uni);
        field = (EditText) findViewById(R.id.major);

        can_help = (TextView) findViewById(R.id.favorite);
        need_help = (TextView) findViewById(R.id.need_help);

        spinner = (Spinner) findViewById(R.id.spinner);

        if(flag.equals("true")){
            arriving_date.setText(shared.getString("date", ""));
            location.setText(shared.getString("country", ""));
            uni.setText(shared.getString("college", ""));
            field.setText(shared.getString("major", ""));
            spinner.setSelection(0);
        }

        //Creating arrayList for the Spinner
        ArrayList<String> gender_list = new ArrayList<String>();
        gender_list.add("Male");
        gender_list.add("Female");

        //Assigning an adapter and the list as dropdown
        ArrayAdapter<String> my_adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, gender_list);
        spinner.setAdapter(my_adapter);

        post_url = "http://192.168.1.101/Mobile%20Computing/Final%20Project/Backend/profile.php";

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

    public void update(View view){
        gender = spinner.getSelectedItem().toString();
        date = arriving_date.getText().toString();
        college = uni.getText().toString();
        major = field.getText().toString();
        country = location.getText().toString();

        shared.edit().putString("gender", gender).commit();
        shared.edit().putString("date", gender).commit();
        shared.edit().putString("college", gender).commit();
        shared.edit().putString("major", gender).commit();
        shared.edit().putString("country", gender).commit();
        shared.edit().putString("status", gender).commit();
        shared.edit().putString("flag", "true").commit();

        if (status.isEmpty()){

        }else{
            PostRequest post = new PostRequest();
            post.execute(gender, date, country, college, major, status, ""+id, post_url);
            Toast.makeText(getApplicationContext(), "Profie updated", Toast.LENGTH_LONG).show();
        }
    }

    public void home(View view){
        Intent home = new Intent(getApplicationContext(), Home.class);
        startActivity(home);
    }

    public void filter(View view){
        Intent filter = new Intent (getApplicationContext(), Filter.class);
        startActivity(filter);
    }

    public void signout (View view){
        Intent signout = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(signout);
    }


    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String gender = params[0];
            String date = params[1];
            String country = params[2];
            String college = params[3];
            String major = params[4];
            String status = params[5];
            String id = params[6];
            String str_url = params[7];
            message = "";

            try {
                Log.i("gender", gender);
                // Creating a new URL connection with PHP.
                URL url = new URL(str_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object

                // Setting the variables to be sent to the URL
                String post_data = URLEncoder.encode("gender", "UTF-8")+"="+URLEncoder.encode(gender, "UTF-8")+"&"
                        +URLEncoder.encode("date", "UTF-8")+"="+URLEncoder.encode(date, "UTF-8")+"&"
                        +URLEncoder.encode("college", "UTF-8")+"="+URLEncoder.encode(college, "UTF-8")+"&"
                        +URLEncoder.encode("major", "UTF-8")+"="+URLEncoder.encode(major, "UTF-8")+"&"
                        +URLEncoder.encode("status", "UTF-8")+"="+URLEncoder.encode(status, "UTF-8")+"&"
                        +URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"
                        +URLEncoder.encode("country", "UTF-8")+"="+URLEncoder.encode(country, "UTF-8");

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    message += line;
                }

                Log.i("update", message);

                bufferedReader.close();
                is.close();
                urlConnection.disconnect();

                //Catching exceptions
            } catch (MalformedURLException e) {
                Log.i("MalF",e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("Ioexp",e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


}