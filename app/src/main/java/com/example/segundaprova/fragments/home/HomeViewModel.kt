package com.example.segundaprova.fragments.home

import androidx.lifecycle.*
import com.example.segundaprova.model.Estado
import com.example.segundaprova.repository.EstadoRemoteRepository
import com.example.segundaprova.repository.EstadoRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(repositoryRemote: EstadoRemoteRepository, repositoryLocal : EstadoRepository) : ViewModel() {

    val repositoryLocal = repositoryLocal
    val repositoryRemote = repositoryRemote

    //var listRemote : LiveData<List<Estado>> = repositoryRemote.getEstado().asLiveData()

    private val _listRemote: MutableLiveData<List<Estado>> = MutableLiveData()
    val listRemote : LiveData<List<Estado>> = _listRemote

    //var list : LiveData<List<Estado>> = repository.list.asLiveData()

    fun getEstadoRemote() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch {
                 repositoryRemote.getEstado().collect {
                     _listRemote.value = it
                 }
            }
        }
    }

    var listLocal : LiveData<List<Estado>> = repositoryLocal.realAllData.asLiveData()

    fun addEstado(estado: Estado) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.addEstado(estado)
        }
    }

    fun addEstadoRemote(estado: List<Estado>) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.addEstadoRemote(estado)
        }
    }

    fun updateEstado(estado: Estado) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.updateEstado(estado)
        }
    }



    fun deletEstadoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.deleteEstadoById(id)
        }


    }


//     fun listEstadoRemote(repositoryRemote : EstadoRemoteRepository) {
//      viewModelScope.launch(Dispatchers.IO) {
//           viewModelScope.launch {
//               listRemote = repositoryRemote.getEstadoRemote().asLiveData()
//           }
//       }
//    }


//
//    fun listEstadoLocal(repositoryLocal: EstadoRepository ) {
//        viewModelScope.launch(Dispatchers.IO) {
//            viewModelScope.launch {
//                listLocal = repositoryLocal.getEstadoLocal().asLiveData()
//            }
//        }
//    }






 class Factory(val repositoryRemote: EstadoRemoteRepository, val repopsitoryLocal : EstadoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(repositoryRemote, repopsitoryLocal) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}