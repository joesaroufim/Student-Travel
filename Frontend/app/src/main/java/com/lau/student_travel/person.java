package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

public class person extends AppCompatActivity {

    // Declaring variables
    TextView arriving_date, location, sex, uni, field, can_help, user_name, favorite, full_name, number;
    CardView card1, card2, card3;
    String username, post_url, fav_url, message, phone_nb;
    public String[] name, college, phone, country, date, status, major, gender;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        // Creating SharedPreferences object to access stored data
        SharedPreferences shared = this.getSharedPreferences("com.lau.student_travel", Context.MODE_PRIVATE);
        id = shared.getInt("id", -1);

        // Getting the username from the previous page using Intent
        Intent message = getIntent();
        username = message.getStringExtra("username");

        // Initializing TextViews
        arriving_date= (TextView) findViewById(R.id.date);
        location = (TextView) findViewById(R.id.country);
        uni = (TextView) findViewById(R.id.uni);
        sex = (TextView) findViewById(R.id.gender);
        field = (TextView) findViewById(R.id.major);
        can_help = (TextView) findViewById(R.id.can_help2);
        full_name = (TextView) findViewById(R.id.full_name);
        user_name = (TextView) findViewById(R.id.username1);
        favorite = (TextView) findViewById(R.id.favorite);
        number = (TextView) findViewById(R.id.phone1);

        // Initializing CardViews
        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);

        // Storing the url of the first API
        post_url = "http://192.168.1.101/Mobile%20Computing/Final%20Project/Backend/person.php";

        // Creating a PostRequest Object to access the API
        PostRequest post = new PostRequest();
        post.execute(username,post_url); // calling the functions in the PostRequest class

        // Storing the url of the second API
        fav_url = "http://192.168.1.101/Mobile%20Computing/Final%20Project/Backend/add_favorites.php";
    }

    public void home(View view){
        // Moving to the Home page if the icon is clicked
        Intent home = new Intent (getApplicationContext(), Home.class);
        startActivity(home);
    }

    public void filter(View view){
        // Moving to the Filter page if the icon is clicked
        Intent filter = new Intent (getApplicationContext(), Filter.class);
        startActivity(filter);
    }

    public void getNumber (View view){
        // Showing the phone number of the person when the phone icon is clicked
        number.setText(phone_nb);

        // Changing the opacity of the other views
        card1.setAlpha(.2f);
        card2.setAlpha(0.2f);

        number.setVisibility(View.VISIBLE);
    }

    public void addFavorite(View view){
        // calling the API to add the person to te user's favorites
        view.setBackgroundColor(Color.parseColor("#138B9A")); // changing the background color of the button
        Favorite fav = new Favorite();
        fav.execute(""+id, username, fav_url);
    }


    public class Favorite extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String username = params[0];
            String str_url = params[1];

            message = "";

            try {
                Log.i("gender", username);
                // Creating a new URL connection with PHP.
                URL url = new URL(str_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object

                // Setting the variables to be sent to the URL
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(""+id, "UTF-8");


                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                // Creating an Input Stream connection
                InputStream is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String line = "";
                // reading data and storing them into a String
                while((line = bufferedReader.readLine()) != null){
                    message = line;
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


    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String username = params[0];
            String str_url = params[1];

            message = "";

            try {
                Log.i("username", username);
                // Creating a new URL connection with PHP.
                URL url = new URL(str_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object

                // Setting the variables to be sent to the URL
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8");

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String line = "";
                // Reading data and storing them in a String
                while((line = bufferedReader.readLine()) != null){
                    message += line;
                }

                //Catching exceptions
            } catch (MalformedURLException e) {
                Log.i("MalF",e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("Ioexp",e.getMessage());
                e.printStackTrace();
            }
            Log.i("Person message", message);

            return message;
        }

        @Override
        protected void onPostExecute(String s) {

            // This method converts the JSON Object received into a String.
            super.onPostExecute(s);
            try{

                Log.i("Person String", s);
                JSONArray json_array  = new JSONArray(s); //Creating a JSON object.

                ArrayList<Object> list = new ArrayList<>();
                JSONObject obj;

                // If the array is empty or not
                if(json_array != null){
                    for(int i = 0; i < json_array.length(); i++){
                        list.add(json_array.get(i));
                    }
                }
                name = new String[json_array.length()];
                phone = new String[json_array.length()];
                college = new String[json_array.length()];
                country = new String[json_array.length()];
                date = new String[json_array.length()];
                status = new String[json_array.length()];
                major = new String[json_array.length()];
                gender = new String[json_array.length()];

                for(int i = 0; i < list.size(); i++){
                    obj = (JSONObject) json_array.get(i);
                    name[i] = obj.getString("name");
                    phone[i] = obj.getString("phone_number");
                    college[i] = obj.getString("university");
                    country[i] = obj.getString("destination");
                    date[i] = obj.getString("arriving_date");
                    status[i] = obj.getString("status");
                    major[i] = obj.getString("major");
                    gender[i] = obj.getString("gender");
                }
                arriving_date.setText(date[0]);
                location.setText(country[0]);
                uni.setText(college[0]);
                sex.setText(gender[0]);
                field.setText(major[0]);
                can_help.setText(status[0]);
                full_name.setText(name[0]);
                user_name.setText(username);
                phone_nb = phone[0];

                Log.i("nameee", name[0]);

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