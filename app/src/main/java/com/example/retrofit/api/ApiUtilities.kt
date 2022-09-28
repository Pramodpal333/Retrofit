package com.example.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtilities {
    val BASE_URL = "https://api.github.com"

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}