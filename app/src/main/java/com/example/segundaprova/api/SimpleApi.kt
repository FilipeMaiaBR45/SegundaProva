package com.example.segundaprova.api

import com.example.segundaprova.model.Estado
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface SimpleApi {

    @GET("/api/v1/state/")
    suspend fun getPost() : List<Estado>

}