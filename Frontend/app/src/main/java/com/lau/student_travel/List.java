package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

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

public class List extends AppCompatActivity {

    String college, country, major, status, post_url;
    String message;
    String[] name, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent message = getIntent();
        college= message.getStringExtra("college");
        country= message.getStringExtra("country");
        major= message.getStringExtra("major");
        status= message.getStringExtra("status");

        post_url = "http://192.168.56.1/Mobile%20Computing/Final%20Project/Backend/list.php";
        PostRequest post = new PostRequest();
        post.execute(college, country, major, status, post_url);

    }


    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String college = params[0];
            String country = params[1];
            String major = params[2];
            String status = params[3];
            String str_url = params[4];

            message = "";

            try {
                Log.i("gender", major);
                // Creating a new URL connection with PHP.
                URL url = new URL(str_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object

                // Setting the variables to be sent to the URL
                String post_data = URLEncoder.encode("country", "UTF-8")+"="+URLEncoder.encode(country, "UTF-8")+"&"
                        +URLEncoder.encode("college", "UTF-8")+"="+URLEncoder.encode(college, "UTF-8")+"&"
                        +URLEncoder.encode("major", "UTF-8")+"="+URLEncoder.encode(major, "UTF-8")+"&"
                        +URLEncoder.encode("status", "UTF-8")+"="+URLEncoder.encode(status, "UTF-8");


                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    message += line;
                    line = bufferedReader.readLine();
                }

                //Catching exceptions
            } catch (MalformedURLException e) {
                Log.i("MalF",e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("Ioexp",e.getMessage());
                e.printStackTrace();
            }
            return message;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            try{

                Log.i("String", s);
                JSONArray json_array  = new JSONArray(s); //Creating a JSON object.

                ArrayList<Object> list = new ArrayList<>();
                JSONObject obj;

                // If the array is empty or not
                if(json_array != null){
                    for(int i = 0; i < json_array.length(); i++){
                        list.add(json_array.get(i));
                    }
                }
                username = new String[json_array.length()];

                for(int i = 0; i < list.size(); i++){
                    obj = (JSONObject) json_array.get(i);
                    username[i] = obj.getString("username");
                }
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