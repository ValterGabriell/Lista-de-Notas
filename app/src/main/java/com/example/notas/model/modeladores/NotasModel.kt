package com.example.notas.model.modeladores

data class NotasModel(
    val id: Long = 0L,
    var titulo: String = "",
    var descricao: String = "",
    var color: Int = 0
)