package com.example.notes

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DisplayActivity : Activity() {
    fun getNoteById(id: String): String {
        val dbHelper: NoteDbHelper = NoteDbHelper(this)
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Note.NoteEntry.COLUMN_NAME_TITLE, Note.NoteEntry.COLUMN_NAME_SUBTITLE)

        val selection = "${Note.NoteEntry.COLUMN_NAME_SUBTITLE} = ?"
        val selectionArgs = arrayOf(id)
        val orderBy = "${Note.NoteEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = db.query(
            Note.NoteEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            orderBy
        )

        val itemIds = mutableListOf<String>()
        with(cursor) {
            while(moveToNext()) {
                val itemId = getString(1)
                itemIds.add(itemId)

                Log.i("aaa", itemId)

                return itemId
            }
        }

        return "Failed"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val displayButton = findViewById<Button>(R.id.display)
        val id = findViewById<EditText>(R.id.Id)

        displayButton.setOnClickListener {
            val noteText = getNoteById(id.text.toString())

            Toast.makeText(this, noteText, Toast.LENGTH_LONG).show()
        }
    }
}