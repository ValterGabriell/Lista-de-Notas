package com.example.filmestop.model.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.notas.model.database.DatabaseService
import com.example.notas.model.database.NotasDAO
import com.example.notas.model.entidades.NotasEntity
import com.example.notas.model.modeladores.NotasModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoritesDAOTest {



    private lateinit var database: DatabaseService
    private lateinit var dao: NotasDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseService::class.java
        ).allowMainThreadQueries().build()
        dao = database.fetchDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun addFavorite() {
        val modelEntity = NotasModel(5,"titulo", "123", 2)
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity))

        val allNotes = dao.getAllNotes()

        assertThat(allNotes.map { it.getAllNotes() }).contains(modelEntity)
    }

    @Test
    fun updateNoteById(){
        val modelEntity = NotasModel(5, "a", "a", 2)
        val modelEntity2 = NotasModel(6, "a", "a", 2)
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity))
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity2))

        dao.updateNoteById(5, "fsdf", "asd", 8)
        val allNotes = dao.getAllNotes()

        assertThat(allNotes.map{it.getAllNotes()}).doesNotContain(modelEntity)
    }

    @Test
    fun deleteAll(){
        val modelEntity = NotasModel(5, "a", "a", 2)
        val modelEntity2 = NotasModel(6, "a", "a", 2)
        val modelEntity3 = NotasModel(7, "a", "a", 2)
        val modelEntity4 = NotasModel(8, "a", "a", 2)

        dao.addNote(NotasEntity().toEntityFromModel(modelEntity))
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity2))
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity3))
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity4))

        dao.deleteAll()

        val allNotas = dao.getAllNotes()
        assertThat(allNotas.map { it.getAllNotes() }).doesNotContain(modelEntity)
    }

    @Test
    fun deleteById(){
        val modelEntity = NotasModel(5, "a", "a", 2)
        val modelEntity2 = NotasModel(6, "a", "a", 2)
        val modelEntity3 = NotasModel(7, "a", "a", 2)
        val modelEntity4 = NotasModel(8, "a", "a", 2)

        dao.addNote(NotasEntity().toEntityFromModel(modelEntity))
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity2))
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity3))
        dao.addNote(NotasEntity().toEntityFromModel(modelEntity4))

        dao.deleteById(7)

        val allNotas = dao.getAllNotes()
        assertThat(allNotas.map { it.getAllNotes() }).doesNotContain(modelEntity3)
    }

}