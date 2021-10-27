package com.example.notas.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.R
import com.example.notas.model.modeladores.NotasModel

class AdapterNotas(  val lista: ArrayList<NotasModel>) :
    RecyclerView.Adapter<AdapterNotas.ViewHolderAdapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cire, parent, false)
        return ViewHolderAdapter(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAdapter, position: Int) {
        val notasModel = lista[position]
        holder.let {
            it.titulo.text = notasModel.titulo
            it.des.text = notasModel.descricao
            it.linear.setBackgroundColor(notasModel.color)
        }

        holder.itemView.tag = lista[position].id

    }

    override fun getItemCount(): Int = lista.size

    class ViewHolderAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val linear = itemView.findViewById<LinearLayout>(R.id.linearBack)
        val titulo = itemView.findViewById<TextView>(R.id.txtTitulo)
        val des = itemView.findViewById<TextView>(R.id.txtDescricao)
    }


}