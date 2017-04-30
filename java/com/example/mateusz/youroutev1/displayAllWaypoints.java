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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_waypoints);

        myOpenMapView = (MapView)findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(2);

        //--- Create Another Overlay for multi marker
        anotherOverlayItemArray = new ArrayList<OverlayItem>();
        anotherOverlayItemArray.add(new OverlayItem(
                "0, 0", "0, 0", new GeoPoint(0, 0)));
        anotherOverlayItemArray.add(new OverlayItem(
                "US", "US", new GeoPoint(38.883333, -77.016667)));
        anotherOverlayItemArray.add(new OverlayItem("China", "China", new GeoPoint(39.916667, 116.383333)));
        anotherOverlayItemArray.add(new OverlayItem("","United Kingdom", new GeoPoint(51.5, -0.116667)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Germany", "Germany", new GeoPoint(52.516667, 13.383333)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Korea", "Korea", new GeoPoint(38.316667, 127.233333)));
        anotherOverlayItemArray.add(new OverlayItem(
                "India", "India", new GeoPoint(28.613333, 77.208333)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Russia", "Russia", new GeoPoint(55.75, 37.616667)));
        anotherOverlayItemArray.add(new OverlayItem(
                "France", "France", new GeoPoint(48.856667, 2.350833)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Canada", "Canada", new GeoPoint(45.4, -75.666667)));

        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay
                = new ItemizedIconOverlay<OverlayItem>(
                this, anotherOverlayItemArray, myOnItemGestureListener);
        myOpenMapView.getOverlays().add(anotherItemizedIconOverlay);
        //---

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
            Toast.makeText(displayAllWaypoints.this, "Title: " +item.getTitle() + "\n" + "Snippet: " + item.getSnippet() + "\n" + item.getPoint().getLatitudeE6() + " : " + item.getPoint().getLongitudeE6() , Toast.LENGTH_LONG).show();
            return true;
        }

    };

}