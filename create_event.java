package com.example.ashwini.ngo_helper;


import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class create_event extends Fragment {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    Calendar myCalendar;
    EditText editText ;
    EditText name1;
    EditText amount1;
    EditText bio1;
    Button b;
    public create_event() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View v=inflater.inflate(R.layout.fragment_create_event, container, false);
            name1=v.findViewById(R.id.name);
            amount1=v.findViewById(R.id.amount);
            editText=v.findViewById(R.id.date);
            bio1=v.findViewById(R.id.bio);
            b=v.findViewById(R.id.button);
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    Log.i("check","inside onclick");

                    final String name = name1.getText().toString();

                    final String bio=bio1.getText().toString();
                    final String start_date= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    String myFormat = "yy/MM/dd"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    editText.setText(sdf.format(myCalendar.getTime()));

                    final String end_date=editText.getText().toString();
                final String amount=amount1.getText().toString();
                    Log.i("check","inside onclick3");

                //validation call
                Toast.makeText(getContext(),start_date+"",Toast.LENGTH_LONG).show();

                    AsyncLogin login= new AsyncLogin();
                    login.execute(name,start_date,end_date,amount,"abcd1234",bio);
//                new create_event.AsyncLogin().execute(name,start_date,end_date,amount,"aa");
                }
                catch(Exception e){
                    Log.i("check","inside onclick4");

                    Toast.makeText(getContext(),e+"",Toast.LENGTH_LONG).show();
                }
            }
        });
                return v;}


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
                url = new URL("http://d2b6b22a.ngrok.io/ngo_helper/project_funding.php");

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
                        .appendQueryParameter("start_date",params[1])
                        .appendQueryParameter("end_date", params[2])
                        .appendQueryParameter("amount", params[3]).
                        appendQueryParameter("org_id",params[4]).appendQueryParameter("bio",params[5]);
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
    private void updateLabel() {
        try {
            //Toast.makeText(a1.this,"hello1",Toast.LENGTH_LONG).show();

            String myFormat = "dd/MM/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            editText.setText(sdf.format(myCalendar.getTime()));

        }
        catch(Exception e){

        }
    }
        };

