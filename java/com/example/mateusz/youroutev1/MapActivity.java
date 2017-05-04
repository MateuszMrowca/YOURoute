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

/*Source:<https://code.google.com/archive/p/osmbonuspack/wikis/Tutorial_1.wiki> Accessed:<04/04/2017>*/
public class MapActivity extends AppCompatActivity
{
    private MapView osm;
    private MapController mc;
    ArrayList<GeoPoint> waypoints = new ArrayList<>();
    RoadManager roadManager = new OSRMRoadManager();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_map);

        osm = (MapView) findViewById(R.id.map);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);

        mc = (MapController) osm.getController();
        mc.setZoom(20);

        ArrayList coordinatesArrayList = getIntent().getParcelableArrayListExtra("List_Of_Coordintes");

        if (coordinatesArrayList.size() != 0)
        {
            drawRouteOnMap(coordinatesArrayList);
        }
    }

    public void drawRouteOnMap(ArrayList coordinateList)
    {
        for (int i = 0; i < coordinateList.size(); i++) {
            double[] a = (double[]) coordinateList.get(i);

            waypoints.add(new GeoPoint(a[0], a[1]));
        }

        double[] start = (double[]) coordinateList.get(0);
        GeoPoint startpoint = new GeoPoint(start[0], start[1]);

        Road road = roadManager.getRoad(waypoints);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, this);
        osm.getOverlays().add(roadOverlay);

        mc.setCenter(startpoint);

        osm.invalidate();
    }
}
