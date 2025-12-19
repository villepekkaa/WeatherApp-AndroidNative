package com.example.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ThemeViewModel : ViewModel() {
    var darkTheme by mutableStateOf(false)
        private set

    fun updateDarkTheme(value: Boolean) {
        darkTheme = value
    }
}
