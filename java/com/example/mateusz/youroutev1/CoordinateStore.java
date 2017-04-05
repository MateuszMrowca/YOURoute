package com.example.mateusz.youroutev1;
import java.util.ArrayList;


public class CoordinateStore
{
    private ArrayList<Coordinate> cList;

    public CoordinateStore()
    {
        this.cList = new ArrayList<Coordinate>();
    }

    public ArrayList<Coordinate> getpList()
    {
        return cList;
    }

    public void add(Coordinate p)
    {
        if(!cList.contains(p)) //uniqueness
            cList.add(p);
    }
}
