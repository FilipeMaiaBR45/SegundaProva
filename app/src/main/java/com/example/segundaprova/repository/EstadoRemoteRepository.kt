package com.example.segundaprova.repository

import androidx.lifecycle.asLiveData
import com.example.segundaprova.api.RetrofitInstance
import com.example.segundaprova.api.SimpleApi
import com.example.segundaprova.model.Estado
import com.example.segundaprova.model.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EstadoRemoteRepository {
    //var list : Flow<List<Estado>> = RetrofitInstance.api.getPost()
    private val refreshIntervalMs: Long = 5000

//    suspend fun getEstadoRemote(): Flow<List<Estado>> {
//        var list: Flow<List<Estado>> = RetrofitInstance.api.getPost()
//        list.asLiveData()
//        return list
//    }

//    suspend fun getEstado() : Flow<List<Estado>> {
//        return flow {
//            emit(RetrofitInstance.api.getPost())
//        }
//
//    }

    val estados : Flow<List<Estado>> = flow {
        while (true){
            val estados = RetrofitInstance.api.getPost()
            emit(estados)
            delay(refreshIntervalMs)
        }
    }

}