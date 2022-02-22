package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.StrictMode;

import java.io.*;
import java.net.*;

public class ClientActivity extends Activity implements View.OnClickListener
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.client_layout);

        Button submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.submit)
        {
            EditText editText = (EditText) findViewById(R.id.CommandBox);

            String command = editText.getText().toString();

            ConnectRunnable connectRunnable = new ConnectRunnable(command);

            Thread t = new Thread(connectRunnable);

            t.start();
        }
    }
}
