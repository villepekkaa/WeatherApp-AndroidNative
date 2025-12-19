package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.viewmodels.LocationViewModel
import com.example.weatherapp.ui.screens.WeatherScreen
import com.example.weatherapp.viewmodels.WeatherViewModel
import kotlin.getValue


class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val locationViewModel = LocationViewModel(this)
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   WeatherScreen(
                       modifier = Modifier.padding(innerPadding),
                       locationViewModel = locationViewModel,
                       weatherViewModel = weatherViewModel
                    )
                }
            }
        }
    }
}

