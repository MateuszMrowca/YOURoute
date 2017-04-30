package com.example.mateusz.youroutev1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Waypoints extends AppCompatActivity
{

    private Button showDialogButton, displayAllWaypoits;
    String[] methods = {"Enter Coordinates","Enter Address","From Map","From Photo"};
    String whichMethod="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waypoints);



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
                            Intent intent = new Intent(Waypoints.this, add_by_coordinates_activity.class);
                            startActivity(intent); // get results and pass on to create a waypoint
                        }
                        else if(methods[i].equals("Enter Address"))
                        {
                            Intent intent = new Intent(Waypoints.this, add_by_address_activity.class);
                            startActivity(intent);
                        }
                        else if(methods[i].equals("From Map"))
                        {
                            Intent intent = new Intent(Waypoints.this, add_by_map_click_activity.class);
                            startActivity(intent);
                        }
                        else if(methods[i].equals("From Photo"))
                        {
                            Intent intent = new Intent(Waypoints.this, add_from_photo_activity.class);
                            startActivity(intent);
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
                Intent intent = new Intent(Waypoints.this, displayAllWaypoints.class);
                startActivity(intent);
            }
        });
    }
}
