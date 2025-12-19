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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.appbars.BottomBar
import com.example.weatherapp.ui.appbars.TopBar
import com.example.weatherapp.ui.screens.InfoScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.viewmodels.LocationViewModel
import com.example.weatherapp.ui.screens.WeatherScreen
import com.example.weatherapp.viewmodels.WeatherViewModel
import com.example.weatherapp.viewmodels.ThemeViewModel
import kotlin.getValue


class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val themeViewModel: ThemeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val locationViewModel = LocationViewModel(this)
        setContent {
            val isDarkTheme = themeViewModel.darkTheme
            WeatherAppTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar() },
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "weather") {
                        composable("weather") {
                            WeatherScreen(
                                modifier = Modifier.padding(innerPadding),
                                locationViewModel = locationViewModel,
                                weatherViewModel = weatherViewModel
                            )
                        }
                        composable("info") {
                            InfoScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                darkTheme = isDarkTheme,
                                onThemeChange = { themeViewModel.updateDarkTheme(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}
