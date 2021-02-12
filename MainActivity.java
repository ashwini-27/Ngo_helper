package com.example.android.new_dot;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.icu.text.DisplayContext.LENGTH_SHORT;




public class MainActivity extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String MOB_NO="^[7-9][0-9]{9}$";
    private static  final String website_pattern="^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
    private EditText mob_no1;
    private EditText email1;
    private EditText reg_id1;
    private EditText vision1;
    private EditText website1;
    private EditText bio1;
    private EditText name1;
    private EditText password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Reference to variables
        mob_no1 = (EditText) findViewById(R.id.mob_no);
        email1 = (EditText) findViewById(R.id.email);
        reg_id1 = (EditText) findViewById(R.id.reg_id);
        vision1= (EditText) findViewById(R.id.vision);
        website1= (EditText) findViewById(R.id.website);
        bio1= (EditText) findViewById(R.id.bio);
        name1= (EditText) findViewById(R.id.name);
        password1=(EditText)findViewById(R.id.password);
    }

    // Triggers when LOGIN Button clicked
    public void checkLogin(View arg0) {

        // Get text from email and passord field
        final String mob_no  = mob_no1.getText().toString();
        final String email  = email1.getText().toString();
        final String reg_id = reg_id1.getText().toString();
        final String vision = vision1.getText().toString();
        final String website = website1.getText().toString();
        final String bio = bio1.getText().toString();
        final String name=name1.getText().toString();
        final String password=password1.getText().toString();
        //validation call
      int k= check(mob_no,email,reg_id,vision,website,bio,name,password);
      if(k==1)
        new AsyncLogin().execute(mob_no,email,reg_id,vision,website,bio,name,password);

    }

    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://d2b6b22a.ngrok.io/ngo_helper/ngo_signup.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);


//Toast.makeText(MainActivity.this,""+params[0],Toast.LENGTH_LONG).show();
                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("mob_no", params[0])
                        .appendQueryParameter("email", params[1])
                        .appendQueryParameter("reg_id", params[2])
                        .appendQueryParameter("vision", params[3])
                        .appendQueryParameter("website", params[4])
                        .appendQueryParameter("bio", params[5])
                        .appendQueryParameter("name", params[6])
                        .appendQueryParameter("password", params[7])
                        .appendQueryParameter("limit_project_no",getString(R.string.amt ))
                        .appendQueryParameter("limit_project_amt", getString(R.string.proj ));
                String query="";
                try {

                   query = builder.build().getEncodedQuery();
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_LONG).show();
                }
               // Toast.makeText(MainActivity.this,""+query,Toast.LENGTH_LONG).show();
                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            Toast.makeText(MainActivity.this,""+result,Toast.LENGTH_LONG).show();
            ////already exist means go to signin
            if(result.equalsIgnoreCase("true"))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */

                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                MainActivity.this.finish();

            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_LONG).show();

            }
        }

    }
    public int  check(String mob_no,String email,String reg_id,String vision,String website,String bio,String name,String password)
    {
        if(mob_no=="")
        {
            mob_no1.setError("required");
            return 0;
        }
        else if(email=="")
        {
            email1.setError("required");
            return 0;
        }
        else if(reg_id=="")
        {
            reg_id1.setError("required");
            return 0;
        }
        else if(vision=="")
        {
            vision1.setError("required");
            return 0;
        }
        else if(website=="")
        {
            website1.setError("required");
            return 0;
        }
        else if(bio=="")
        {
            bio1.setError("required");
            return 0;
        }
        else if(name=="")
        {
            name1.setError("required");
            return 0;
        }
        else if(password=="")
        {
            password1.setError("required");
            return 0;
        }
        else
        {
            if(!validate_mob(mob_no))
            {
                mob_no1.setError("Enter correct no");
                return 0;
            }
            else if(!validate_email(email)) {
                email1.setError("Enter valid Email Address");
                return 0;
            }
           /* else if(!validate_website(website)){
                website1.setError("Enter proper website URL");
            }*/
        }
        return 1;
    }
    public boolean validate_email(final String hex) {
        pattern=Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
    public boolean validate_mob(final String mm){
        pattern= Pattern.compile(MOB_NO);
        matcher=pattern.matcher(mm);
        return  matcher.matches();
    }}