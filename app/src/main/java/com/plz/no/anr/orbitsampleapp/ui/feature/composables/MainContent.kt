package com.plz.no.anr.orbitsampleapp.ui.feature.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plz.no.anr.orbitsampleapp.repository.Model
import com.plz.no.anr.orbitsampleapp.ui.feature.MainContract

@Composable
fun MainContent(
    data: Model,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Text(text = data.name)
        Text(text = data.gender)
        Text(text = data.probability.toString())
        Text(text = data.count.toString())

    }
}