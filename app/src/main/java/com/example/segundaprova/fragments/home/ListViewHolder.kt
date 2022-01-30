package com.example.segundaprova.fragments.home

import android.text.Layout
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.segundaprova.R

class ListViewHolder(v : View) : RecyclerView.ViewHolder(v) {
    val textViewAbreviacao : TextView
    val textViewUf : TextView
    val textViewCapital : TextView
    val rowLayout : View
    //val reciclerView : RecyclerView

    init {
        textViewAbreviacao = v.findViewById(R.id.textViewAbreviacao)
        textViewUf = v.findViewById(R.id.textViewUf)
        textViewCapital = v.findViewById(R.id.textViewCapital)
        rowLayout = v.findViewById(R.id.rowLayout)
       // reciclerView = v.findViewById(R.id.recyclerview)
    }
}