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
        mc.setZoom(12);


        /*
        * for (int i = 0; i> arraylist.size; i++)
        * {
        *   waypoint.add(new GeoPoint (array.get[0], array.get[1]))
        * }
        * */

        RoadManager roadManager = new OSRMRoadManager();
        ArrayList<GeoPoint> waypoints = new ArrayList<>();

        GeoPoint Point0 = new GeoPoint (53.241678960621357 , -3.567476961761713);
        GeoPoint Point1 = new GeoPoint (53.24166202917695 , -3.56757402420044);
        GeoPoint Point2 = new GeoPoint (53.241631016135216 , -3.567676031962037);
        GeoPoint Point3 = new GeoPoint (53.241605032235384 , -3.56779002584517);
        GeoPoint Point4 = new GeoPoint (53.241580976173282 , -3.567866971716285);
        GeoPoint Point5 = new GeoPoint (53.241558009758592 , -3.567973002791405);

        waypoints.add(Point0);
        waypoints.add(Point1);
        waypoints.add(Point2);
        waypoints.add(Point3);
        waypoints.add(Point4);
        waypoints.add(Point5);

        Road road = roadManager.getRoad(waypoints);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, this);
        osm.getOverlays().add(roadOverlay);

        mc.setCenter(Point1);
        osm.invalidate();
    }

    public void addmarker(GeoPoint centre)
    {
        Marker marker = new Marker(osm);
        marker.setPosition(centre);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        osm.getOverlays().clear();
        osm.getOverlays().add(marker);
        osm.invalidate();
    }
}
