package com.example.ashwini.ngo_helper;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class add_product extends Fragment {
    EditText name1;
    EditText price1;
    EditText contribution1;
    EditText brief1;
    Button b;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public add_product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try{
        Toast.makeText(getContext(),"here",Toast.LENGTH_LONG).show();}
        catch (Exception e)
        {

        }
        View v=inflater.inflate(R.layout.fragment_add_product, container, false);
        name1=v.findViewById(R.id.name);
        price1=v.findViewById(R.id.price);
        contribution1=v.findViewById(R.id.contribution);
        brief1=v.findViewById(R.id.brief);
        b=v.findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=name1.getText().toString();
                final String price=price1.getText().toString();
                final String contribution=contribution1.getText().toString();
                final String brief=brief1.getText().toString();
                add_product.AsyncLogin login= new add_product.AsyncLogin();
                login.execute(name,price,contribution,brief,"provider");//instead of provider ngo unique reg_id
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.i("check","inside onclick5");
            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://d2b6b22a.ngrok.io/ngo_helper/add_product.php");

                Log.i("check","inside onclick7");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block

                Log.i("check","inside onclick6");
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);

                Log.i("check","inside onclick8");
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Log.i("check","inside onclick9");


                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("name", params[0])
                        .appendQueryParameter("price",params[1])
                        .appendQueryParameter("contribution", params[2])
                        .appendQueryParameter("brief", params[3]).appendQueryParameter("provider","abcd1234");
                String query = builder.build().getEncodedQuery();


                //Toast.makeText(ngo_signin.this,""+query,Toast.LENGTH_LONG).show();
                // Open connection for sending data
                OutputStream os=null;
                try {
                    os = conn.getOutputStream();

                    Log.i("check", "query encoded");
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();

                    conn.connect();
                }
                catch (Exception e)
                {
                    Log.i("check","conn done"+e.toString());

                }
            } catch (IOException e1) {

                Log.i("check", e1.toString());
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                Log.i("check","response");
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

                    Log.i("check","no response");
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
            Toast.makeText(getContext(),""+result,Toast.LENGTH_LONG).show();
            ////already exist means go to signin
            if(result.equalsIgnoreCase("true"))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */
                Toast.makeText(getContext(), "success"+result, Toast.LENGTH_LONG).show();
                //intent to homepage
                //ngo_signin.this.finish();

            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(getContext(), "Invalid reg_id or password", Toast.LENGTH_LONG).show();

            } else{

                Toast.makeText(getContext(), "not verified"+result, Toast.LENGTH_LONG).show();

            }
        }

    }

}
