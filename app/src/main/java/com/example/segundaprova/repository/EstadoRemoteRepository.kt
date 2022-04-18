package com.example.segundaprova.repository

import com.example.segundaprova.api.RetrofitInstance
import com.example.segundaprova.api.SimpleApi
import com.example.segundaprova.model.Estado
import com.example.segundaprova.model.Post
import kotlinx.coroutines.flow.Flow

class EstadoRemoteRepository {

var list : Flow<List<Estado>> = RetrofitInstance.api.getPost()

    suspend fun getEstado() : Flow<List<Estado>> {
        return list
    }
}