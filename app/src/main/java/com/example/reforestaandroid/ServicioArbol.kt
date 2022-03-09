package com.example.reforestaandroid

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServicioArbol {

    @GET("api/arbol")
    fun obtenerTodos(): Call<List<Arbol>>

    @POST("api/arbol")
    fun guardarArbol(@Body arbol:Arbol):Call<Estatus>
}