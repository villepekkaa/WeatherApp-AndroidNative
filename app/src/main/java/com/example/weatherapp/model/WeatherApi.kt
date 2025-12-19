package com.example.weatherapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherapp.BuildConfig
import android.util.Log

const val BASE_URL = "https://api.openweathermap.org/"
const val API_KEY = BuildConfig.OPENWEATHER_API_KEY

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherData

    companion object {
        private var weatherService: WeatherApi? = null

        fun getInstance(): WeatherApi {
            Log.d("WeatherApi", "API_KEY: $API_KEY")
            if (weatherService == null) {
                weatherService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WeatherApi::class.java)
            }
            return weatherService!!
        }
    }
}