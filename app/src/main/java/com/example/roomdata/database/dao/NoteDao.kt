package com.example.roomdata.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdata.model.Note
import kotlinx.coroutines.selects.select

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)
    @Update
    suspend fun updateNote(note: Note)
    @Delete
    suspend fun deleteNote(note: Note)
    @Query("select * from note_table ")
    fun getAllNote(): LiveData<List<Note>>
    @Query("select * from note_table where id_note=:id")
    fun getNote(id: Int): LiveData<Note>
}