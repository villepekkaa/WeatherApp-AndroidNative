package com.example.weatherapp.ui.screens

import android.Manifest

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.viewmodels.LocationViewModel
import com.example.weatherapp.viewmodels.WeatherViewModel
import java.util.Locale



@Composable
fun WeatherScreen(
    modifier: Modifier,
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val location by locationViewModel.getLocationLiveData().observeAsState()
    val weather by weatherViewModel.weather.observeAsState()
    val permissionGranted by locationViewModel.getPermissionLiveData().observeAsState()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            locationViewModel.onPermissionResult(isGranted)
        }
    )

    LaunchedEffect(Unit) {
        locationViewModel.checkLocationPermission(context)
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted == false) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else if (permissionGranted == true) {
            locationViewModel.requestLocationIfPermitted()
        }
    }

    LaunchedEffect(location) {
        location?.let {
            weatherViewModel.fetchWeather(it.latitude, it.longitude)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        weather?.let { data ->
            val iconCode = data.weather?.getOrNull(0)?.icon
            if (iconCode != null) {
                val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"
                AsyncImage(
                    model = iconUrl,
                    contentDescription = data.weather?.getOrNull(0)?.description ?: stringResource(R.string.weather_icon_description),
                    modifier = Modifier.size(150.dp)
                )
            }
            Text(text = stringResource(R.string.location_label, data.name ?: "-"))
            Text(text = stringResource(R.string.weather_label, data.weather?.getOrNull(0)?.description ?: "-"))

            val fiLocale = Locale.forLanguageTag("fi-FI")
            val tempCelsius = (data.main?.temp ?: 273.15) - 273.15
            val tempText = String.format(fiLocale, "%.1f", tempCelsius).replace('.', ',') + " °C"
            Text(text = stringResource(R.string.temperature_label, tempText))

        } ?: run {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.loading_weather))
        }

        if (permissionGranted == false) {
            Text(text = stringResource(R.string.no_location_permission_granted_please_grant_permission_in_settings))
        }
    }
}
