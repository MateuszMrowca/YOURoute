package com.example.mateusz.youroutev1;

import java.util.ArrayList;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class displayAllWaypoints extends Activity {

    private MapView myOpenMapView;
    private MapController myMapController;

    ArrayList<OverlayItem> anotherOverlayItemArray;
    ArrayList waypointsListFromIntent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_waypoints);

        myOpenMapView = (MapView)findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(2);
        waypointsListFromIntent = new ArrayList();

        //--- Create Another Overlay for multi marker
        anotherOverlayItemArray = new ArrayList<OverlayItem>();




        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                waypointsListFromIntent = null;
            }
            else
            {
                waypointsListFromIntent = extras.getParcelableArrayList("waypointsList");
            }
        }
        else
        {
            waypointsListFromIntent = (ArrayList) savedInstanceState.getSerializable("waypointsList");
        }

        for(int i = 0; i < waypointsListFromIntent.size(); i++)
        {
            ArrayList tempWaypoint = (ArrayList) waypointsListFromIntent.get(i);

            String title = tempWaypoint.get(0).toString();
            String desc = tempWaypoint.get(1).toString();
            String lat = tempWaypoint.get(2).toString();
            String lon = tempWaypoint.get(3).toString();

            double latitude = Double.parseDouble(lat);
            double longitude = Double.parseDouble(lon);

            anotherOverlayItemArray.add(new OverlayItem(title, desc, new GeoPoint(latitude, longitude)));
        }
        anotherOverlayItemArray.add(new OverlayItem("0, 0", "0, 0", new GeoPoint(0, 0)));

        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay
                = new ItemizedIconOverlay<OverlayItem>(
                this, anotherOverlayItemArray, myOnItemGestureListener);
        myOpenMapView.getOverlays().add(anotherItemizedIconOverlay);

        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        myOpenMapView.getOverlays().add(myScaleBarOverlay);
    }

    OnItemGestureListener<OverlayItem> myOnItemGestureListener
            = new OnItemGestureListener<OverlayItem>(){

        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            Toast.makeText(displayAllWaypoints.this, "Title: " +item.getTitle() + "\n" + "Description: " + item.getSnippet() + "\n" + item.getPoint().getLatitudeE6() + " : " + item.getPoint().getLongitudeE6() , Toast.LENGTH_LONG).show();
            return true;
        }

    };

}