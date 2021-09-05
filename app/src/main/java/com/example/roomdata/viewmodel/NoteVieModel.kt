package com.example.roomdata.viewmodel

import android.app.Activity
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.roomdata.database.repository.NoteRepository
import com.example.roomdata.databinding.ActivityAddBinding
import com.example.roomdata.model.Note
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(application: Application): ViewModel(){
    private val noteRepository = NoteRepository(application)

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }
    fun getNoteById(id: Int) = viewModelScope.launch {
        noteRepository.getNoteById(id)
    }
    fun getAllNote():LiveData<List<Note>> = noteRepository.getAllNote()

    class NoteViewModelFactory( private val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) return NoteViewModel(application) as T
            throw IllformedLocaleException("unable constructor viewmodel")
        }

    }
}