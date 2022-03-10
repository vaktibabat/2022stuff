package com.example.soundsender

import android.app.Activity
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.media.MediaRecorder.AudioSource.MIC
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import java.io.File

class MicActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mic)

        val startRec: Button = findViewById(R.id.startRec)
        val stopRec: Button = findViewById(R.id.stopRec)

        val mediaRecorder: MediaRecorder = MediaRecorder()
        val outputFile: File = File(this.filesDir, "NEW.mp4")

        mediaRecorder.setAudioSource(MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        mediaRecorder.setOutputFile(outputFile)
        mediaRecorder.prepare()

        startRec.setOnClickListener {
            mediaRecorder.start()
        }

        stopRec.setOnClickListener {
            mediaRecorder.stop()

            var fileData: String = ""
            var fileData2: String = ""

            outputFile.forEachLine {
                fileData += it
                fileData2 += it

            }

            val byteArray = fileData.toByteArray()

            fileData = Base64.encodeToString(byteArray, Base64.DEFAULT)

            Log.i("DATA", fileData)
            Log.i("DATA", fileData2)
            Log.i("FILESDIR", this.filesDir.toString())
        }


    }
}