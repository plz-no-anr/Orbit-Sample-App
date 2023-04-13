package com.plz.no.anr.orbitsampleapp.network

import com.plz.no.anr.orbitsampleapp.repository.Model

@kotlinx.serialization.Serializable
data class NetworkModel(
    val name: String,
    val gender: String,
    val probability: Double,
    val count: Long
) {
    fun asDomain() = Model(
        name, gender, probability, count
    )
}
