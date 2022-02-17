package com.example.segundaprova.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.segundaprova.model.Estado

@Dao
interface EstadoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(estado: Estado)

    @Delete
    fun delete(estado: Estado)

    @Update
    fun update(estado: Estado)

    @Query("SELECT * FROM Estado")
    fun listAll(): LiveData<List<Estado>>

    @Query("SELECT * FROM estado WHERE id = :id ")
    fun listEstadoById(id : Long) : LiveData<Estado>

    @Query("DELETE FROM estado WHERE id = :id ")
    fun deleteEstadoById(id : Long)
}