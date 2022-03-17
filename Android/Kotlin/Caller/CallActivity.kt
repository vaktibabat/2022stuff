package com.example.caller

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CallActivity : Activity() {
    private fun spamCall(phoneNumber: String) {
        val phoneIntent = Intent(Intent.ACTION_CALL)

        phoneIntent.data = Uri.parse("tel:$phoneNumber")

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PERMISSION_GRANTED) return

        startActivity(phoneIntent)

        Log.i("CALL", "CALLED")

        val phoneStateListener = PhoneStateListener()

        val telephonyManager: TelephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)

        var prev: String = telephonyManager.callState.toString()
        var curr: String = telephonyManager.callState.toString()

        while (true)
        {
            prev = curr
            curr = telephonyManager.callState.toString()

            if (prev == "2" && curr == "0")
            {
                startActivity(phoneIntent)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val startCallButton: Button = findViewById(R.id.startCall)
        val numberBox: EditText = findViewById(R.id.numberBox)

        startCallButton.setOnClickListener {
            val number = numberBox.text.toString()

            Log.i("NUMBER", number)

            spamCall(number)
        }

    }
}