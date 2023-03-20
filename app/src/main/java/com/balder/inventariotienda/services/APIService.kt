package com.balder.inventariotienda.services

import com.balder.inventariotienda.models.Stock
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET("stock")
    fun getStockList(): Call<List<Stock>>

    @POST("stock")
    fun addStock(@Body stock: Stock): Call<Stock>

    @PUT("stock/{id}")
    fun updateStock(@Path("id") id: Int, @Body stock: Stock): Call<Stock>
}