package com.example.weatherapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.LocationLiveData

class LocationViewModel (context: Context) : ViewModel() {
    private val locationLiveData = LocationLiveData(context)
    fun getLocationLiveData() = locationLiveData
}