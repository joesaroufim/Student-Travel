package com.lau.student_travel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class Signup extends AppCompatActivity {

    TextView req1,req2,req3,req4,req5,login;
    EditText user,number,name,pass,confirmed_pass;
    public String message;
    public String  post_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user = (EditText) findViewById(R.id.new_user);
        number = (EditText) findViewById(R.id.phone);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.new_password);
        confirmed_pass = (EditText) findViewById(R.id.confirmed_password);

        req1 = (TextView) findViewById(R.id.required1);
        req2 = (TextView) findViewById(R.id.required2);
        req3 = (TextView) findViewById(R.id.required3);
        req4 = (TextView) findViewById(R.id.required4);
        req5 = (TextView) findViewById(R.id.required5);

        req1.setVisibility(View.GONE);
        req2.setVisibility(View.GONE);
        req3.setVisibility(View.GONE);
        req4.setVisibility(View.GONE);
        req5.setVisibility(View.GONE);

        message = "";
        post_url = "http://192.168.56.1/Mobile%20Computing/Final%20Project/Backend/sign_up.php";

    }

    public void signup (View view){
        String username = user.getText().toString();
        String full_name = name.getText().toString();
        String phone_number = number.getText().toString();
        String password = pass.getText().toString();
        String confirmed_password = confirmed_pass.getText().toString();
        req5.setText("Field Required!");
        req1.setVisibility(View.GONE);
        req1.setText("Field required!");

        if (username.isEmpty()){
            req1.setVisibility(View.VISIBLE);
            return;
        }else if (full_name.isEmpty()){
            req2.setVisibility(View.VISIBLE);
            return;
        }else if (phone_number.isEmpty()){
            req3.setVisibility(View.VISIBLE);
            return;
        }else if (password.isEmpty()){
            req4.setVisibility(View.VISIBLE);
            return;
        }else if (confirmed_password.isEmpty()){
            req5.setVisibility(View.VISIBLE);
            return;
        }else{
            if (!password.equals(confirmed_password)){
                confirmed_pass.setText("");
                req5.setText("Incompatible values");
                req5.setVisibility(View.VISIBLE);
                return;
            }else{
                PostRequest post = new PostRequest();// Initialize a PostRequest object everytime the user clicks the button.
                post.execute(username, password, phone_number, full_name, post_url);


            }

        }
    }


    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String username = params[0];
            String password = params[1];
            String phone = params[2];
            String full_name = params[3];
            String str_url = params[4];

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
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"
                        +URLEncoder.encode("full_name", "UTF-8")+"="+URLEncoder.encode(full_name, "UTF-8")+"&"
                        +URLEncoder.encode("phone", "UTF-8")+"="+URLEncoder.encode(phone, "UTF-8");

                Log.i("data", post_data);
                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    message = line;
                }

                if(message.equals("correct")) {
                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(login);
                }else{
                    pass.setText("");
                    confirmed_pass.setText("");
                    req1.setVisibility(View.VISIBLE);
                    req1.setText("Account already exists!");
                    req2.setVisibility(View.GONE);
                    req3.setVisibility(View.GONE);
                    req4.setVisibility(View.GONE);
                    req5.setVisibility(View.GONE);
                }

                Log.i("message", message);
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