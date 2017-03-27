package com.example.mateusz.youroutev1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mateusz on 21/02/2017.
 */

public class DatabaseOperations extends SQLiteOpenHelper
{
    public static final int database_version = 1;

    //DROP TABLE BEFORE THIS!
    public String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS "+TableData.TableInfo.TABLE_NAME+" ("+TableData.TableInfo.COORDINATES+" TEXT );";

    public DatabaseOperations(Context context)
    {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb)
    {
        clearTable();
        Log.d("DBO", "clearTable");

        sdb.execSQL(CREATE_TABLE_QUERY);
        Log.d("DBO", "Table Created using: " + CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion)
    {

    }

    public boolean  addCoordinatesToTable(DatabaseOperations dopm, double latitude, double longitude)
    {
        SQLiteDatabase SQ = dopm.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Log.d("DBO", "madecv");
        String coordinates = "(" + latitude + "," + longitude + ")";
        Log.d("DBO", "coordinates are: " + coordinates);
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

    public void clearTable()
    {
//       TODO clear table query
        SQLiteDatabase db = this.getWritableDatabase();// db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
        db.execSQL("DELETE FROM " + TableData.TableInfo.TABLE_NAME);
        db.execSQL(CREATE_TABLE_QUERY);
        db.close();
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TableData.TableInfo.TABLE_NAME, null);
        return data;
    }
}





























