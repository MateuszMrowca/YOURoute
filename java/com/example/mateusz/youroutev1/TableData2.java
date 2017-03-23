package com.example.mateusz.youroutev1;

import android.provider.BaseColumns;

/**
 * Created by Mateusz on 21/02/2017.
 */

public class TableData2
{
    public TableData2()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        //        public static final String LATITUDE = "latitude";
//        public static final String LONGITUDE = "longitude";
        public static final String COORDINATES = "coordinates";
        public static final String DATABASE_NAME = "gps_coordinates.db";
        //        public static final String TABLE_NAME = "default_name";
        public static final String TABLE_NAME = "route_name";
    }
}
