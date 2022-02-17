package com.example.segundaprova.fragments.altera

import android.app.Application
import androidx.lifecycle.*
import com.example.segundaprova.db.AppDatabase
import com.example.segundaprova.fragments.detalhe.DetalhesViewModel
import com.example.segundaprova.model.Estado
import com.example.segundaprova.repository.EstadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlteraViewModel(application: Application, id: Long) :AndroidViewModel(application) {

    lateinit var estado : LiveData<Estado>

    private val repository: EstadoRepository


    init {
        val estadoDao = AppDatabase.getDatabase(application).estadoDao()
        repository = EstadoRepository(estadoDao)

        viewModelScope.launch(Dispatchers.IO) {
            estado = repository.listEstadoById(id)
        }
    }

    fun alterarEstado(){
        viewModelScope.launch(Dispatchers.IO) {
           repository.updateEstado(estado.value!!)
        }

    }

    class AlteraFragmentViewModelFactory(val application: Application, val id:Long) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlteraViewModel::class.java)) {
                return AlteraViewModel(application, id) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}