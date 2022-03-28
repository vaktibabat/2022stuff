package com.example.eulersphiwebnew

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class HttpActivity : Activity() {
    private fun post(n: Int): String? {
        val url: String = "http://192.168.88.150:3000/eulers_phi"
        val mURL = URL(url)
        val connection = mURL.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.connectTimeout = 300000
        connection.doOutput = true

        val postData = "n=$n".toByteArray(StandardCharsets.UTF_8)

        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty("Content-Length", postData.size.toString())
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")

        try {
            val outputStream = DataOutputStream(connection.outputStream)
            outputStream.write(postData)
            outputStream.flush()
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }

        try {
            val inputStream = DataInputStream(connection.inputStream)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var output = reader.readLine()

            output = output.replace(n.toString(), "")

            output = output.filter { it.isDigit() }

            return output
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }

        return "-1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        val number = findViewById<EditText>(R.id.num)
        val send = findViewById<Button>(R.id.send)
        val result = findViewById<TextView>(R.id.response)

        send.setOnClickListener {
            val r = Runnable {
                val res = post(Integer.parseInt(number.text.toString()))

                Log.i("Result", res.toString())
            }

            val t = Thread(r)
            t.start()
        }
    }
}