package com.example.notas.model.database

import android.content.Context
import com.example.notas.model.entidades.NotasEntity
import com.example.notas.model.modeladores.NotasModel

class RoomDataSource (context: Context): DataSource {
    private val fetchDao = DatabaseService.getInstanceDatabase(context).fetchDao()

    override fun addNote(notasModel: NotasModel) {
        fetchDao.addNote(NotasEntity().toEntityFromModel(notasModel))
    }

    override fun updateNoteById(id: Long, titulo: String, des: String, color: Int) {
        fetchDao.updateNoteById(id, titulo, des, color)
    }

    override fun getAllNotes(): List<NotasModel>
    =fetchDao.getAllNotes().map {
                it.getAllNotes()
        }


    override fun getNoteById(id: Long): NotasModel {
        return fetchDao.getNoteById(id).getAllNotes()
    }

    override fun deleteAll(notasModel: NotasModel) {
        fetchDao.deleteAll()
    }

    override fun deleteById(id: Long) {
        fetchDao.deleteById(id)
    }
}