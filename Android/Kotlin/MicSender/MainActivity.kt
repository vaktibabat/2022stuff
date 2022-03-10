package com.example.soundsender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val RECORD_AUDIO_CODE = 100

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start: Button = findViewById(R.id.start)
        val audioPerm: Button = findViewById(R.id.recordPerm)

        start.setOnClickListener {
            val intent = Intent(this, MicActivity::class.java)
            startActivity(intent)
        }

        audioPerm.setOnClickListener {
            checkPermission(Manifest.permission.RECORD_AUDIO, RECORD_AUDIO_CODE)
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
            RECORD_AUDIO_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera Permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Camera permission not granted", Toast.LENGTH_SHORT).show()
                }
        }
    }
}