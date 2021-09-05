package com.example.roomdata.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomdata.R
import com.example.roomdata.databinding.ActivityAddBinding
import com.example.roomdata.model.Note
import com.example.roomdata.viewmodel.NoteViewModel

class AddActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy{
        ViewModelProvider(this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_add)
            binding.viewModel = noteViewModel
            binding.lifecycleOwner = this

        binding.btaddNote.setOnClickListener {
            noteViewModel.insertNote(Note(binding.ttNote.text.toString(),binding.dtNote.text.toString()))
            finish()
        }
        binding.btCancel.setOnClickListener {
            finish()
        }

    }
}