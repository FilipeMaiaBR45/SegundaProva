package com.example.segundaprova.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.segundaprova.dao.EstadoDao
import com.example.segundaprova.model.Estado

class EstadoRepository(private val estadoDao : EstadoDao) {

    val realAllData : LiveData<List<Estado>> = estadoDao.listAll()

    suspend fun addEstado (estado: Estado){
        estadoDao.insert(estado)
    }

    suspend fun updateEstado( estado: Estado){
        estadoDao.update(estado)
    }

    suspend fun listEstadoById (id : Long) : LiveData<Estado> {
       return estadoDao.listEstadoById(id)
    }

    suspend fun deleteEstadoById (id : Long){
        return estadoDao.deleteEstadoById(id)
    }

}