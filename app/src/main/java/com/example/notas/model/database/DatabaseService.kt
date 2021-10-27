package com.example.notas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notas.model.entidades.NotasEntity

@Database(entities = [NotasEntity::class], version = 1)
abstract class DatabaseService : RoomDatabase(){
    companion object{
        private const val DATABASE_NAME = "database"
        private var INSTANCE : DatabaseService? = null

        private fun createDatabase(context: Context) : DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .allowMainThreadQueries().build()

        fun getInstanceDatabase(context: Context) : DatabaseService =
            (INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            })
    }

    abstract fun fetchDao() : NotasDAO

}