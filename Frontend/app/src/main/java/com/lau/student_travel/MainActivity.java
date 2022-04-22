package com.lau.student_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    EditText user,pass;
    TextView signup, user_error, pass_error, cred_error;
    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        signup = (TextView) findViewById(R.id.signup);
        user_error = (TextView) findViewById(R.id.username_error);
        pass_error = (TextView) findViewById(R.id.password_error);
        cred_error = (TextView) findViewById(R.id.credentials_error);

        user_error.setVisibility(View.GONE);
        pass_error.setVisibility(View.GONE);
        cred_error.setVisibility(View.GONE);


    }

    public void login(View view){
        String username = user.getText().toString();
        String password = pass.getText().toString();

        if(username.isEmpty()){
            user_error.setVisibility(View.VISIBLE);
            return;
        }else if(password.isEmpty()){
            pass_error.setVisibility(View.VISIBLE);
            return;
        }else{
            Intent home = new Intent(getApplicationContext(), Home.class);
            startActivity(home);
        }
    }

    public void signup (View view){
        Intent signup = new Intent (getApplicationContext(), Signup.class);
        startActivity(signup);
    }


//    public class PostRequest extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            //The method take String parameters and send data to the received url.
//
//            //Storing data in String objects
//            String username = params[0];
//            String password = params[1];
//            String str_url = params[2];
//
//            try {
//                // Creating a new URL connection with PHP.
//                URL url = new URL(str_url);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setDoInput(true);
//                urlConnection.setDoOutput(true);
//
//                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.
//
//                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object
//
//                // Setting the variables to be sent to the URL
//                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
//                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
//
//                br.write(post_data); //Writing and sending data.
//                br.flush();
//                br.close();
//                out.close();
//
//                InputStream is = urlConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
//                message = "";
//                String line = "";
//                while((line = bufferedReader.readLine()) != null){
//                    message += line;
//                 }
//                bufferedReader.close();
//                is.close();
//                urlConnection.disconnect();
//
//                //Catching exceptions
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//    }

}