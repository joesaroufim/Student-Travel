package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    //Initializing variables
    EditText user,pass;
    TextView signup, user_error, pass_error, cred_error;
    public String message, post_url;

    //Creating sharedPrefernce to store local values
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shared = this.getSharedPreferences("com.lau.student_travel", Context.MODE_PRIVATE);

        //Getting the EditText Views by their Ids
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        signup = (TextView) findViewById(R.id.signup);
        user_error = (TextView) findViewById(R.id.username_error);
        pass_error = (TextView) findViewById(R.id.password_error);
        cred_error = (TextView) findViewById(R.id.credentials_error);

        //Setting the error messages to Invisible
        user_error.setVisibility(View.GONE);
        pass_error.setVisibility(View.GONE);
        cred_error.setVisibility(View.GONE);

        message = "";
        //API url
        post_url = "http://192.168.1.101/Mobile%20Computing/Final%20Project/Backend/login.php";


    }

    public void login(View view){
        // This method the username and password from the user, calls the API to check for the credentials and
        // acts accordingly

        //Storing the values of the EditTexts in String variables
        String username = user.getText().toString();
        String password = pass.getText().toString();

        if(username.isEmpty()){ //checking whether a field is empty
            user_error.setVisibility(View.VISIBLE);
            return;
        }else if(password.isEmpty()){
            pass_error.setVisibility(View.VISIBLE);
            return;
        }else{ //Call the PostRequest class to connect to the database
            PostRequest post = new PostRequest();
            post.execute(username, password, post_url);
        }
    }

    public void signup (View view){
        Intent signup = new Intent (getApplicationContext(), Signup.class);
        startActivity(signup);
    }

    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String username = params[0];
            String password = params[1];
            String str_url = params[2];

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
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String line = "";
                while((line = bufferedReader.readLine()) != null){ //Reading the returned result from the api
                    message = line;
                 }

                String[] msg = message.split(" "); //Splitting the resulted string to obtain the id and boolean answer
                String state = msg[0];
                Log.i("msg", state);

                if (state.equals("true")){
                    // Store the user id in the shared preference to be used in other pages
                    shared.edit().putInt("id", Integer.parseInt(msg[1])).commit();
                    Intent home = new Intent(getApplicationContext(), Home.class); //Move to the Home page
                    startActivity(home);
                }else{
                    pass.setText("");
//                    cred_error.setVisibility(View.VISIBLE);
                }

                bufferedReader.close();
                is.close();
                urlConnection.disconnect();

                //Catching exceptions
            } catch (MalformedURLException e) {
                Log.i("MalF",e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("IoExp",e.getMessage());
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