package com.example.simpleexample.network

import com.example.simpleexample.model.RecyclerListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("repositories")
    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListModel
}