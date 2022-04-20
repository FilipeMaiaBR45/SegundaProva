package com.example.segundaprova

import android.app.Application
import com.example.segundaprova.db.AppDatabase
import com.example.segundaprova.repository.EstadoRemoteRepository
import com.example.segundaprova.repository.EstadoRepository

class SegundaProvaApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repositoryLocal by lazy { EstadoRepository(database.estadoDao()) }
    val repositoryRemote by lazy {
        EstadoRemoteRepository()
    }


}