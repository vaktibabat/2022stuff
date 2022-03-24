package com.example.callreader

import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.widget.Button
import android.widget.Toast

class CallReader : Activity() {
    private fun displayCalls() {
        val c = this.contentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC")

        val num = c!!.getColumnIndex(CallLog.Calls.NUMBER)
        val name = c.getColumnIndex(CallLog.Calls.CACHED_NAME)
        val duration = c.getColumnIndex(CallLog.Calls.DURATION)
        val type = c.getColumnIndex(CallLog.Calls.TYPE)

        while (c.moveToNext()) {
            val numS = c.getString(num)
            val nameS = c.getString(name)
            val durationS = c.getString(duration)
            val typeS = c.getString(type)

            Toast.makeText(this, "$numS\n$nameS\n$durationS\n$typeS", Toast.LENGTH_LONG).show()

            Thread.sleep(5000)
        }

        c.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callreader)

        val display = findViewById<Button>(R.id.display)

        display.setOnClickListener {
            displayCalls()
        }
    }
}