package com.my.new2pma;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import java.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class first extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {






//        Intent temp = new Intent(this, Notification_receivor.class);
//
//        //old notification system
//        temp.putExtra("des","mark todays attendence");
//        temp.putExtra("notify",false);
//        Calendar myCalendar= Calendar.getInstance();
//        myCalendar.set(Calendar.HOUR_OF_DAY,18,30);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, temp,PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button explore,feed,shop;
        explore=(Button) findViewById(R.id.explore);
        feed=(Button)findViewById(R.id.feed);
        shop=(Button)findViewById(R.id.shop);
//
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                explore_frag myfrag= new explore_frag();
                explore1 myfrag= new explore1();
                getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,myfrag).commit();

            }
        });
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topfrag firstFragment = new topfrag();
                getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,firstFragment).commit();
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_frag myfrag= new shop_frag();
                getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,myfrag).commit();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(findViewById(R.id.contentdefault)!=null) {
            if(savedInstanceState==null) {
                topfrag firstFragment = new topfrag();
             //   FragmentManager manager=new getSupportFragmentManager();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.contentdefault, firstFragment).commit();
              /*  Slider secondfrag=new Slider();
                 getSupportFragmentManager().beginTransaction()
                        .add(R.id.slider, secondfrag).commit();*/

            }


        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.home) {
//            FragmentManager fm=getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new topfrag()).addToBackStack(null).commit();
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//            FragmentManager fm=getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new thirdlevel_todoadd()).addToBackStack(null).commit();
//
//
//        } else if (id == R.id.nav_slideshow) {
//
//            FragmentManager fm=getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new expense_add()).addToBackStack(null).commit();
//
//        } else if (id == R.id.nav_manage) {
//
//
//
//            FragmentManager fm=getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new thirdlevel_todoadd()).addToBackStack(null).commit();
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
