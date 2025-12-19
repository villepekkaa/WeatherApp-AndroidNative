package com.example.weatherapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherApi
import com.example.weatherapp.model.WeatherData
import kotlinx.coroutines.launch
import java.util.*

class WeatherViewModel : ViewModel() {
    private val _weather = MutableLiveData<WeatherData?>()
    val weather: LiveData<WeatherData?> = _weather

    fun fetchWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val api = WeatherApi.getInstance()
                val result = api.getWeatherData(latitude, longitude)
                _weather.postValue(result)
            } catch (e: Exception) {
                Log.d("WeatherViewModel", e.message ?: "fetchWeather error")
                _weather.postValue(null)
            }
        }
    }

    fun formatTemperature(tempKelvin: Double?): String {
        val tempCelsius = (tempKelvin ?: 273.15) - 273.15
        return String.format(Locale("fi", "FI"), "%.1f°C", tempCelsius)
    }
}
