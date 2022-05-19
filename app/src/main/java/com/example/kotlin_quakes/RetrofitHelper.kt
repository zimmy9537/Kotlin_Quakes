package com.example.kotlin_quakes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val BASE_URL =
        "https://earthquake.usgs.gov/fdsnws/event/1/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}