package com.example.mateusz.youroutev1;
import java.io.Serializable;

public class Coordinate implements Serializable
{
    private double[] coordinate;
    private double lat, lon;

    public Coordinate(double lat, double lon)
    {
        coordinate = new double[2];
        this.coordinate[0]= lat;
        this.coordinate[1]= lon;
        this.lat = lat;
        this.lon = lon;
    }

    public double[] getCoordinate() {
        return coordinate;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setCoordinate(double[] coordinate) {
        this.coordinate = coordinate;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
