package com.example.segundaprova.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.segundaprova.db.AppDatabase
import com.example.segundaprova.model.Estado
import com.example.segundaprova.repository.EstadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstadoViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Estado>>
    //var currentItem : LiveData<Estado>()

    private var _currentItem = MutableLiveData<Estado>(Estado(0,"","","",0.0F,0,0))
    var currentItem: LiveData<Estado> = _currentItem



    private val repository: EstadoRepository



    init {
        val estadoDao = AppDatabase.getDatabase(application).estadoDao()
        repository = EstadoRepository(estadoDao)
        readAllData = repository.realAllData


    }

    fun addEstado(estado: Estado) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEstado(estado)
        }
    }

    fun updateEstado(estado: Estado) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEstado(estado)
        }
    }

    fun listEstadoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentItem.value = repository.listEstadoById(id)
        }


    }

    fun deletEstadoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEstadoById(id)
        }


    }
}