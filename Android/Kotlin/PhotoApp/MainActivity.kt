package com.example.camerasender

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest

const val CAMERA_CODE: Int = 100
const val STORAGE_READ_CODE: Int = 101
const val STORAGE_WRITE_CODE: Int = 102

class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.start)
        val camButton: Button = findViewById(R.id.camPerm)
        val readButton: Button = findViewById(R.id.readButton)
        val writeButton: Button = findViewById(R.id.writePerm)

        startButton.setOnClickListener{
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        camButton.setOnClickListener{
            checkPermission(Manifest.permission.CAMERA, CAMERA_CODE)
        }

        readButton.setOnClickListener{
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_READ_CODE)
        }

        writeButton.setOnClickListener {
            checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_WRITE_CODE)
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

        when (requestCode)
        {
            CAMERA_CODE->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Camera Permission granted", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Camera permission not granted", Toast.LENGTH_SHORT).show()
                }
            STORAGE_READ_CODE->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Storage Read Permission granted", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Storage Read permission not granted", Toast.LENGTH_SHORT).show()
                }
            STORAGE_WRITE_CODE->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Storage Write Permission granted", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Storage Write permission not granted", Toast.LENGTH_SHORT).show()
                }
        }
    }
}