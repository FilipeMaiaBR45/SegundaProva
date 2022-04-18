package com.example.segundaprova.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.segundaprova.model.Estado
import com.example.segundaprova.repository.EstadoRemoteRepository

class homeViewModel(repository: EstadoRemoteRepository) : ViewModel() {
    var list : LiveData<List<Estado>> = repository.list.asLiveData()
}