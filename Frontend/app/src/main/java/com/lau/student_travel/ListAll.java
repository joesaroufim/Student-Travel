package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class ListAll extends AppCompatActivity {

    // Declaring Variables
    TextView col1, col2,col3;
    TableLayout table;
    String get_url, send_username;
    String[] college, username, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        // Initializing TableLayout
        table = (TableLayout) findViewById(R.id.table);

        // Setting the Number of Columns
        table.setColumnStretchable(0,true);
        table.setColumnStretchable(1,true);
        table.setColumnStretchable(2,true);

        // Storing the API url
        get_url = "http://192.168.43.127/Mobile%20Computing/Final%20Project/Backend/list_all.php";

        // Creating a PostRequest Object to connect with the database
        GetRequest get = new GetRequest();
        get.execute(get_url);

    }

    public void home(View view){
        // Moving to the Home page
        Intent home = new Intent (getApplicationContext(), Home.class);
        startActivity(home);
    }

    public void filter(View view){
        // Moving to the Filter page
        Intent filter = new Intent (getApplicationContext(), Filter.class);
        startActivity(filter);
    }

    public void profile(View view){
        // Moving to the Profile page
        Intent profile = new Intent (getApplicationContext(), Profile.class);
        startActivity(profile);
    }


    public class GetRequest extends AsyncTask<String, Void, String> {
        // This class contains methods that enable url connection to an API to retrieve data stored in it.

        protected String doInBackground(String... urls){
            //The method takes String parameter and gets a required data from an external URL API.
            URL url;
            HttpURLConnection http; //Initializing the url connection object
            String results = "";
            try{
                url = new URL(urls[0]);
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

        protected void onPostExecute(String s) {
            // This method converts the JSON Object received into a String.
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
                college = new String[json_array.length()];
                username = new String[json_array.length()];
                status = new String[json_array.length()];

                // Reading each row and storing the value in the corresponding array
                for(int i = 0; i < list.size(); i++){
                    obj = (JSONObject) json_array.get(i);
                    college[i] = obj.getString("university");
                    username[i] = obj.getString("username");
                    status[i] = obj.getString("status");
                }

                // Filling up the rows in the table
                for (int i = 0; i<college.length; i++){
                    final TableRow new_row = new TableRow(getApplicationContext());
                    col1 = new TextView(getApplicationContext());
                    col2= new TextView(getApplicationContext());
                    col3 = new TextView(getApplicationContext());
                    col1.setText(username[i]);
                    col1.setTextSize(18);
                    col1.setGravity(Gravity.CENTER);
                    col1.setTypeface(null, Typeface.BOLD);
                    col2.setText(college[i]);
                    col2.setTextSize(18);
                    col2.setGravity(Gravity.CENTER);
                    col2.setTypeface(null, Typeface.BOLD);
                    col3.setText(status[i]);
                    col3.setTextSize(18);
                    col3.setGravity(Gravity.CENTER);
                    col3.setTypeface(null, Typeface.BOLD);
                    new_row.addView(col1);
                    new_row.addView(col2);
                    new_row.addView(col3);
                    new_row.setId(i); // setting an id for each row
                    new_row.setBackgroundColor(Color.parseColor("#8FCDD5"));
                    send_username = username[i]; // storing the username of each profile
                    new_row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            send_username = username[new_row.getId()]; // getting the username of the clicked person
                            Intent person = new Intent(getApplicationContext(), person.class);
                            person.putExtra("username", send_username); // sending the username to the Person page
                            startActivity(person);
                        }
                    });
                    table.addView(new_row);
                }
            }catch(Exception e){
                Log.i("exeOnPost",e.getMessage());
            }

        }
    }
}