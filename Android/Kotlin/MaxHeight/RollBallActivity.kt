package com.example.rollaball

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlin.math.sin

class RollBallActivity : Activity(), SensorEventListener {
    /*
    private lateinit var star: ImageView
    private lateinit var proximitySensor: Sensor
    //private lateinit var gyroSensor: Sensor
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rollaball)

        star = findViewById(R.id.imageView2) as ImageView

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
      //  gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        when (sensorEvent?.sensor) {
            proximitySensor-> {
                Log.i("SENSOR", "You are close to something!")
                    star.y += 100
            }
            /*
            gyroSensor-> {
                Log.i("SENSOR", "Phone Orientation changed!")
                star.y -= 100
            }
             */
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.i("SENSOR", "Sensor accuracy changed!")
    }

    override fun onResume() {
        super.onResume()

        proximitySensor.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }

        /*
        gyroSensor.also { gyro ->
            sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL)
        }
         */
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }
     */

    lateinit var sensorManager: SensorManager
    lateinit var accelerometerSensor: Sensor
    lateinit var gravitySensor: Sensor
    val gravity: Double = 8.07
    var maxHeight: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rollaball)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.i("Accuracy", "Changed")
    }

    override fun onSensorChanged(p0: SensorEvent) {
                val velocity = p0.values[0]

                val height = (velocity * velocity) * (sin(180.0) * sin(180.0)) / gravity - gravity * ((velocity * sin(180.0) / gravity) * (velocity * sin(180.0) / gravity)) / 2

                if (maxHeight < height)
                {
                    Log.i("HEIGHT", height.toString())
                    Toast.makeText(this, height.toString(), Toast.LENGTH_LONG).show()
                    maxHeight = height
                }
    }

    override fun onResume() {
        super.onResume()

        accelerometerSensor.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }

        /*
        gyroSensor.also { gyro ->
            sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL)
        }
         */
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }
}