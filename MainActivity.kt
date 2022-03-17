package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayButton = findViewById<Button>(R.id.displayAct)
        val addButton = findViewById<Button>(R.id.AddAct)

        displayButton.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }

        addButton.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }
    }
}