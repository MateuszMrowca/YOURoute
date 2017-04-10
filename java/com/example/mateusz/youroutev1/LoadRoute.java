package com.example.mateusz.youroutev1;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button chooseFileButton, displayMap;
    private ArrayList<double[]> coordinatesasdoubles;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_route);
        coordinatesasdoubles = new ArrayList<>();

        chooseFileButton = (Button) findViewById(R.id.chooseFileButton);
        displayMap = (Button) findViewById(R.id.displayMap);
        final Gson gson = new Gson();

        chooseFileButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(LoadRoute.this, "load file", Toast.LENGTH_SHORT).show();

                //get path
                //get file
                //parse contents to coordinatesasdoubles
                String readfromfile =  readFromFile();


                TypeToken<ArrayList<double[]>> token = new TypeToken<ArrayList<double[]>>(){};
                coordinatesasdoubles = gson.fromJson(readfromfile, token.getType());
                System.out.println();
            }
        });

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

    public String readFromFile()
    {
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                //Environment.DIRECTORY_PICTURES
                                Environment.DIRECTORY_DCIM + "/YOURoute/Routes/"
                        );
        File file = new File(path, "routetodundalk.txt");

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
