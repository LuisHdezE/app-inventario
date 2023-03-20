package com.balder.inventariotienda.models

data class Stock (
    val id: Int,
    val codigo: String,
    val nombre: String,
    val costo: Double,
    val precio: Double,
    val existencia: Int,
    val conteo: Int // Nueva propiedad
)