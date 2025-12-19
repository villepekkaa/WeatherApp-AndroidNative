package com.example.weatherapp.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.viewmodels.LocationViewModel
import com.example.weatherapp.viewmodels.WeatherViewModel
import java.util.Locale

@Composable
fun WeatherScreen(
    modifier: Modifier,
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel
) {

    val location by locationViewModel.getLocationLiveData().observeAsState()
    val weather by weatherViewModel.weather.observeAsState()

    LaunchedEffect(location) {
        location?.let {
            weatherViewModel.fetchWeather(it.latitude, it.longitude)
        }
    }
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted: Boolean ->
                if (isGranted) {
                    locationViewModel.getLocationLiveData().getLocationData()
                }
            }
        )
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(16.dp))

        weather?.let { data ->
            val iconCode = data.weather?.getOrNull(0)?.icon
            if (iconCode != null) {
                val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"
                AsyncImage(
                    model = iconUrl,
                    contentDescription = data.weather?.getOrNull(0)?.description ?: "Weather icon",
                    modifier = Modifier.size(100.dp) // Set the size to 100x100 dp
                )
            }
            Text(text = "Location: ${data.name}")
            Text(text = "Weather: ${data.weather?.getOrNull(0)?.description ?: "-"}")

            val fiLocale = Locale.forLanguageTag("fi-FI")
            val tempCelsius = (data.main?.temp ?: 273.15) - 273.15
            val tempText = String.format(fiLocale, "%.1f", tempCelsius).replace('.', ',') + " °C"
            Text(text = "Temperature: $tempText")

        } ?: Text(text = "Loading weather...")

        Button(
            onClick = {
                requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        ) {
            Text("Get Weather")
        }
    }
}
