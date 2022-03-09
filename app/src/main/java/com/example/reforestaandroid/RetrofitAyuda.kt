package com.example.reforestaandroid

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitAyuda {
    fun obtenerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reforesta.herokuapp.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
}