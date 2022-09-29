package com.example.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
   private val BASE_UR = "https://api.github.com"

    fun getSome(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_UR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}