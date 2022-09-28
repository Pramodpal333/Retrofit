package com.example.retrofit.api

import com.example.retrofit.model.Users
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/users")
    suspend fun getUsers() = Response<Users>()
}