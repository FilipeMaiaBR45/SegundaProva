package com.example.segundaprova.fragments.detalhe

import android.app.Application
import androidx.lifecycle.*
import com.example.segundaprova.db.AppDatabase
import com.example.segundaprova.model.Estado
import com.example.segundaprova.repository.EstadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalhesViewModel(application: Application, id : Long) : AndroidViewModel(application) {

    lateinit var estado: LiveData<Estado>

    var id : Long = 0

    private val repository: EstadoRepository


    init {
        val estadoDao = AppDatabase.getDatabase(application).estadoDao()
        repository = EstadoRepository(estadoDao)

        viewModelScope.launch(Dispatchers.IO) {
            estado = repository.listEstadoById(id)
        }
    }

    class DetalhesFragmentViewModelFactory(val application: Application, val id:Long) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetalhesViewModel::class.java)) {
                return DetalhesViewModel(application, id) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}