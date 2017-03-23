package com.example.mateusz.youroutev1;

import android.provider.BaseColumns;

/**
 * Created by Mateusz on 21/02/2017.
 */

public class TableData
{
    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String COORDINATES = "coordinates";
        public static final String DATABASE_NAME = "gps_coordinates.db";
        public static final String TABLE_NAME = "route_name";
    }
}
