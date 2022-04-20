package com.example.segundaprova.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.segundaprova.repository.EstadoRemoteRepository
import com.example.segundaprova.repository.EstadoRepository

class HomeViewModel(repository: EstadoRemoteRepository, repopsitoryLocal: EstadoRepository) : ViewModel() {
    class Factory {

    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}