package com.example.countdowntimer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountdownActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown_layout);

        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText date = (EditText) findViewById(R.id.editTextDate);
                EditText days = (EditText) findViewById(R.id.days);

                Log.i("DEBUG", date.getText().toString());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date dateVal = sdf.parse(date.getText().toString());

                    long diff = dateVal.getTime() - System.currentTimeMillis();

                    Log.i("DEBUG", "Days till date: " + diff / 86400000);

                    days.setText((CharSequence) Long.toString(diff / 86400000));
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }


            }
        }
        );
    }


}
