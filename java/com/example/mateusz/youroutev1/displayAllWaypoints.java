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

/*Source <http://android-er.blogspot.ie/2012/05/create-multi-marker-openstreetmap-for.html> Accessed: <01/05/2017>
changes were made to fit my user created waypoints, this is passed in from previous activity.*/


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
        myOpenMapView.setMultiTouchControls(true);
        myOpenMapView.getMapCenter();
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(13);
        waypointsListFromIntent = new ArrayList();

        //--- Create Another Overlay for multi marker
        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        /*Source:<http://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android> Accessed:<1/05/2017>*/
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

        //this for loop was added so that i could add all waypoints as markers
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
            return false;
        }


        //changed the toast that displays information for user so it is easier to understand.
        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            Toast.makeText(displayAllWaypoints.this, "Title: " +item.getTitle() + "\n" + "Description: " + item.getSnippet() + "\n" + item.getPoint().getLatitudeE6() + " : " + item.getPoint().getLongitudeE6() , Toast.LENGTH_LONG).show();
            return true;
        }

    };

}