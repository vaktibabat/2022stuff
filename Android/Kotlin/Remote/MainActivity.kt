package com.example.trojan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val READ_SMS_CODE: Int = 100

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.startButtonMain)
        val smsPermButton: Button = findViewById(R.id.smsPerm)

        startButton.setOnClickListener {
            val connectActivity = Intent(this, ConnectActivity::class.java)
            startActivity(connectActivity)
        }

        smsPermButton.setOnClickListener{
            checkPermission(Manifest.permission.READ_SMS, READ_SMS_CODE)
        }
    }

    private fun checkPermission(permission: String, requestCode: Int)
    {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }
        else
        {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == READ_SMS_CODE)
        {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "SMS permission not granted :(", Toast.LENGTH_SHORT).show()
            }
        }
    }
}