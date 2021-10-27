package com.example.notas.model.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notas.model.modeladores.NotasModel

@Entity(tableName = "tabela_de_notas")
class NotasEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @ColumnInfo(name = "titulo")
    val titulo:String = "",
    @ColumnInfo(name = "descricao")
    val descricao:String = "",
    @ColumnInfo(name = "color")
    val color : Int = 0
        ){

    fun toEntityFromModel(notasModel: NotasModel) = NotasEntity(
        notasModel.id, notasModel.titulo, notasModel.descricao, notasModel.color
    )

    fun getAllNotes() = NotasModel(id, titulo, descricao, color)



}