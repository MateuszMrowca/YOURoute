package com.example.mateusz.youroutev1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mateusz on 21/02/2017.
 */

public class DatabaseOperations extends SQLiteOpenHelper
{
    public static final int database_version = 1;
//    public String CREATE_QUERY = "CREATE TABLE "+TableData.TableInfo.TABLE_NAME+" ("+TableData.TableInfo.LATITUDE+" DOUBLE,"+ TableData.TableInfo.LONGITUDE+" DOUBLE );";

    //truncate table before this
    public String TRUNCATE_TABLE_QUERY = "TRUNCATE TABLE "+ TableData.TableInfo.TABLE_NAME;

    //DROP TABLE BEFORE THIS!
//    public String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TableData.TableInfo.TABLE_NAME;
    public String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS "+TableData.TableInfo.TABLE_NAME+" ("+TableData.TableInfo.COORDINATES+" TEXT );";

    public DatabaseOperations(Context context)
    {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb)
    {
        sdb.execSQL(CREATE_TABLE_QUERY);
        Log.d("Database operations", "Table Created using: " + CREATE_TABLE_QUERY);

        //clear table query
//        sdb.execSQL(TRUNCATE_TABLE_QUERY);
//        Log.d("Database operations", "Table cleared using: " + TRUNCATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public boolean  addCoordinatesToTable(DatabaseOperations dopm, double latitude, double longitude)
    {
        SQLiteDatabase SQ = dopm.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String coordinates = "(" + latitude + "," + longitude + ")";

        cv.put(TableData.TableInfo.COORDINATES, coordinates);

        long result = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);

        if(result == -1)
        {
            return false;
        }

        else
        {
            Log.d("Database operations", "One row inserted");
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TableData.TableInfo.TABLE_NAME, null);
        return data;
    }
}





























