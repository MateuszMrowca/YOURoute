package com.example.mateusz.youroutev1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseOperations extends SQLiteOpenHelper
{
    public static final int database_version = 1;

    public String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS "+TableData.TableInfo.TABLE_NAME+" ("+TableData.TableInfo.COORDINATES+" TEXT );";

    public DatabaseOperations(Context context)
    {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb)
    {
        sdb.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion)
    {

    }

    public boolean  addCoordinatesToTable(DatabaseOperations dopm, double latitude, double longitude)
    {
        SQLiteDatabase SQ = dopm.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String coordinates = latitude + "," + longitude;
        cv.put(TableData.TableInfo.COORDINATES, coordinates);

        long result = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);

        if(result == -1)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    public void clearTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
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





























