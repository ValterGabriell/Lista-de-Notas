package com.example.notas.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notas.model.entidades.NotasEntity

@Dao
interface NotasDAO {
    @Insert
    fun addNote(notasEntity: NotasEntity)

    @Query("UPDATE tabela_de_notas SET id = :id, titulo = :titulo, descricao = :descricao, color =:color")
    fun updateNoteById(id:Long, titulo:String, descricao:String, color:Int)

    @Query("SELECT * FROM tabela_de_notas")
    fun getAllNotes() : List<NotasEntity>

    @Query("SELECT * FROM tabela_de_notas WHERE id = :id")
    fun getNoteById(id: Long) : NotasEntity

    @Query("DELETE FROM tabela_de_notas")
    fun deleteAll()

    @Query("DELETE FROM tabela_de_notas WHERE id=:id")
    fun deleteById(id: Long)

}