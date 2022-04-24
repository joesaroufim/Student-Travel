package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class person extends AppCompatActivity {

    TextView arriving_date, location, sex, uni, field, can_help, user_name;
    String username, get_url;
    public String[] name, college, phone, country, date, status, major, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        Intent message = getIntent();
        username = message.getStringExtra("username");

        arriving_date= (TextView) findViewById(R.id.date);
        location = (TextView) findViewById(R.id.country);
        uni = (TextView) findViewById(R.id.uni);
        sex = (TextView) findViewById(R.id.gender);
        field = (TextView) findViewById(R.id.major);
        can_help = (TextView) findViewById(R.id.can_help);
        user_name = (TextView) findViewById(R.id.user_name);

        get_url = "http://192.168.56.1/Mobile%20Computing/Final%20Project/Backend/person.php?username="+username;
        GetRequest get = new GetRequest();
        get.execute(get_url);
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
                user_name.setText(name[0]);

                Log.i("nameee", name[0]);

            }catch(Exception e){
                Log.i("exeOnPost",e.getMessage());
            }

        }
    }
}