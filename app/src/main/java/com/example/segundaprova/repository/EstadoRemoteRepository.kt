package com.example.segundaprova.repository

import com.example.segundaprova.api.RetrofitInstance
import com.example.segundaprova.model.Estado
import com.example.segundaprova.model.Post

class EstadoRemoteRepository {

    suspend fun getEstado() : List<Estado> {
        return RetrofitInstance.api.getPost()
    }
}