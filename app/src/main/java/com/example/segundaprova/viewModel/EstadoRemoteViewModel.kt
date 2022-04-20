package com.example.segundaprova.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.segundaprova.fragments.detalhe.DetalhesViewModel
import com.example.segundaprova.model.Estado
import com.example.segundaprova.repository.EstadoRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class EstadoRemoteViewModel(private val repository: EstadoRemoteRepository) : ViewModel() {
//
//    val myResponse: MutableLiveData<List<Estado>> = MutableLiveData()
//    //var list : LiveData<List<Estado>> = repository.list.asLiveData()
//
//    fun getEstado() {
//        viewModelScope.launch(Dispatchers.IO) {
//            viewModelScope.launch {
//                val response: List<Estado> = repository.getEstado()
//                myResponse.value = response
//            }
//        }
//    }

}
