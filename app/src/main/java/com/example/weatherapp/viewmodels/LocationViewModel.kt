package com.example.weatherapp.viewmodels

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.LocationLiveData

class LocationViewModel (context: Context) : ViewModel() {
    private val locationLiveData = LocationLiveData(context)
    private val permissionGranted = MutableLiveData<Boolean>()
    fun getLocationLiveData() = locationLiveData
    fun getPermissionLiveData() = permissionGranted

    fun checkLocationPermission(context: Context) {
        val granted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        permissionGranted.postValue(granted)
    }

    fun onPermissionResult(granted: Boolean) {
        permissionGranted.postValue(granted)
        if (granted) {
            locationLiveData.getLocationData()
        }
    }

    fun requestLocationIfPermitted() {
        if (permissionGranted.value == true) {
            locationLiveData.getLocationData()
        }
    }
}