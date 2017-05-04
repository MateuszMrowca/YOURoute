package com.example.mateusz.youroutev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

//Source:<http://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android> Accessed: <01/05/2017>

public class add_by_coordinates_activity extends AppCompatActivity
{
    private EditText titleTV, descriptionTV, latTV, lonTV;
    private String titleString, descriptionString, latVal, lonVal;
    private Button addByCoordinates;
    private ArrayList<String> waypoint;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_by_coordinates_activity);

        titleTV = (EditText) findViewById(R.id.waypoint_title);
        descriptionTV = (EditText) findViewById(R.id.waypoint_snippet);
        latTV = (EditText) findViewById(R.id.latitudeEditText);
        lonTV = (EditText) findViewById(R.id.longitudeEditText);

        addByCoordinates = (Button) findViewById(R.id.add_by_coordinates_button);
        addByCoordinates.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                titleString = titleTV.getText().toString();
                descriptionString = descriptionTV.getText().toString();
                lonVal = lonTV.getText().toString();
                latVal = latTV.getText().toString();

                waypoint = new ArrayList<>();

                waypoint.add(titleString);
                waypoint.add(descriptionString);
                waypoint.add(latVal);
                waypoint.add(lonVal);


                Intent intent = new Intent();
                intent.putExtra("MESSAGE", waypoint);

                setResult(2, intent);

                finish();
            }
        });

    }
}
