package com.plz.no.anr.orbitsampleapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.plz.no.anr.orbitsampleapp.network.Api
import com.plz.no.anr.orbitsampleapp.ui.feature.MainViewModel
import com.plz.no.anr.orbitsampleapp.repository.Repository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
val appModule = module {
    single<Api> {
        Retrofit.Builder()
            .baseUrl("https://api.genderize.io")
            .addConverterFactory(json.asConverterFactory(MediaType.parse("application/json")!!))
            .build()
            .create(Api::class.java)
    }
    single {
        Repository(get())
    }
    viewModel {
        MainViewModel(get())
    }
}

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    allowStructuredMapKeys = true
    prettyPrint = true
    coerceInputValues = true
    useArrayPolymorphism = true
    allowSpecialFloatingPointValues = true
    useAlternativeNames = true
}