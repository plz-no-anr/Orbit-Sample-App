package com.plz.no.anr.orbitsampleapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/")
    suspend fun getGenderOfName(
        @Query("name") name: String,
    ): Response<NetworkModel>
}