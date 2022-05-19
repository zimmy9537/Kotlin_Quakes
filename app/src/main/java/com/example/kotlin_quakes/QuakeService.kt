package com.example.kotlin_quakes

import com.example.kotlin_quakes.data.QuakeBase
import retrofit2.http.GET

interface QuakeService {

    @GET("query?format=geojson&orderby=time&minmag=5&limit=10")
    suspend fun getQuakes(): QuakeBase
}