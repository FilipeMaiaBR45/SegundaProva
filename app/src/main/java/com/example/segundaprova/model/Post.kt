package com.example.segundaprova.model

data class Post (
    var id: Long = 0,
    var unidadeFederativa: String,
    var abreviacao : String,
    var capital : String,
    var area : Float,
    var populacao : Long,
    var pib : Long
    )