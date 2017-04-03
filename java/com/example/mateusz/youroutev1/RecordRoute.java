package com.example.mateusz.youroutev1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordRoute extends AppCompatActivity
{
    private Button stopRoute, startRoute, saveRoute, clearRoute, showRoute;
    private TextView coordinatestv;
    private BroadcastReceiver broadcastReceiver;
    DatabaseOperations myDB;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_route);

        stopRoute = (Button) findViewById(R.id.stopRouteButton);
        startRoute = (Button) findViewById(R.id.startRouteButton);
        saveRoute = (Button) findViewById(R.id.saveRouteButton);
        clearRoute = (Button) findViewById(R.id.clearRouteButton);
        showRoute = (Button) findViewById(R.id.showRouteButton);

        coordinatestv = (TextView) findViewById(R.id.coordinatesTextView);
        if(!runtime_permissions())
        {
            enable_buttons();
        }

        ListView listView = (ListView)findViewById(R.id.listView);
        myDB =new DatabaseOperations(ctx);

        //populate arraylist with table data and then view it
        ArrayList<String> coordList = new ArrayList<>();
        Cursor data =  myDB.getListContents();

        ArrayList<double[]>coordinatesasdoubles = new ArrayList<double[]>();

        String cData;

        if(data.getCount() == 0)
        {
            Toast.makeText(ctx, "No contents in the list", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(data.moveToNext()) {
                cData = data.getString(0);//coordinates

                List<String> geopointlistnotsplit = Arrays.asList(cData.split(","));

                System.out.print("Lat:" + geopointlistnotsplit.get(0));
                System.out.print("Lon:" + geopointlistnotsplit.get(1));

                double latCoord = Double.parseDouble(geopointlistnotsplit.get(0));
                double lonCoord = Double.parseDouble(geopointlistnotsplit.get(1));

                double[] geopointsList = new double[2];

                geopointsList[0] = latCoord;
                geopointsList[1] = lonCoord;

                coordinatesasdoubles.add(geopointsList);

                coordList.add(cData);//cannot be a String needs to take in two doubles

                ListAdapter listAdapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, coordList);
                listView.setAdapter(listAdapter);
            }

        }
    }

    @Override
    protected void onPause()
    {
        System.out.print("onPause");
        super.onPause();

    }

    @Override
    protected void onResume()
    {
        Toast.makeText(getBaseContext(), "onResume", Toast.LENGTH_SHORT).show();

        super.onResume();
        if(broadcastReceiver != null)
        {
            broadcastReceiver = new BroadcastReceiver()
            {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                    Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
                    coordinatestv.setText("\n" +intent.getExtras().get("coordinates"));
                }
            };
            registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(broadcastReceiver != null)
        {
            unregisterReceiver(broadcastReceiver);
        }
    }





    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }

    private void enable_buttons()
    {
        SQLiteDatabase sdb;

        startRoute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //here pass in route name as putExtra?
                Intent i = new Intent(getApplicationContext(),GPS_Service.class);
                startService(i);
            }
        });

        stopRoute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO add prompt for save or delete
                Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                stopService(i);

            }
        });

        saveRoute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getBaseContext(), "Save route", Toast.LENGTH_SHORT).show();

            }
        });

        clearRoute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getBaseContext(), "Clear table", Toast.LENGTH_SHORT).show();
                myDB.clearTable();

            }
        });



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }else {
                runtime_permissions();
            }
        }
    }
}