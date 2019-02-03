package com.my.new2pma;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.List;
public class explore1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    // TODO: Rename and change types of parameters

    private EditText name;
    private Button btn;
    public RecyclerView rv;
//    private OnFragmentInteractionListener mListener;

    public explore1() {
    }
//    public static explore1 newInstance(String param1, String param2) {
//        explore1 fragment = new explore1();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    public List<ngo_explore> ngos= new ArrayList<ngo_explore>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_explore1, container, false);
        rv=(RecyclerView)v.findViewById(R.id.list_qw);
        name=(EditText)v.findViewById(R.id.editText);
        btn= (Button)v.findViewById(R.id.button2);

     preparedata();
       /* try {

            rv.addOnItemTouchListener(
                    new RecyclerItemClickListener(getActivity(),rv, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                            TextView name= (TextView)view.findViewById(R.id.name);
                            int id=Integer.parseInt((String) name.getText());
                            event_home frag_event= new event_home();
                            Bundle bundle = new Bundle();
                            bundle.putInt("pid",id);
                            frag_event.setArguments(bundle);
//                            switch (position) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.explore, frag_event).addToBackStack(null).commit();
//

                        }
                        @Override
                        public void onLongItemClick(View view, int position) {

                        }
                    }
                    ));

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"there is an error"+e,Toast.LENGTH_SHORT).show();
        }*/
        return v;
    }
    public void preparedata(){
        new AsyncLogin().execute("dsmf");

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
                url = new URL("http://d2b6b22a.ngrok.io/ngo_helper/check_ret.php");

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
            int i=0;
            ngos.clear();


            ////
            for(i=0;i<data1.length;i+=7){
//                ngos.add(new ngo_explore(data1[(i+2)],data1[(i+4)],data1[(i+3)],data1[(i+5)],data1[(i+6)],R.drawable.logo,Integer.parseInt(data1[(i+1)])));
            }
            ////


            RecyclerView.LayoutManager m1LayoutManager = new LinearLayoutManager(getContext());
            rv.setLayoutManager(m1LayoutManager);
            rv.setItemAnimator(new DefaultItemAnimator());
            ngos_adapter ngosla = new ngos_adapter(ngos,getContext());
            rv.setAdapter(ngosla);
//            return;


            ////already exist means go to signin


        }

    }
}


