package com.example.notes

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Button
import android.widget.EditText

object Note {
    object NoteEntry : BaseColumns {
        const val TABLE_NAME = "notes"
        const val COLUMN_NAME_TITLE = "notesTitle"
        const val COLUMN_NAME_SUBTITLE = "notesSubtitle"
    }
}

const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${Note.NoteEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${Note.NoteEntry.COLUMN_NAME_TITLE} TEXT," +
            "${Note.NoteEntry.COLUMN_NAME_SUBTITLE} TEXT)"

const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Note.NoteEntry.TABLE_NAME}"

class NoteDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Notes.db"
    }
}

class NotesActivity : Activity() {
    private fun createNewNote(text: String, id: String) {
        val dbHelper = NoteDbHelper(this)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(Note.NoteEntry.COLUMN_NAME_TITLE, text)
            put(Note.NoteEntry.COLUMN_NAME_SUBTITLE, id)
        }

        db?.insert(Note.NoteEntry.TABLE_NAME, null, values)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val submitButton = findViewById<Button>(R.id.newNote)
        val textBox = findViewById(R.id.textBox) as EditText
        val idBox = findViewById(R.id.idBox) as EditText

        submitButton.setOnClickListener {
            val text = textBox.text.toString()
            val id = idBox.text.toString()


            Log.i("NOTE", "Creating new note with text $text and id $id")
            createNewNote(text, id)
        }
    }
}