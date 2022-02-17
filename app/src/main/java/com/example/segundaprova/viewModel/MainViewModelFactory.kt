package com.example.segundaprova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.segundaprova.repository.EstadoRemoteRepository

class MainViewModelFactory(private val repository: EstadoRemoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EstadoRemoteViewModel(repository) as T
    }
}

