package com.example.segundaprova.fragments.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.segundaprova.R
import com.example.segundaprova.databinding.CustomRowBinding
import com.example.segundaprova.model.Estado

class ListAdapter :
    androidx.recyclerview.widget.ListAdapter<Estado, ListAdapter.ListViewHolder>(EstadoDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.bind(currentItem)
    }


    override fun getItemId(position: Int): Long {
        var id = currentList[position].id
        Log.i("idList", "$id")

        return id

    }

    class ListViewHolder private constructor(var binding: CustomRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ListViewHolder {
                val binding: CustomRowBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.custom_row,
                    parent,
                    false
                )

                return ListViewHolder(binding)
            }
        }


        fun bind(currentItem: Estado) {
            binding.estado = currentItem

        }


    }

    class EstadoDiffCallBack : DiffUtil.ItemCallback<Estado>() {
        override fun areItemsTheSame(oldItem: Estado, newItem: Estado): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Estado, newItem: Estado): Boolean {
            return oldItem == newItem
        }

    }
}


