package com.example.segundaprova.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.segundaprova.R
import com.example.segundaprova.model.Estado

class ListAdapter() : RecyclerView.Adapter<ListViewHolder>() {

    private var estadoList = emptyList<Estado>()

    var estadoListByid : LiveData<Estado>?

    init {
        estadoListByid = null
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return ListViewHolder(view)
    }
    override fun getItemCount(): Int {
        return estadoList.size
    }




    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = estadoList[position]
        holder.textViewCapital.text = currentItem.capital
        holder.textViewUf.text = currentItem.unidadeFederativa
        holder.textViewAbreviacao.text = currentItem.abreviacao

//
        holder.rowLayout.setOnClickListener {
             val action = HomeFragmentDirections.actionHomeFragmentToAlteraFragment(currentItem)
             holder.itemView.findNavController().navigate(action)

        }

    }


    fun setData(estado : List<Estado>){
        this.estadoList = estado
        notifyDataSetChanged()
    }

    fun setEstadoListById(estado : LiveData<Estado>?){
        this.estadoListByid = estado
        notifyDataSetChanged()

    }
}


