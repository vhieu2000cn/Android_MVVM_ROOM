package com.example.roomdata.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdata.R
import com.example.roomdata.databinding.ItemNoteRcvBinding
import com.example.roomdata.interfaceNote.SendData
import com.example.roomdata.model.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private lateinit var mSendData: SendData
    fun setOnItemClick(sendData: SendData){
        mSendData = sendData
    }

    var listNote: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class NoteViewHolder(
        private val noteItemBinding: ItemNoteRcvBinding
        ): RecyclerView.ViewHolder(noteItemBinding.root){
            fun onBind(note: Note){
                noteItemBinding.note = note

                noteItemBinding.btdelete.setOnClickListener {
                    mSendData.sendData(it,note)
                }
                noteItemBinding.itemNote.setOnClickListener {
                    mSendData.updateData(it,note)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemNoteRcvBinding = ItemNoteRcvBinding.inflate(inflater,parent,false)
        return NoteViewHolder(itemNoteRcvBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(listNote[position])
    }

    override fun getItemCount(): Int = listNote.size
}