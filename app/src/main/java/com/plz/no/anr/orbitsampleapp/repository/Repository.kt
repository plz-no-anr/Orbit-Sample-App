package com.plz.no.anr.orbitsampleapp.repository

import com.plz.no.anr.orbitsampleapp.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
    private val api: Api
){

    suspend fun getGenderOfName(name: String): Flow<Result<Model?>> = flow {
        val response = api.getGenderOfName(name)
        println("response: $response")
        requireNotNull(response.body()?.asDomain())
        val result = Result.success(response.body()?.asDomain()!!)
        emit(result)
    }
}