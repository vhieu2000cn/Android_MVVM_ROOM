package com.example.roomdata.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdata.R
import com.example.roomdata.adapter.NoteAdapter
import com.example.roomdata.databinding.ActivityMainBinding
import com.example.roomdata.interfaceNote.SendData
import com.example.roomdata.model.Note
import com.example.roomdata.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy{
        ViewModelProvider(this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_main)

        initRcv(binding)
        initFab(binding)
    }

    private fun initFab(binding: ActivityMainBinding) {
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRcv(binding: ActivityMainBinding) {
        val adapter = NoteAdapter()
        binding.rcvNote.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            setAdapter(adapter)
            addItemDecoration(object : DividerItemDecoration(context, VERTICAL) {})
        }
        noteViewModel.getAllNote().observe(this, Observer {
            adapter.listNote = it
        })
        adapter.setOnItemClick(object : SendData {
            override fun sendData(view: View, note: Note) {
                noteViewModel.deleteNote(note)
            }

            override fun updateData(view: View, note: Note) {
                val intent = Intent(this@MainActivity,UpdateActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("note",note)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }

}