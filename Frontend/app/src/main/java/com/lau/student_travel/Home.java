package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    TextView col1, col2,col3;
    TableLayout table;
    TableRow row;
    public String[] full_name, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        table = (TableLayout) findViewById(R.id.table);
        String get_url = "http://192.168.56.1/Mobile%20Computing/Final%20Project/Backend/list_favorites.php";

        GetRequest get = new GetRequest();
        get.execute(get_url);

        table.setColumnStretchable(0,true);
        table.setColumnStretchable(1,true);
        table.setColumnStretchable(2,true);

        for (int i = 0; i<full_name.length; i++){
            row = new TableRow(this);
            col1 = new TextView(this);
            col2= new TextView(this);
            col3 = new TextView(this);
            col1.setText(full_name[i]);
            col1.setTextSize(20);
            col1.setGravity(Gravity.CENTER);
            col1.setTypeface(null, Typeface.BOLD);
            col2.setText(username[i]);
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

    }

    public void profile (View view){
        Intent profile = new Intent(getApplicationContext(), Profile.class);
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
            Log.i("entered:", "hellooo");
            return results;
        }

        protected void onPostExecute(String s) {
            // This method converts the JSON Object received into a String.
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
                full_name = new String[json_array.length()];
                username = new String[json_array.length()];

                for(int i = 0; i < list.size(); i++){
                    obj = (JSONObject) json_array.get(i);
                    full_name[i] = obj.getString("name");
                    username[i] = obj.getString("username");
                }
                Log.i("name", full_name[0]);

            }catch(Exception e){
                Log.i("exeOnPost",e.getMessage());
            }
        }
    }

}