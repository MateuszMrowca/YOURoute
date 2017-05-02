package com.example.mateusz.youroutev1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Waypoints extends AppCompatActivity
{

    private Button showDialogButton, displayAllWaypoits;
    String[] methods = {"Enter Coordinates","Enter Address","From Map","From Photo"};
    ArrayList waypointInfo;
    ArrayList<ArrayList> waypointsList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waypoints);

        waypointsList = new ArrayList<>();


        showDialogButton = (Button) findViewById(R.id.add_waypoint);
        showDialogButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Waypoints.this);
                builder.setTitle("Select method");
                builder.setItems(methods, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Toast.makeText(getBaseContext(), methods[i],Toast.LENGTH_SHORT).show();
                        if(methods[i].equals("Enter Coordinates"))
                        {
                            Intent intentGetGetMessage = new Intent(Waypoints.this, add_by_coordinates_activity.class);
                            startActivityForResult(intentGetGetMessage, 2); // get results and pass on to create a waypoint
                            Toast.makeText(getBaseContext(), "coordinates", Toast.LENGTH_SHORT).show();
                        }
                        else if(methods[i].equals("Enter Address"))
                        {
                            Toast.makeText(getBaseContext(), "Add a waypoint from address - Coming Soon!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Waypoints.this, add_by_address_activity.class);
//                            startActivity(intent);
                        }
                        else if(methods[i].equals("From Map"))
                        {

                            Toast.makeText(getBaseContext(), "Add a waypoint from map - Coming Soon!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Waypoints.this, add_by_map_click_activity.class);
//                            startActivity(intent);
                        }
                        else if(methods[i].equals("From Photo"))
                        {

                            Toast.makeText(getBaseContext(), "Add a waypoint from photo location taken - Coming Soon!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Waypoints.this, add_from_photo_activity.class);
//                            startActivity(intent);
                        }
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        displayAllWaypoits = (Button) findViewById(R.id.display_all_waypoints);
        displayAllWaypoits.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //get an arraylist of waypoints in this class
                Intent intent = new Intent(Waypoints.this, displayAllWaypoints.class);
                intent.putExtra("waypointsList", waypointsList);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2)
        {
            if(data != null) {
                ArrayList<String> message = data.getStringArrayListExtra("MESSAGE");
                waypointInfo = new ArrayList();

                waypointInfo.add(0, message.get(0));
                waypointInfo.add(1, message.get(1));
                waypointInfo.add(2, message.get(2));
                waypointInfo.add(3, message.get(3));

                waypointsList.add(waypointInfo);

                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
