package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

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

    // Declaring Variables
    EditText arriving_date, location, uni, field;
    TextView can_help, need_help, name, username_1;
    Spinner spinner;
    String gender, date, college, major, status, country, post_url, name_url;
    String message;
    SharedPreferences shared;
    String[] full_name, username;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Creating SharedPreferences Object to retrieve stored data
        shared = this.getSharedPreferences("com.lau.student_travel", Context.MODE_PRIVATE);
        id = shared.getInt("id", -1);

        // Initializing EditText Views
        arriving_date = (EditText) findViewById(R.id.date);
        location = (EditText) findViewById(R.id.country);
        uni = (EditText) findViewById(R.id.uni);
        field = (EditText) findViewById(R.id.major);

        // Initializing TextViews
        can_help = (TextView) findViewById(R.id.favorite);
        need_help = (TextView) findViewById(R.id.need_help);
        username_1 = (TextView) findViewById(R.id.username1);
        name = (TextView) findViewById(R.id.full_name);

        // Initializing the Spinner
        spinner = (Spinner) findViewById(R.id.spinner);

        //Creating arrayList for the Spinner
        ArrayList<String> gender_list = new ArrayList<String>();
        gender_list.add("Male");
        gender_list.add("Female");

        //Assigning an adapter and the list as dropdown
        ArrayAdapter<String> my_adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, gender_list);
        spinner.setAdapter(my_adapter);

        // Storing the API url
        post_url = "http://192.168.43.127/Mobile%20Computing/Final%20Project/Backend/profile.php";

        name_url = "http://192.168.43.127/Mobile%20Computing/Final%20Project/Backend/name.php?id="+id;
        GetName get_name = new GetName();
        get_name.execute(name_url);

    }

    public void canHelp(View view){
        // Setting the status of the user as "can help"
        status = "can help";
        view.setBackgroundColor(Color.parseColor("#138B9A"));
        need_help.setBackgroundColor(Color.parseColor("#F2F6F6"));
    }

    public void needHelp(View view){
        // Setting the status of the user as "need help"
        status = "need help";
        view.setBackgroundColor(Color.parseColor("#138B9A"));
        can_help.setBackgroundColor(Color.parseColor("#F2F6F6"));
    }

    public void update(View view){
        // Storing the entered values in the required variables and sending them to the API to update the database
        gender = spinner.getSelectedItem().toString();
        date = arriving_date.getText().toString();
        college = uni.getText().toString();
        major = field.getText().toString();
        country = location.getText().toString();

        if (status.isEmpty()){

        }else{
            PostRequest post = new PostRequest();
            post.execute(gender, date, country, college, major, status, ""+id, post_url);
            Toast.makeText(getApplicationContext(), "Profie updated", Toast.LENGTH_LONG).show();
        }
    }

    public void home(View view){
        // Moving to the Home page
        Intent home = new Intent(getApplicationContext(), Home.class);
        startActivity(home);
    }

    public void filter(View view){
        // Moving to the Filter page
        Intent filter = new Intent (getApplicationContext(), Filter.class);
        startActivity(filter);
    }

    public void signout (View view){
        // Moving to the login page
        Intent signout = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(signout);
    }

    public void list(View view){
        // Move to the Filter page
        Intent list = new Intent(getApplicationContext(), ListAll.class);
        startActivity(list);
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
                // Reading values fromm the database and storing them into a String
                while((line = bufferedReader.readLine()) != null){
                    message += line;
                }

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


    public class GetName extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method takes String parameter and gets a required data from an external URL API.
            URL url;
            HttpURLConnection http; //Initializing the url connection object
            String results = "";
            try{
                url = new URL(params[0]);
                http = (HttpURLConnection) url.openConnection(); //Declaring the Url connection object

                InputStream inputStream = http.getInputStream(); //initializing InputStream Object to pass data.

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); //Initializing BufferedReader Object to Read data.
                String line = reader.readLine(); //Get the data ad store it in a String.

                while( line  != null){
                    results += line;
                    line = reader.readLine(); //Concatenate each line
                }

            }catch(Exception e){
                Log.i("exeDOin",e.getMessage());
                return null;
            }
            Log.i("entered:", results);
            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{

                Log.i("String", "im here");
                JSONArray json_array  = new JSONArray(s); //Creating a JSON object.

                ArrayList<Object> list = new ArrayList<>();
                JSONObject obj;

                // If the array is empty or not
                if(json_array != null){
                    for(int i = 0; i < json_array.length(); i++){
                        list.add(json_array.get(i));
                    }
                }

                // Initializing String arrays to store the column values
                full_name = new String[json_array.length()];
                username = new String[json_array.length()];

                // Reading each row and storing the value in the corresponding array
                for(int i = 0; i < list.size(); i++){
                    obj = (JSONObject) json_array.get(i);
                    full_name[i] = obj.getString("name");
                    username[i] = obj.getString("username");
                }

                username_1.setText(username[0]);
                name.setText(full_name[0]);

            }catch(Exception e){
                Log.i("exeOnPost",e.getMessage());
            }

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