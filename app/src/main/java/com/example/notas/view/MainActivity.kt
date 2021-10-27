package com.example.notas.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.R
import com.example.notas.databinding.ActivityMainBinding
import com.example.notas.model.modeladores.NotasModel
import com.example.notas.view.adapters.AdapterNotas
import com.example.notas.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    private var listaNotas = ArrayList<NotasModel>()
    private lateinit var adapterC : AdapterNotas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializacao()

    }

    private fun configRv(context: Context){
        adapterC = AdapterNotas(listaNotas)

        binding.rvNotas.apply {
            adapter = adapterC
           layoutManager = LinearLayoutManager(context)
        }

        val helper = ItemTouchHelper(TouchHelper(0, ItemTouchHelper.LEFT))
        helper.attachToRecyclerView(binding.rvNotas)

    }

    private fun addObervers(){
        model.listaNotass.observe(this, {
                listaNotas = it as ArrayList<NotasModel>
            configRv(this)
        })
    }

    override fun onStart() {
        super.onStart()
        clickFab()
    }

    override fun onResume() {
        super.onResume()
        model.getAllNotes()
        addObervers()
    }

    private fun clickFab() {
        binding.fabAddNote.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.layout_add_note, null)
            val alert = AlertDialog.Builder(this)
            val alertDialog = alert.create()
            alertDialog.apply {
                setView(view)
                show()
            }
            configView(view, alertDialog)
        }
    }

    private fun configView(view: View, alertDialog: AlertDialog) {
        view.run {
            val etTitulo = findViewById<EditText>(R.id.etTituloNota)
            val etDescricao = findViewById<EditText>(R.id.etDescricao)
            val btnSave = findViewById<Button>(R.id.btnSaveNote)
            val btnCor1 = findViewById<Button>(R.id.btnCor1)
            val btnCor2 = findViewById<Button>(R.id.btnCor2)
            val btnCor3 = findViewById<Button>(R.id.btnCor3)
            val btnCor4 = findViewById<Button>(R.id.btnCor4)

            setColors(btnCor1, view)
            setColors(btnCor2, view)
            setColors(btnCor3, view)
            setColors(btnCor4, view)

            btnSave.setOnClickListener {
                val colorDrawable = view.background as ColorDrawable
                val colorId = colorDrawable.color

                val notasModel = NotasModel()
                notasModel.apply {
                    titulo = etTitulo.text.toString()
                    descricao = etDescricao.text.toString()
                    color = colorId
                }
                model.addNote(notasModel)
                alertDialog.dismiss()
                onResume()
            }

        }
    }

    private fun setColors(button: Button, view: View) {
        button.setOnClickListener {
            val back = button.currentTextColor
            view.setBackgroundColor(back)
        }
    }

    private fun inicializacao() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item){
            val notasModel = NotasModel()
            model.deleteAll(notasModel)
            onResume()

        }
        return super.onOptionsItemSelected(item)
    }

    inner class TouchHelper(drag : Int, swipe:Int) : ItemTouchHelper.SimpleCallback(drag, swipe) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
           return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val id = viewHolder.itemView.tag
            model.deleteById(id as Long)
            adapterC.lista.removeAt(viewHolder.adapterPosition)
            adapterC.notifyItemRemoved(viewHolder.adapterPosition)
        }
    }

}