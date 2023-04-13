package com.plz.no.anr.orbitsampleapp

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.plz.no.anr.orbitsampleapp.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var json: Json
    lateinit var api: Api
    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setup() {
        println("setup")
        json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            allowStructuredMapKeys = true
            prettyPrint = true
            coerceInputValues = true
            useArrayPolymorphism = true
            allowSpecialFloatingPointValues = true
            useAlternativeNames = true
        }
        api = Retrofit.Builder()
            .baseUrl("https://api.genderize.io")
            .addConverterFactory(json.asConverterFactory(MediaType.parse("application/json")!!))
            .build()
            .create(Api::class.java)
    }

    @Test
    fun getApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getGenderOfName("peter")
            println("response: $response")
        }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}