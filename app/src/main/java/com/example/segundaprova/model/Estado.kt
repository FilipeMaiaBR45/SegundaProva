package com.example.segundaprova.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
 data class Estado(
   @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var unidadeFederativa: String,
    var abreviacao : String,
    var capital : String,
    var area : Float,
    var populacao : Long,
    var pib : Long
    ) : Parcelable