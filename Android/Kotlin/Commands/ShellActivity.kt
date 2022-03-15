package com.example.remoteshell

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class ShellActivity : Activity() {
    private fun startShell() {
        val r = Runnable {
            val serverSock = ServerSocket(4444)
            val s: Socket = serverSock.accept()

            while (true) {
                val dataInputStream = DataInputStream(s.getInputStream())
                val command = dataInputStream.readUTF().toString()

                if (command.trim().lowercase() == "exit")
                {
                    s.close()
                    break
                }

                Log.i("DEBUG", command)

                val p: Process = Runtime.getRuntime().exec(command)

                val out = BufferedReader(InputStreamReader(p.inputStream))
                val clientOutput = s.getOutputStream()
                val writer = PrintWriter(clientOutput, true)

                var tmp: String = out.readLine()

                while (tmp != null) {
                    tmp = out.readLine()

                    Log.i("DEBUG", tmp)
                    writer.run { println(tmp) }

                    continue
                }
            }
        }

        val thread = Thread(r)
        thread.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shell)

        val start: Button = findViewById(R.id.startShell)

        start.setOnClickListener {
            startShell()
        }
    }
}