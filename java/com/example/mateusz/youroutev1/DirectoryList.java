package com.example.mateusz.youroutev1;

/**
 * Created by Mateusz on 10/04/2017.
 */

import android.app.ListActivity;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryList extends ListActivity
{
    private List<String> filelist = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final File path = Environment.getExternalStoragePublicDirectory
                (
                    Environment.DIRECTORY_DCIM + "/YOURoute/Routes/"
                );
        ListDir(path);
    }

    void ListDir(File f)
    {
        File[] files = f.listFiles();
        filelist.clear();
        for (File file : files) {
            filelist.add(file.getPath());
        }
        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filelist);
        setListAdapter(listAdapter);
    }
}
