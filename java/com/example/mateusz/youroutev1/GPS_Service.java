package com.example.mateusz.youroutev1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

public class GPS_Service extends Service
{
    private LocationManager locationManager;
    private LocationListener listener;

    Context ctx = this;

    public GPS_Service()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(locationManager != null)
        {
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
    }

    @Override
    public void onCreate()
    {
        final DatabaseOperations DB = new DatabaseOperations(ctx);


        listener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                Intent i = new Intent("onLocationUpdate");
                i.putExtra("coordinates", location.getLongitude()+ " : " +location.getLatitude());
                DB.addCoordinatesToTable(DB, location.getLatitude(), location.getLongitude());
                Toast.makeText(getBaseContext(), "Added coordinates " +location.getLongitude()+ " : " +location.getLatitude(), Toast.LENGTH_SHORT).show();
                sendBroadcast(i);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle)
            {

            }

            @Override
            public void onProviderEnabled(String s)
            {

            }

            @Override
            public void onProviderDisabled(String s)
            {
                Toast.makeText(getBaseContext(), "onProviderDisabled", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };
        locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);

        //noinspection ResourceType
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5, listener); //this is where i will set the user preferences for MINdistance and MINtime


    }
}
