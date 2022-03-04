package com.example.unknown;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class SmsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        Log.i("a", "a");

        Button start = (Button) findViewById(R.id.start);

        ContentResolver cr = this.getContentResolver();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
                int totalSMS;

                if (c != null)
                {
                    totalSMS = c.getCount();

                    if (c.moveToFirst())
                    {
                        for (int i = 0; i < totalSMS; ++i)
                        {
                            String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                            String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                            String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
                            Date dateFormat = new Date(Long.valueOf(smsDate));
                            String type;

                            switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE))))
                            {
                                case Telephony.Sms.MESSAGE_TYPE_INBOX:
                                    type = "Inbox";
                                    break;
                                case Telephony.Sms.MESSAGE_TYPE_SENT:
                                    type = "Sent";
                                    break;
                                case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                                    type = "Outbox";
                                    break;
                                default:
                                    type = "";
                                    break;
                            }

                            String message = "DATE\n" +
                                    dateFormat.toString() + "\n" +
                                    "NUMBER\n" +
                                    number + "\n" +
                                    "BODY\n" +
                                    body + "\n" +
                                    "TYPE\n" +
                                    type + "\n";

                            Log.i("MESSAGE", message);

                            Toast.makeText(SmsActivity.this, message, Toast.LENGTH_LONG).show();

                            c.moveToNext();
                        }
                    }

                    c.close();
                } else
                {
                    Log.i("MESSAGES", "No messages to show.");
                }

            }
        });
    }


}
