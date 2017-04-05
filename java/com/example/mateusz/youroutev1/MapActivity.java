package com.example.mateusz.youroutev1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.bonuspack.overlays.*;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity
{

    private MapView osm;
    private MapController mc;
    private ArrayList<double[]>coordinatesasdoubles;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //TODO receive the arraylist

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_map);

        osm = (MapView) findViewById(R.id.map);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);

        mc = (MapController) osm.getController();
        mc.setZoom(20);

        RoadManager roadManager = new OSRMRoadManager();
        ArrayList<GeoPoint> waypoints = new ArrayList<>();

        ArrayList coordinatesArrayList = getIntent().getParcelableArrayListExtra("List_Of_Coordintes"); //TODO  seerialize arraylist of coordinates

        for(int i = 0; i<coordinatesArrayList.size(); i++)
        {
            double[] a = (double[]) coordinatesArrayList.get(i);

            waypoints.add(new GeoPoint(a[0], a[1]));
        }


        double[] start= (double[]) coordinatesArrayList.get(0);
        GeoPoint startpoint = new GeoPoint(start[0], start[1]);




        Road road = roadManager.getRoad(waypoints);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, this);
        osm.getOverlays().add(roadOverlay);

        mc.setCenter(startpoint);

//        addmarker(startpoint);

        osm.invalidate();
    }

    public void addmarker(GeoPoint start)
    {
        Marker marker = new Marker(osm);
        marker.setPosition(start);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        osm.getOverlays().clear();
        osm.getOverlays().add(marker);


        osm.invalidate();
    }
}
