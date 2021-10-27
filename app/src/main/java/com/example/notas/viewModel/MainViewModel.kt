package com.example.notas.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notas.model.database.RoomDataSource
import com.example.notas.model.modeladores.NotasModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val listaNotass = MutableLiveData<List<NotasModel>>()

    fun addNote(notasModel: NotasModel){
        RoomDataSource(getApplication()).addNote(notasModel)
        Log.i("add", notasModel.toString())
    }

    fun getAllNotes() {
             val listaAux = RoomDataSource(getApplication()).getAllNotes()
        listaNotass.postValue(listaAux)
            Log.i("tag", listaAux.toString())
    }

    fun deleteAll(notasModel: NotasModel){
        RoomDataSource(getApplication()).deleteAll(notasModel)
    }

    fun deleteById(id:Long){
        RoomDataSource(getApplication()).deleteById(id)
    }



}