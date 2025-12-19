package com.example.weatherapp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherApi
import com.example.weatherapp.model.WeatherData
import kotlinx.coroutines.launch
import java.util.Locale

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val data: WeatherData) : WeatherUiState
    data class Error(val message: String?) : WeatherUiState
}

class WeatherViewModel : ViewModel() {
    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    fun fetchWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            try {
                val api = WeatherApi.getInstance()
                val result = api.getWeatherData(latitude, longitude)
                weatherUiState = WeatherUiState.Success(result)
            } catch (e: Exception) {
                Log.d("WeatherViewModel", e.message ?: "fetchWeather error")
                weatherUiState = WeatherUiState.Error(e.message)
            }
        }
    }

    fun formatTemperature(tempKelvin: Double?): String {
        val tempCelsius = (tempKelvin ?: 273.15) - 273.15
        return String.format(Locale.forLanguageTag("fi-FI"), "%.1f°C", tempCelsius)
    }
}
