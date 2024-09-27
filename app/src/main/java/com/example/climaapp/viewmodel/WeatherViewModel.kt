package com.example.climaapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climaapp.model.WeatherModel
import com.example.climaapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    // Repositorio para obtener los datos
    private val repository = WeatherRepository()

    // LiveData para exponer los datos del clima a la UI
    val weatherLiveData = MutableLiveData<WeatherModel>()
    val errorLiveData = MutableLiveData<String>()

    // Función para obtener el clima desde el repositorio
    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
                val response = repository.getWeather(city)
                weatherLiveData.value = response
            } catch (e: Exception) {
                // Manejar cualquier error que ocurra durante la obtención de datos
                errorLiveData.value = "Error fetching weather data"
            }
        }
    }
}

