package com.example.roomdata.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomdata.R
import com.example.roomdata.databinding.ActivityAddBindingImpl
import com.example.roomdata.databinding.ActivityMainBindingImpl
import com.example.roomdata.databinding.ActivityUpdateBinding
import com.example.roomdata.model.Note
import com.example.roomdata.viewmodel.NoteViewModel

class UpdateActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy{
        ViewModelProvider(this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUpdateBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_update)

        val note = intent.extras?.getSerializable("note") as Note
        binding.ttNoteUpdate.setText(note.title)
        binding.dtNoteUpdate.setText(note.depcription)

        binding.updateNote.setOnClickListener {
            note.title = binding.ttNoteUpdate.text.toString()
            note.depcription = binding.dtNoteUpdate.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }

    }
}