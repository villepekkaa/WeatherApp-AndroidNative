package com.example.weatherapp.model

data class WeatherData(
    val name: String?,
    val sys: Sys?,
    val main: Main?,
    val weather: List<Weather>?
)

data class Sys(
    val country: String?
)

data class Main(
    val temp: Double?
)

data class Weather(
    val description: String?,
    val icon: String?
)
