package com.example.mateusz.youroutev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button Routes, Waypoints;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Routes = (Button) findViewById(R.id.Routes);
        Routes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getBaseContext(), "Routes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RoutesActivity.class);
                startActivity(intent);
            }
        });

        Waypoints = (Button) findViewById(R.id.Waypoints);
        Waypoints.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getBaseContext(), "Waypoints", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Waypoints.class);
                startActivity(intent);
            }
        });


    }

}
