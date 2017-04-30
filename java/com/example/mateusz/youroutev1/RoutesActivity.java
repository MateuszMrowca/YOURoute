package com.example.mateusz.youroutev1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RoutesActivity extends AppCompatActivity
{
    private Button startRouteButton, loadRouteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        startRouteButton = (Button) findViewById(R.id.createRoute);
        startRouteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO - add a get route name dialog box with a default value "route 1"
                Intent intent = new Intent(RoutesActivity.this, RecordRoute.class);
                startActivity(intent);
            }
        });

        loadRouteButton = (Button) findViewById(R.id.loadRoute);
        loadRouteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RoutesActivity.this, LoadRoute.class);
                startActivity(intent);
            }
        });


    }

}
