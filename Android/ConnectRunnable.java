package com.example.helloworld;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import android.widget.TextView;
import android.view.View;

public class ConnectRunnable extends Activity implements  Runnable {
    private String _command;

    public ConnectRunnable(String command)
    {
        _command = new String(command);
    }

    public void run()
    {
        try {
            Socket s = new Socket("192.168.88.150", 4444);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            dos.writeUTF(_command);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String tmp = null;

            while ((tmp = in.readLine()) != null) {
                Log.i("Command output: ", tmp);
            }

            s.close();
        }
        catch (IOException e)
        {
            Log.e("Error", e.getMessage());
        }
    }
}
