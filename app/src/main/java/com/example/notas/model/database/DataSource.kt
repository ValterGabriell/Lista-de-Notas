package com.example.notas.model.database

import com.example.notas.model.modeladores.NotasModel

interface DataSource {
    fun addNote(notasModel: NotasModel)
    fun updateNoteById(id:Long, titulo:String, des:String, color:Int)
    fun getAllNotes() : List<NotasModel>
    fun getNoteById(id: Long) : NotasModel
    fun deleteAll(notasModel: NotasModel)
    fun deleteById(id: Long)

}