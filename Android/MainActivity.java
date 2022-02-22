package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends  AppCompatActivity implements View.OnClickListener
{

    private Button buttonStartReceiving;
    private boolean end = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartReceiving = (Button) findViewById(R.id.startRecieving);

        buttonStartReceiving.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.startRecieving) {
             Log.i("Button pressed. ", "Starting to listen...");

             Intent intent = new Intent(this, ClientActivity.class);
             startActivity(intent);
        }
    }
}
