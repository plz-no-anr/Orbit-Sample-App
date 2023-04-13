package com.plz.no.anr.orbitsampleapp.ui.feature.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Loading...",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 18.sp
        )
    }
}