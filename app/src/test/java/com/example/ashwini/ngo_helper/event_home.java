package com.my.new2pma;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class event_home extends Fragment {
    int pid=5;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    String[] data= new String[6];
    public event_home() {
        // Required empty public constructor
    }

    TextView stdate,endate,overview,total,received;
    ProgressBar prgs;
    EditText in;
    Button pay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pid = bundle.getInt("pid", 5);
//            pid=5;
        }

        View v= inflater.inflate(R.layout.fragment_event_home, container, false);
        getdata(pid);
        stdate= (TextView) v.findViewById(R.id.posted);
        endate=(TextView) v.findViewById(R.id.enddate);
        overview=(TextView) v.findViewById(R.id.overview);
        in=(EditText) v.findViewById(R.id.payin);
        pay=(Button) v.findViewById(R.id.pay);
        prgs= (ProgressBar)v.findViewById(R.id.prgs);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                payment pyfr= new payment();
                getFragmentManager().beginTransaction().replace(R.id.contentdefault,pyfr).addToBackStack(null).commit();
            }
        });
        try {

            Toast.makeText(getContext(),data[0],Toast.LENGTH_LONG).show();
            stdate.setText(data[0]);
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),""+e,Toast.LENGTH_LONG).show();
        }
        return v;
    }

    private void getdata(int pid)
    {



        new AsyncLogin().execute(String.valueOf(pid));
//        data[0]="ashwini";
//        data[1]="ashwini";
//        data[2]="ashwini";
//        data[3]="ashwini";
//        data[4]="ashwini";
//        data[5]="ashwini";
    }


    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(getContext());
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
                url = new URL("http://d2b6b22a.ngrok.io/ngo_helper/project_info.php");

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
                        .appendQueryParameter("pid", params[0]);

                String query="";
                try {

                    query = builder.build().getEncodedQuery();
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),""+e,Toast.LENGTH_LONG).show();
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
            String[] data1=result.split("\\.");
            data[0]=data1[4];
            data[1]=data1[5];
            data[2]="Organisation id:"+data1[0]+"\n"+data1[1]+"\n"+data1[6];
            data[3]=data1[2];
            data[4]=data1[3];

            endate.setText(data[1]);
            overview.setText(data[2]);
            int total = Integer.parseInt(data[3]);
            int received = Integer.parseInt(data[4]);
            prgs.setMax(100);
            prgs.setProgress(received * 100 / total);

             Toast.makeText(getContext(),data[0],Toast.LENGTH_LONG).show();
            ////already exist means go to signin


        }

    }}

//}

