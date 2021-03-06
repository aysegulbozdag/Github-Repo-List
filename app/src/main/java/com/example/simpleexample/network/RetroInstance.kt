package com.example.simpleexample.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    //static
    companion object {
        val BaseUrl = "https://api.github.com/search/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
    }
}


