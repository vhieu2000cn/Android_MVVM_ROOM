package com.example.roomdata.interfaceNote

import android.view.View
import com.example.roomdata.model.Note

interface SendData {
    fun sendData(view: View, note: Note)
    fun updateData(view: View,note: Note)
}