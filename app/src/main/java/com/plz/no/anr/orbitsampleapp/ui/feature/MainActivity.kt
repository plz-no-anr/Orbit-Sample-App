package com.plz.no.anr.orbitsampleapp.ui.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plz.no.anr.orbitsampleapp.repository.Model
import com.plz.no.anr.orbitsampleapp.ui.feature.composables.Error
import com.plz.no.anr.orbitsampleapp.ui.feature.composables.Loading
import com.plz.no.anr.orbitsampleapp.ui.feature.composables.MainContent
import com.plz.no.anr.orbitsampleapp.ui.theme.OrbitSampleAppTheme
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrbitSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {

    viewModel.collectSideEffect {
        when (it) {
            is MainContract.SideEffect.ShowError -> {
                println("Error: ${it.error.message}")
            }
        }
    }

    val state = viewModel.collectAsState(lifecycleState = LocalLifecycleOwner.current.lifecycle.currentState).value
    println("state: $state")
    var name by remember { mutableStateOf("") }
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize(0.3f)
        ) {
            when {
                state.isLoading -> Loading()
                state.isError -> Error()
                else -> state.data?.let {
                    MainContent(data = it)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
        TextField(
            value = name,
            onValueChange = { name = it }
        )

        Button(onClick = {
            println("Search for $name")
            viewModel.setIntent(MainContract.Intent.GetGenderOfName(name = name))
        }) {
            Text(text = "Search")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OrbitSampleAppTheme {
        MainScreen()
    }
}