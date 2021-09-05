package com.example.roomdata.database.repository

import android.app.Application
import com.example.roomdata.database.NoteDatabase
import com.example.roomdata.database.dao.NoteDao
import com.example.roomdata.model.Note

class NoteRepository(application: Application) {
    private val noteDao: NoteDao
    init {
        noteDao = NoteDatabase.getInstance(application).getNoteDao()
    }

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun getNoteById(id: Int) = noteDao.getNote(id)
    fun getAllNote() = noteDao.getAllNote()
}