package com.example.mateusz.youroutev1;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadRoute extends AppCompatActivity
{
    private Button displayMap;
    private ArrayList<double[]> coordinatesasdoubles;
    private String fileName;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_route);
        coordinatesasdoubles = new ArrayList<>();

        displayMap = (Button) findViewById(R.id.displayMap);
        final Gson gson = new Gson();
        fileName = "route1.txt";
        final ListView listView = (ListView) findViewById(R.id.listView);
        final TextView pathTexView = (TextView) findViewById(R.id.pathTextView);


        ArrayList<String> filenamesList = new ArrayList<String>();
//        String path = Environment.getExternalStorageDirectory().toString()+"/Pictures/";
        final File path2 =
                Environment.getExternalStoragePublicDirectory
                        (
                                Environment.DIRECTORY_DCIM + "/YOURoute/Routes/"
                        );
        String path = path2.toString();

        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
            filenamesList.add(files[i].getName());
        }


        ListAdapter listAdapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, filenamesList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int itemPos = position;
                String fileName =(String) listView.getItemAtPosition(position);
                Toast.makeText(LoadRoute.this, fileName, Toast.LENGTH_SHORT).show();
                pathTexView.setText(fileName);


                String readfromfile = readFromFile(fileName);
                TypeToken<ArrayList<double[]>> token = new TypeToken<ArrayList<double[]>>(){};
                coordinatesasdoubles = gson.fromJson(readfromfile, token.getType());
            }
        });


//        chooseFileButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(LoadRoute.this, "load file " + fileName, Toast.LENGTH_SHORT).show();
//
//                //get path
//                //get file
//                //parse contents to coordinatesasdoubles
//                String readfromfile =  readFromFile();
//
//
//                TypeToken<ArrayList<double[]>> token = new TypeToken<ArrayList<double[]>>(){};
//                coordinatesasdoubles = gson.fromJson(readfromfile, token.getType());
//                System.out.println();
//            }
//        });

        displayMap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(LoadRoute.this, "display map", Toast.LENGTH_SHORT).show();
                //get arraylist
                //sends over as intent and display route on map
                Intent intent = new Intent(LoadRoute.this, FollowRouteActivity.class);
                intent.putExtra("List_Of_Coordintes", coordinatesasdoubles);
                startActivity(intent);
            }
        });
    }

    public String readFromFile(String fileName)
    {
        //Display directories
        //click directory of choice
        //
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                Environment.DIRECTORY_DCIM + "/YOURoute/Routes/"
                        );
        File file = new File(path, fileName);

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return text.toString();
    }
}
