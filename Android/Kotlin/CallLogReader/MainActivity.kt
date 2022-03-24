package com.example.callreader

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.number.IntegerWidth
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.CallLog
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


const val READ_CALL_LOG_CODE = 100

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start = findViewById<Button>(R.id.start)
        val readLogPerm = findViewById<Button>(R.id.logPermission)

        start.setOnClickListener {
            val intent = Intent(this, CallReader::class.java)
            startActivity(intent)
        }

        readLogPerm.setOnClickListener {
            checkPermission(Manifest.permission.READ_CALL_LOG, READ_CALL_LOG_CODE)
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

        when (requestCode) {
            READ_CALL_LOG_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera Permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Camera permission not granted", Toast.LENGTH_SHORT).show()
                }
        }
    }
}