package com.my.new2pma;

import android.app.ProgressDialog;
import android.content.Intent;
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

import static com.my.new2pma.R.string.app_name;

public class shop_frag extends Fragment {
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecyclerView productList;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public shop_frag() {
    }









    public List<Product> products= new ArrayList<Product>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.shop_frag, container, false);

        productList=(RecyclerView) view.findViewById(R.id.first_Level_AttendenceView1);
     //   products.add(new Product("hfh","hello","hi","ok",93));
        new AsyncLogin().execute("dsmf");
           // preparedata();

       /* try {

            productList.addOnItemTouchListener(
                    new RecyclerItemClickListener(getActivity(),productList, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                            TextView projectid= (TextView)view.findViewById(R.id.projectid);
                            int id=Integer.parseInt((String) projectid.getText());
                            product_home my= new product_home();
                            Bundle bundle = new Bundle();
                            bundle.putInt("pid",id);
                            my.setArguments(bundle);
//                            switch (position) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.contentdefault, my).addToBackStack(null).commit();
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

        return view;

    }
    @Override
    public  void onResume() {
        try {
            ((first) getActivity()).getSupportActionBar().setTitle(app_name);
        } catch (Exception e) {
            Log.e("action bar error::", String.valueOf(e));
        }
        super.onResume();
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
                url = new URL("http://d2b6b22a.ngrok.io/ngo_helper/products_list.php");

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

            Toast.makeText(getContext(),""+result,Toast.LENGTH_LONG).show();
//            Log.i("dataasd",result);
        //    subjects.clear();
          //  for(i=0;i<data1.length;i+=7){

            products.add(new Product(data1[0],data1[1],data1[2],data1[3], Integer.parseInt(data1[4])));
             //subjects.add(new Projects("dbms",2,3,today,today,R.drawable.logo,90));

// Log.i("listsize",String.valueOf(subjects.size()));

                //

//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//                Log.i("dataasd",data1[(i+2)]+data1[(i+3)]+data1[(i+4)]+data1[(i+5)]+data1[(i+6)]);
           //     subjects.add(new Projects(data1[(i+2)],data1[(i+3)],data1[(i+4)],data1[(i+5)],data1[(i+6)],R.drawable.logo,Integer.parseInt(data1[(i+1)])));
       //     }

   RecyclerView.LayoutManager m1LayoutManager = new LinearLayoutManager(getContext());
            productList.setLayoutManager(m1LayoutManager);
            productList.setItemAnimator(new DefaultItemAnimator());
            Product_List_Adapter subject_list_adapter = new Product_List_Adapter(products, getContext());
            productList.setAdapter(subject_list_adapter);
//            return;


            ////already exist means go to signin


        }

    }

}





