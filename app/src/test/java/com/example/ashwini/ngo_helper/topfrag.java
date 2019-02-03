package com.my.new2pma;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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
import java.util.Date;
import java.util.List;

import static com.my.new2pma.R.string.app_name;


/**
 * A simple {@link Fragment} subclass.
 */
public class topfrag extends Fragment {

    public RecyclerView subjectList;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public topfrag() {
        // Required empty public constructor
    }

    public List<Projects> subjects= new ArrayList<Projects>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_topfrag, container, false);








//        ViewPager viewPager=(ViewPager) view.findViewById(R.id.notification_front);





//        options=(RecyclerView) view.findViewById(R.id.recycler_view_for_option_card);
        subjectList=(RecyclerView) view.findViewById(R.id.first_Level_AttendenceView);
//        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getContext());
//        viewPager.setAdapter(pagerAdapter);
//        subjects.clear();
        preparedata();

        Log.i("listsize",String.valueOf(subjects.size()));
//        Date today=new Date();
       // subjects.add(new Projects("dbms","2","3","today","today",R.drawable.logo,90));
//        subjects.add(new Projects("dbms",2,3,today,today,R.drawable.logo,90));
//        List<Integer> imageresources=new ArrayList<Integer>();
//        imageresources.add(R.drawable.attendance);
//        imageresources.add(R.drawable.expense_icon_final);
//
//        imageresources.add(R.drawable.todo_final);


        try {

            subjectList.addOnItemTouchListener(
                    new RecyclerItemClickListener(getActivity(),subjectList, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                            TextView projectid= (TextView)view.findViewById(R.id.projectid);
                            int id=Integer.parseInt((String) projectid.getText());
                            event_home frag_event= new event_home();
                            Bundle bundle = new Bundle();
                            bundle.putInt("pid",id);
                            frag_event.setArguments(bundle);
//                            switch (position) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.contentdefault, frag_event).addToBackStack(null).commit();
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
        }
        //  ImageButton update=(ImageButton) options.findViewById(R.id.imageButton);
        /*View.OnClickListener updater=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expense();
            }
        };*/
        // update.setOnClickListener(updater);









       /* we will add this to recycler vie onitem click
        AdapterView.OnItemClickListener listListener =new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                Intent intent;
                switch(position){
                    case 0:
                       // intent=new Intent(First.this,SecondLevel_Attendence.class);
                       // startActivity(intent);
                        FragmentManager fm=getFragmentManager();
                        fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new topfrag()).addToBackStack(null).commit();
                      //  getActivity().getActionBar().setTitle("Attendence will come here");
                        break;
                    case 1:
                        //intent=new Intent(First.this,second_level_expenses.class);
                        //startActivity(intent);

                        FragmentManager ft=getFragmentManager();
                        ft.beginTransaction().replace(R.id.contentdefault, (Fragment)new secondlevel_expense()).addToBackStack(null).commit();

                        ((first) getActivity()).getSupportActionBar().setTitle("EXPENSE");
                        break;
                    case 2:
                       // intent=new Intent(First.this,second_level_todos.class);
                       // startActivity(intent);
                        FragmentManager fp=getFragmentManager();
                        fp.beginTransaction().replace(R.id.contentdefault, (Fragment)new secondlevel_todo()).addToBackStack(null).commit();

                       /* mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
                        setSupportActionBar(mActionBarToolbar);*/
        //   ((first) getActivity()).getSupportActionBar().setTitle("TODO");
        // getActivity().getActionBar().setTitle("EXPENSEactivity");

        // break;
        //}
               /* TextView text=(TextView) view.findViewById(R.id.textfirst);
                text.setText("you pressed the item no"+Integer.toString(position+1));*/
/*
            }
        };
        ListView listView=(ListView) view.findViewById(R.id.listfirst);
        listView.setOnItemClickListener(listListener);*/

        return view;

    }
    /*
        public  void expense()
        {
            FragmentManager fm=getFragmentManager();
            fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new topfrag()).addToBackStack(null).commit();
        }*/
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

//            Log.i("dataasd",result);
            subjects.clear();
            for(i=0;i<data1.length;i+=7){

//                subjects.add(new Projects("dbms",2,3,today,today,R.drawable.logo,90));

// Log.i("listsize",String.valueOf(subjects.size()));

          //   Toast.makeText(getContext(),""+String.valueOf(subjects.size()),Toast.LENGTH_LONG).show();

//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//                Log.i("dataasd",data1[(i+2)]+data1[(i+3)]+data1[(i+4)]+data1[(i+5)]+data1[(i+6)]);
                subjects.add(new Projects(data1[(i+2)],data1[(i+3)],data1[(i+4)],data1[(i+5)],data1[(i+6)],R.drawable.logo,Integer.parseInt(data1[(i+1)])));
            }

            RecyclerView.LayoutManager m1LayoutManager = new LinearLayoutManager(getContext());
            subjectList.setLayoutManager(m1LayoutManager);
            subjectList.setItemAnimator(new DefaultItemAnimator());
            Subject_List_Adapter subject_list_adapter = new Subject_List_Adapter(subjects, getContext());
            subjectList.setAdapter(subject_list_adapter);
//            return;


            ////already exist means go to signin


        }

    }
    public void preparedata(){
        new AsyncLogin().execute("dsmf");

    }

}
