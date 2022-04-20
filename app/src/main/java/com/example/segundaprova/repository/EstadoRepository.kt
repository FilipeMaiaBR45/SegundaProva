package com.example.segundaprova.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.segundaprova.api.RetrofitInstance
import com.example.segundaprova.dao.EstadoDao
import com.example.segundaprova.model.Estado
import kotlinx.coroutines.flow.Flow

class EstadoRepository(private val estadoDao : EstadoDao) {

    val realAllData : Flow<List<Estado>> = estadoDao.listAll()


    suspend fun addEstado (estado: Estado){
        estadoDao.insert(estado)
    }

    suspend fun addEstadoRemote (estado: List<Estado>){
        estadoDao.insertRemote(estado)
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